package io.pgs.config;

import com.zaxxer.hikari.HikariDataSource;
import io.pgs.cmn.MariaDB;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration // Spring 환경설정에 관련된 파일이다
@EnableTransactionManagement
@MapperScan(value = "io.pgs.svc", annotationClass = MariaDB.class, sqlSessionFactoryRef = "mariaSqlSessionFactory")
public class RepositoryConfig {

    @Bean(name = "jasyptEncryptor")
    public StringEncryptor jasyptEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("yiryir6398!");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPoolSize("1");
        encryptor.setConfig(config);
        return encryptor;
    }
    ///
    /// maria db 연결 관련
    ///
    @Bean(name = "mariaDataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource mariaDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "mariaTransactionManager")
    public PlatformTransactionManager mariaTransactionManager(@Qualifier("mariaDataSource") DataSource mariaDataSource) {
        return new DataSourceTransactionManager(mariaDataSource);
    }

    @Bean(name = "mariaSqlSessionFactory")
    public SqlSessionFactory mariaSqlSessionFactory(@Qualifier("mariaDataSource") DataSource mariaDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(mariaDataSource);
        sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:**/mapper/maria/*.xml"));
        return sessionFactoryBean.getObject();
    }
    ///
    /// maria db 연결 관련
    ///
}

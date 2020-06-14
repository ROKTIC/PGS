package io.pgs.config;

import io.pgs.cmn.NoEncodingPasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/dist/**", "/plugins/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/**").hasRole("ADMIN")
                    .antMatchers("/login").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll()
                .and()
                    .csrf().disable();

    }

    @Resource(name="mariaDataSource")
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(this.dataSource)
                .usersByUsernameQuery("select username, password, enabled from pgsds.users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from pgsds.authorities where username = ?")
                .passwordEncoder(new NoEncodingPasswordEncoder());
    }
}

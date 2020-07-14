package io.pgs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync
public class UnitCollectorEventConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5); // 기본 스레드 수
        threadPoolTaskExecutor.setMaxPoolSize(5);  // 최대 스레드 수
        threadPoolTaskExecutor.setQueueCapacity(0); // Queue 수
        threadPoolTaskExecutor.setThreadNamePrefix("Collector-Executor");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (Throwable throwable, Method method, Object... objects) -> {
            log.debug("==============>>>>>>>>>>>> THREAD ERROR");
            log.debug("Exception Message :: " + throwable.getMessage());
            log.debug("Method Name :: " + method.getName());
            for (Object param : objects) {
                log.debug("Parameter Value :: " + param);
            }
            // JOB_LOG : 종료 입력
            log.debug("==============>>>>>>>>>>>> THREAD ERROR END");
        };
    }


}

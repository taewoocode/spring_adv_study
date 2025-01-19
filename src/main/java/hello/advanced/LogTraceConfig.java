package hello.advanced;

import hello.advanced.trace.logtrace.FieldLogTrace;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration은 Component가 있기 때문에 ComponentScan의 대상이 된다. - 싱글톤 - 인스턴스 - 1 -
 */
@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

//    @Bean
//    public LogTrace logTrace() {
//        return new FieldLogTrace();
//    }
}

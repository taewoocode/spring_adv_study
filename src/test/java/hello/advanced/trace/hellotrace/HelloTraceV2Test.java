package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.FieldLogTrace;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

class HelloTraceV2Test {

    @Test
    @DisplayName("로그 출력 확인")
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);

    }

    @Test
    @DisplayName("예외 출력 확인")
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }

    @Test
    @DisplayName("TraceId 생명주기")
    void trace_lifeCycle() {
        FieldLogTrace logTrace = new FieldLogTrace();
        TraceStatus task1 = logTrace.begin("firstTask");
        TraceStatus task2 = logTrace.begin("secondTask");


        logTrace.end(task2);
        logTrace.end(task1);
    }
}
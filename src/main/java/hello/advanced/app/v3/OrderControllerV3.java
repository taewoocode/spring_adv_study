package hello.advanced.app.v3;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller + ResponseBody
 */
@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    /** 의존 관계 주입 **/
    private final LogTrace trace;
    private final OrderServiceV3 orderService ;

    @GetMapping("/v3/request")
    public String request(TraceId beforeTraceId, String itemId) {

        /** status scope 변경 **/
        TraceStatus status = null;

        /** 예외가 터져도 실행이 되야함 **/
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(), itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);

            /** 예외는 꼭 던져야 한다. **/
            throw e;

        }
    }
}

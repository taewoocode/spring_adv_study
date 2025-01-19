package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller + ResponseBody
 */
@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;

    /** 의존 관계 주입 **/
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(TraceId beforeTraceId, String itemId) {

        /** status scope 변경 **/
        TraceStatus status = null;

        /** 예외가 터져도 실행이 되야함 **/
        try {
            status = trace.beginSync(beforeTraceId, "OrderController.request()");
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

package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller + ResponseBody
 */
@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;

    /** 의존 관계 주입 **/
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {

         /** status scope 변경 **/
        TraceStatus status = null;

        /** 예외가 터져도 실행이 되야함 **/
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);

            /** 예외는 꼭 던져야 한다. **/
            throw e;

        }
    }
}

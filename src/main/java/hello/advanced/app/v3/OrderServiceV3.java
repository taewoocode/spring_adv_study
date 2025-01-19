package hello.advanced.app.v3;


import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem2(String itemId) {
        TraceStatus status = trace.begin("OrderService.orderItem");
        trace.exception(status, new IllegalStateException());
        orderRepository.save(itemId);
        trace.end(status);
    }

    public void orderItem(TraceId traceId, String itemId) {

        TraceStatus status = null;

        /** 예외가 터져도 실행이 되야함 **/
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        }  catch (Exception e) {
            trace.exception(status, e);

            /** 예외는 꼭 던져야 한다. **/
            throw e;
        }
    }
}

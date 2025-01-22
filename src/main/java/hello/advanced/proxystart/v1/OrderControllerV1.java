package hello.advanced.proxystart.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping // spring @Controller or @RequestMapping 이 있어야 Spring Controller로 인식이 가능하다.
@ResponseBody
public interface OrderControllerV1 {

    @GetMapping("v1/req")
    String request(@RequestParam("item") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}

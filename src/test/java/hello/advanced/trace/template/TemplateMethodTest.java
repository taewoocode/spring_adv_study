package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class TemplateMethodTest {
    
    @Test
    public void templateMethodV0() {
         logic1();
         logic2();
     }

     @Test
     public void logic1() {
         long startTime = System.currentTimeMillis();
         //비즈니스 로직 실행
         log.info("비즈니스1 로직 실행");
         //비즈니스 로직 종
         long endTime = System.currentTimeMillis();
         long resultTime = endTime - startTime;
         log.info("resultTime={}", resultTime);
     }

    @Test
    public void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스2 로직 실행");
        //비즈니스 로직 종
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    void templateMethodV1() {
        AbstractTemplate template = new SubClassLogic1();
        template.execute();
    }

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    void templateMethodV2() {
        AbstractTemplate template = new SubClassLogic2();
        template.execute();
    }


    @Test
    void templateMethodV3() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        template1.execute();

        log.info("클래스 이름1={}", template1.getClass());
        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        template2.execute();
        log.info("클래스 이름2={}", template2.getClass());
    }
}

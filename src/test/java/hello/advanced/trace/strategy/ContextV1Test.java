package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    public void strategyV0() {
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
    public String logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        log.info("비즈니스2 로직 실행");
        //비즈니스 로직 종
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
        return "ok";
    }

    /**
     * strategy()
     */
    @Test
    void strategyV1() {
        new ContextV1(new StrategyLogic1()).execute();
        new ContextV1(new StrategyLogic2()).execute();
    }

    @Test
    void strategyV2() {
        Strategy strategy = new Strategy(){

            @Override
            public void call() {
                log.info("비즈니스 로직 실행1");
            }
        };
        log.info("strategy={}", strategy.getClass());
        ContextV1 contextV1 = new ContextV1(strategy);
        contextV1.execute();

        Strategy strategy2 = new Strategy(){

            @Override
            public void call() {
                log.info("비즈니스 로직 실행2 ");
            }
        };
        log.info("strategy2={}", strategy2);
        ContextV1 context2 = new ContextV1(strategy2);
        context2.execute();
    }
}

package hello.advanced.trace;

import java.util.UUID;

public class TraceId {

    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    /**
     * 내부에서 사용하는 생성자 (외부x)
     */
    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    /**
     * Id: 앞자리 8자리만 사용 0 ~ 7
     * ex) 550e8400-e29b-41d4-a716-446655440000 -> 550e8400
     */
    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * 아후의 Id를 생성
     */
    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    /**
     * 이전의 Id를 생성
     */
    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    /**
     * 첫 번째 레벨 확인
     */
    public boolean isFirstLevel() {
        return level == 0;
    }

    /**
     * Id 반환
     */
    public String getId() {
        return id;
    }

    /**
     * level 반환
     */
    public int getLevel() {
        return level;
    }
}

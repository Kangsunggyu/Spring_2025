package org.example.memo.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class MemoResponseDto {
    private final long id;
    private final String content;
    private final LocalDateTime createTime;

    public MemoResponseDto(long id, String content, LocalDateTime createTime) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
    }
}

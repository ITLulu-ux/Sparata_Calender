package org.example.sparata_calender.Dto;

import lombok.Getter;
import org.example.sparata_calender.Entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String contents;
    private String writer;
    private Long scheduleId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.writer = comment.getWriter();
        this.scheduleId = comment.getScheduleId();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}

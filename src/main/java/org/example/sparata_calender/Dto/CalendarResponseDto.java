package org.example.sparata_calender.Dto;

import lombok.Getter;
import org.example.sparata_calender.Entity.Calendar;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CalendarResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> comments;

    public CalendarResponseDto(Calendar calendar, List<CommentResponseDto> comments) {
        this.id = calendar.getId();
        this.title = calendar.getTitle();
        this.contents = calendar.getContents();
        this.writer = calendar.getWriter();
        this.createdAt = calendar.getCreatedAt();
        this.modifiedAt = calendar.getModifiedAt();
        this.comments = comments;
    }
}

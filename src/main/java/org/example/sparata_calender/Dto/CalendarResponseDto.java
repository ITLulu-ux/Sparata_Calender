package org.example.sparata_calender.Dto;

import lombok.Getter;
import org.example.sparata_calender.Entity.Calendar;

import java.time.LocalDateTime;

@Getter
public class CalendarResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CalendarResponseDto(Calendar calendar) {
        this.id = calendar.getId();
        this.title = calendar.getTitle();
        this.contents = calendar.getContents();
        this.writer = calendar.getWriter();
        this.createdAt = calendar.getCreatedAt();
        this.modifiedAt = calendar.getModifiedAt();
    }
}

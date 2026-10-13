package org.example.sparata_calender.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String contents;
    private String writer;
    private String password;
}


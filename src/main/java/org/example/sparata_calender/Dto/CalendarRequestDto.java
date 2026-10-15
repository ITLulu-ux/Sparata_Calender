package org.example.sparata_calender.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarRequestDto {
    @NotBlank(message = "일정 제목은 필수입니다.") // 공백 무효 처리
    @Size(max = 30, message = "일정 제목은 최대 30자까지 입력할 수 있습니다.")
    private String title;

    @NotBlank(message = "일정 내용은 필수입니다.")
    @Size(max = 200, message = "일정 내용은 최대 200자까지 입력할 수 있습니다.")
    private String contents;

    @NotBlank(message = "작성자명은 필수입니다.")
    private String writer;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
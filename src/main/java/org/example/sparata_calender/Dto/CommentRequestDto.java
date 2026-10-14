package org.example.sparata_calender.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    @NotBlank(message = "댓글 내용은 필수입니다.")
    @Size(max = 100, message = "댓글 내용은 최대 100자까지 입력할 수 있습니다.")
    private String contents;

    @NotBlank(message = "작성자명은 필수입니다.")
    private String writer;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}


package org.example.sparata_calender.Controller;

import lombok.RequiredArgsConstructor;
import org.example.sparata_calender.Dto.CommentRequestDto;
import org.example.sparata_calender.Dto.CommentResponseDto;
import org.example.sparata_calender.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{scheduleId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto addComment(
            @PathVariable Long scheduleId,
            @RequestBody CommentRequestDto requestDto
    ) {
        return commentService.create(scheduleId, requestDto);
    }
}


package org.example.sparata_calender.Service;

import lombok.RequiredArgsConstructor;
import org.example.sparata_calender.Dto.CommentRequestDto;
import org.example.sparata_calender.Dto.CommentResponseDto;
import org.example.sparata_calender.Entity.Comment;
import org.example.sparata_calender.Repository.CalendarRepository;
import org.example.sparata_calender.Repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CalendarRepository calendarRepository;

    @Transactional
    public CommentResponseDto create(
            Long scheduleId,
            CommentRequestDto requestDto
    ) {
        // 1. 해당 일정이 존재하는지 확인
        calendarRepository.findById(scheduleId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "일정을 찾을 수 없습니다."
                        ));

        // 2. 일정에 등록된 댓글 개수 확인
        long commentCount =
                commentRepository.countByScheduleId(scheduleId);

        if (commentCount >= 10) {
            throw new IllegalArgumentException(
                    "일정 하나에는 댓글을 최대 10개까지 작성할 수 있습니다."
            );
        }

        // 3. 댓글 엔티티 생성
        Comment comment = new Comment(
                requestDto.getContents(),
                requestDto.getWriter(),
                requestDto.getPassword(),
                scheduleId
        );

        // 4. 댓글 저장
        Comment savedComment = commentRepository.save(comment);

        // 5. 비밀번호가 제외된 응답 DTO 반환
        return new CommentResponseDto(savedComment);
    }
}

package org.example.sparata_calender.Service;

import lombok.RequiredArgsConstructor;
import org.example.sparata_calender.Dto.CalendarRequestDto;
import org.example.sparata_calender.Dto.CalendarResponseDto;
import org.example.sparata_calender.Dto.CommentResponseDto;
import org.example.sparata_calender.Entity.Calendar;
import org.example.sparata_calender.Entity.Comment;
import org.example.sparata_calender.Repository.CalendarRepository;
import org.example.sparata_calender.Repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor    // 생성자 자동 생성
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final CommentRepository commentRepository;

    // 새로운 일정 생성하고 저장
    public CalendarResponseDto create(
            CalendarRequestDto requestDto
    ) {
        Calendar calendar = new Calendar(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getWriter(),
                requestDto.getPassword()
        );

        Calendar savedCalendar = calendarRepository.save(calendar);

        return new CalendarResponseDto(savedCalendar, List.of());
    }

    // 모두 조회
    public List<CalendarResponseDto> findAll(String writer) {
        List<Calendar> calendars;

        if (writer == null || writer.isBlank()) {
            calendars =
                    calendarRepository.findAllByOrderByModifiedAtDesc();
        } else {
            calendars =
                    calendarRepository
                            .findAllByWriterOrderByModifiedAtDesc(writer);
        }

        return calendars.stream()
                .map(calendar ->
                        new CalendarResponseDto(calendar, List.of()))
                .toList();
    }

    // id로 조회
    public CalendarResponseDto findById(Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "일정을 찾을 수 없습니다."
                        ));

        List<CommentResponseDto> comments =
                commentRepository.findAllByScheduleId(id)
                        .stream()
                        .map(CommentResponseDto::new)
                        .toList();

        return new CalendarResponseDto(calendar, comments);
    }

    // 트랜잭션 동기화
    // 수정
    @Transactional
    public CalendarResponseDto update(
            Long id,
            CalendarRequestDto requestDto
    ) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "일정을 찾을 수 없습니다."
                        ));

        if (!calendar.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException(
                    "비밀번호가 일치하지 않습니다."
            );
        }

        calendar.update(
                requestDto.getTitle(),
                requestDto.getWriter()
        );

        return new CalendarResponseDto(calendar, List.of());
    }

    // 삭제
    @Transactional
    public void delete(
            Long id,
            CalendarRequestDto requestDto
    ) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "일정을 찾을 수 없습니다."
                        ));

        if (!calendar.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException(
                    "비밀번호가 일치하지 않습니다."
            );
        }

        calendarRepository.delete(calendar);
    }
}

package org.example.sparata_calender.Controller;

import lombok.RequiredArgsConstructor;
import org.example.sparata_calender.Dto.CalendarRequestDto;
import org.example.sparata_calender.Dto.CalendarResponseDto;
import org.example.sparata_calender.Service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class CalenderController {

    private final CalendarService calendarService;

    // 일정 전체 조회
    @GetMapping
    public List<CalendarResponseDto> showCalendar(
            @RequestParam(required = false) String writer
    ) {
        return calendarService.findAll(writer);
    }

    // id 별로 조회
    @GetMapping("/{id}")
    public CalendarResponseDto showCalendarById(
            @PathVariable Long id
    ) {
        return calendarService.findById(id);
    }

    // 일정 생성
    @PostMapping
    public CalendarResponseDto addCalendar(
            @Valid @RequestBody CalendarRequestDto requestDto
    ) {
        return calendarService.create(requestDto);
    }

    // id로 부분 수정
    @PatchMapping("/{id}")
    public CalendarResponseDto modifyCalender(@PathVariable Long id, @Valid @RequestBody CalendarRequestDto requestDto) {
        return calendarService.update(id, requestDto);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @Valid @RequestBody CalendarRequestDto requestDto
    ) {
        calendarService.delete(id, requestDto);
        return ResponseEntity.noContent().build();
    }


}

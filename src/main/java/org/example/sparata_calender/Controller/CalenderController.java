package org.example.sparata_calender.Controller;

import lombok.RequiredArgsConstructor;
import org.example.sparata_calender.Dto.CalendarRequestDto;
import org.example.sparata_calender.Dto.CalendarResponseDto;
import org.example.sparata_calender.Service.CalendarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class CalenderController {

    private final CalendarService calendarService;

    @GetMapping
    public List<CalendarResponseDto> showCalendar(
            @RequestParam(required = false) String writer
    ) {
        return calendarService.findAll(writer);
    }

    @GetMapping("/{id}")
    public CalendarResponseDto showCalendarById(
            @PathVariable Long id
    ) {
        return calendarService.findById(id);
    }

    @PostMapping
    public CalendarResponseDto addCalendar(
            @RequestBody CalendarRequestDto requestDto
    ) {
        return calendarService.create(requestDto);
    }

    @PutMapping("/{id}")
    public String modifyCalender(@PathVariable Long id, @RequestBody CalendarRequestDto requestDto) {

    }

    @DeleteMapping
    public String delete(@PathVariable Long id, @RequestBody CalendarRequestDto requestDto) {

    }

}

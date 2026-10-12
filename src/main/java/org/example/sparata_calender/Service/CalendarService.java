package org.example.sparata_calender.Service;

import lombok.RequiredArgsConstructor;
import org.example.sparata_calender.Dto.CalenderRequestDto;
import org.example.sparata_calender.Dto.CalenderResponseDto;
import org.example.sparata_calender.Entity.Calendar;
import org.example.sparata_calender.Repository.CalendarRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public CalenderResponseDto create(
            CalenderRequestDto requestDto
    ) {
        Calendar calendar = new Calendar(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getWriter(),
                requestDto.getPassword()
        );

        Calendar savedCalendar = calendarRepository.save(calendar);

        return new CalenderResponseDto(savedCalendar);
    }
}
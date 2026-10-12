package org.example.sparata_calender.Repository;

import org.example.sparata_calender.Entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findAllByOrderByModifiedAtDesc();

    List<Calendar> findAllByWriterOrderByModifiedAtDesc(String writer);


}

package org.example.sparata_calender.Repository;

import org.example.sparata_calender.Entity.Calendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalenderRepository extends CrudRepository<Calendar, Integer> {
}

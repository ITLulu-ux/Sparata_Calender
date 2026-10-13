package org.example.sparata_calender.Repository;

import org.example.sparata_calender.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    long countByScheduleId(Long scheduleId);
}

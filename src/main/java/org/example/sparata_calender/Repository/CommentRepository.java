package org.example.sparata_calender.Repository;

import org.example.sparata_calender.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    long countByScheduleId(Long scheduleId);

    List<Comment> findAllByScheduleId(Long scheduleId);
}

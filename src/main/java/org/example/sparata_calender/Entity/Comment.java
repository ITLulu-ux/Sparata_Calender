package org.example.sparata_calender.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EntityListeners;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String contents;
    private String writer;
    private String password;
    private Long scheduleId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    protected Comment() {
    }

    public Comment(
            String contents,
            String writer,
            String password,
            Long scheduleId
    ) {
        this.contents = contents;
        this.writer = writer;
        this.password = password;
        this.scheduleId = scheduleId;
    }

//    public void update(String contents, String writer) {
//        this.contents = contents;
//        this.writer = writer;
//    }
}
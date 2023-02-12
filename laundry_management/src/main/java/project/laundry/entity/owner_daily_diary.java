package project.laundry.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class owner_daily_diary {

    @Id @GeneratedValue
    @Column(name = "diary_id")
    private Long id;

    private Long income;

    private LocalDateTime date;
}

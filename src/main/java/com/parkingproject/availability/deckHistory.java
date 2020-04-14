package com.parkingproject.availability;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class deckHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer eventId;

    private Integer deckId;

    private int spaceLeft;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date date;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    private Date time;
}

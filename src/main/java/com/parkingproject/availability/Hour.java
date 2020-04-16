package com.parkingproject.availability;

import lombok.Data;

@Data
public class Hour
{
    private Integer hour;

    private Integer average;

    Hour(Integer hour, Integer average)
    {
        this.hour = hour;
        this.average = average;
    }
}

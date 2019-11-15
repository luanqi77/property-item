package com.qf.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "advise")
public class Advise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advise_id")
    private Integer adviseId;
    @Column(name = "user_id")
    private Integer userId;

    private String description;
    @Column(name = "advise_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adviseTime;

    private Integer status;

}
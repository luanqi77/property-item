package com.qf.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Integer replyId;
    @Column(name = "advise_id")
    private Integer adviseId;
    @Column(name = "staff_id")
    private Integer staffId;
    @Column(name = "reply_deso")
    private String replyDeso;
    @Column(name = "reply_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date replyTime;

    private Integer status;
    @Column(name = "staff_name")
    private String staffName;

}
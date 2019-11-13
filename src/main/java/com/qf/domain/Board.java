package com.qf.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description:
 * @Company: 憨憨互联
 * @Author: 张朝
 * @Date: 2019/11/12
 * @Time: 10:14
 */
@Data
@Table(name = "board")
@Entity
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;

    @Column(name = "board_deso")
    private String boardDeso;

    @Column(name = "board_time")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date boardTime;

    private Integer level;

    private String title;
}

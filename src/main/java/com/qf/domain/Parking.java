package com.qf.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/14
 * @Time: 21:21
 */
@Data
@Table(name = "parking")
@Entity
public class Parking {
    @Id
    @Column(name = "park_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer park_id;
}

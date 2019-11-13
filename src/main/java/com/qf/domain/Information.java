package com.qf.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Company: Telegram
 * @Author: 高健
 * @Date: 2019/11/12
 * @Time: 10:41
 */
@Data
//@Entity
//@Table(name = "information")
public class Information {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "info_id")
    private Integer inid;

    private String description;

    private String tel;

    //@Column(name = "conn_name")
    private String connname;

    //@Column(name = "server_name")
    private String servername;

    private String company;

}

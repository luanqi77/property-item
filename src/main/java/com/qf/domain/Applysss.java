package com.qf.domain;

import lombok.Data;

import java.util.Date;

/**
 * @PackageName:com.qf.domain;
 * @ClassName:Applysss;
 * @author:马浩雲
 * @date2019/11/1411:38
 */
@Data
public class Applysss {
    private String username;
    private Integer apply_id;
    private String apply_deso;
    private Integer build;
    private Integer floor;
    private Integer room;
    private Date apply_time;
    private Integer status;
    private String staff_name;


}

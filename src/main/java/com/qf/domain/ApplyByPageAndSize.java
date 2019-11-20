package com.qf.domain;

import lombok.Data;

import java.util.List;

/**
 * @PackageName:com.qf.domain;
 * @ClassName:ApplyByPageAndSize;
 * @author:马浩雲
 * @date2019/11/1920:11
 */
@Data
public class ApplyByPageAndSize {
    private List<Applysss> applyList;

    private Integer total;
}

package com.qf.bean;

import com.qf.domain.LogInfo;
import lombok.Data;

import java.util.List;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/16 14:54
 */
@Data
public class logInfoResponse {
    private List<LogInfo> logInfos;
    private Long total;
}

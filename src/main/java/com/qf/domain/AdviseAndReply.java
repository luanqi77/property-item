package com.qf.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AdviseAndReply {
    String description;
    String replyDeso;
    Date adviseTime;
    Date replyTime;

}

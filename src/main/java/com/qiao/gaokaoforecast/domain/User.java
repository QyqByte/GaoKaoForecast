package com.qiao.gaokaoforecast.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QYQ
 * @version 0.1.0
 * @create 2021-08-19 17:01
 * @since 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String user_name;
    private String user_email;
    private String user_password;
    private int user_rank;
    private int user_score;
}

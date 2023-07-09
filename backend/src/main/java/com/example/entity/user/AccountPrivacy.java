package com.example.entity.user;

import lombok.Data;

/**
 * @ClassName AccountPrivacy
 * @Description TODO
 * @Author su
 * @Date 2023/7/9 15:02
 */
@Data
public class AccountPrivacy {
    int uid;
    boolean email;
    boolean sex;
    boolean phone;
    boolean qq;
    boolean wx;
    boolean blog;
}

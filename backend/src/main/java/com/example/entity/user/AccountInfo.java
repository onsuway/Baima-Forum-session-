package com.example.entity.user;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ClassName AccountInfo
 * @Description TODO
 * @Author su
 * @Date 2023/7/3 16:32
 */
@Data
public class AccountInfo {
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_一-龥]+$";

    int uid;

    @Pattern(regexp = USERNAME_REGEX)
    @Length(min = 2, max = 8)
    String username;

    String sex;

    @Length(max = 11)
    String phone;

    @Length(max = 11)
    String qq;

    @Length(max = 30)
    String wx;

    @Length(max = 50)
    String blog;

    @Length(max = 500)
    String desc;
}

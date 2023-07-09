package com.example.service;

import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountPrivacy;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author syp10
 * @Data 2023/7/3 16:37
 */
public interface UserService {
    boolean saveUserInfo(AccountInfo info);

    AccountInfo userInfo(int uid);

    boolean saveEmail(String email, int uid);

    boolean changePassword(String old_password, String new_password, int uid);

    void saveUserPrivacy(AccountPrivacy privacy);

    AccountPrivacy userPrivacy(int uid);
}

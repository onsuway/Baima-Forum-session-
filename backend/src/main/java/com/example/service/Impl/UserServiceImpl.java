package com.example.service.Impl;

import com.example.entity.auth.Account;
import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountPrivacy;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author su
 * @Date 2023/7/3 16:38
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public boolean saveUserInfo(AccountInfo info) {

        Account account = userMapper.findAccountByUsernameOrEmail(info.getUsername());
        if (account == null) {
            userMapper.updateUsername(info.getUsername(), info.getUid());
        } else if (account.getId() != info.getUid()) {
            return false;
        }

        userMapper.saveInfo(info);
        return true;
    }

    @Override
    public AccountInfo userInfo(int uid) {
        return userMapper.findInfoById(uid);
    }

    @Override
    public boolean saveEmail(String email, int uid) {
        Account account = userMapper.findAccountByUsernameOrEmail(email);
        if (account == null){
            userMapper.updateEmail(email, uid);
        }else return account.getId() == uid;
        return true;
    }

    @Override
    public boolean changePassword(String old_password, String new_password, int uid) {
        Account account = userMapper.findAccountById(uid);
        if (encoder.matches(old_password, account.getPassword())) {
            String encode = encoder.encode(new_password);
            userMapper.updatePassword(encode, uid);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public void saveUserPrivacy(AccountPrivacy privacy) {
        userMapper.savePrivacy(privacy);
    }

    @Override
    public AccountPrivacy userPrivacy(int uid) {
        return userMapper.findPrivacyById(uid);
    }
}

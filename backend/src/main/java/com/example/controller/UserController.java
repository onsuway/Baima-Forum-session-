package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountPrivacy;
import com.example.entity.user.AccountUser;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description 用户信息相关的controller
 * @Author su
 * @Date 2023/4/23 21:37
 */
@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";

    @Resource
    UserService service;

    @GetMapping("/me")
    public RestBean<AccountUser> me(@SessionAttribute("account") AccountUser user) {
        return RestBean.success(user);
    }

    @PostMapping("/save-info")
    public RestBean<String> saveInfo(@RequestBody @Validated AccountInfo info,
                                     @SessionAttribute("account") AccountUser user) {
        info.setUid(user.getId());
        if (service.saveUserInfo(info)) {
            user.setUsername(info.getUsername());
            return RestBean.success();
        } else {
            return RestBean.failure(400, "此用户名已被其他用户使用，无法修改");
        }
    }

    @GetMapping("/info")
    public RestBean<AccountInfo> info(@SessionAttribute("account") AccountUser user) {
        return RestBean.success(service.userInfo(user.getId()));
    }

    @GetMapping("/email")
    public RestBean<String> getEmail(@SessionAttribute("account") AccountUser user){
        return RestBean.success(user.getEmail());
    }

    @PostMapping("/save-email")
    public RestBean<String> email(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                  @SessionAttribute("account") AccountUser user) {
        if (service.saveEmail(email, user.getId())) {
            user.setEmail(email);
            return RestBean.success();
        } else {
            return RestBean.failure(400, "此邮箱已被其他用户使用，无法修改");
        }
    }

    @PostMapping("/save-password")
    public RestBean<String> savePassword(@Length(min = 6, max = 16) @RequestParam("old") String old_password,
                                         @Length(min = 6, max = 16) @RequestParam("new") String new_password,
                                         @SessionAttribute("account") AccountUser user){
        if (service.changePassword(old_password, new_password, user.getId())) {
            return RestBean.success();
        }else {
            return RestBean.failure(400, "原密码错误！");
        }
    }

    @PostMapping("/save-privacy")
    public RestBean<Void> savePrivacy(@RequestBody AccountPrivacy privacy,
                                      @SessionAttribute("account") AccountUser user){
        privacy.setUid(user.getId());
        service.saveUserPrivacy(privacy);
        return RestBean.success();
    }

    @GetMapping("/privacy")
    public RestBean<AccountPrivacy> getPrivacy(@SessionAttribute("account") AccountUser user){
        return RestBean.success(service.userPrivacy(user.getId()));
    }
}

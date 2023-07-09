package com.example.mapper;

import com.example.entity.auth.Account;
import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountPrivacy;
import com.example.entity.user.AccountUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassName UserMapper
 * @Description user的mapper类
 * @Author su
 * @Date 2023/4/19 21:32
 */
public interface UserMapper {

    @Select("select * from db_account where username = #{text} or email = #{text}")
    Account findAccountByUsernameOrEmail(String text);

    @Select("select * from db_account where id = #{id}")
    Account findAccountById(int id);

    @Select("select * from db_account where username = #{text} or email = #{text}")
    AccountUser findAccountUserByUsernameOrEmail(String text);

    @Insert("insert into db_account (username, password, email) values (#{username}, #{password}, #{email})")
    int creatAccount(String username, String password, String email);

    @Update("update db_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);

    @Insert("""
            insert db_account_info (uid, sex, phone, qq, wx, blog, `desc`)
            values (#{uid}, #{sex}, #{phone}, #{qq}, #{wx}, #{blog}, #{desc})
            on duplicate key update uid = #{uid}, sex = #{sex}, phone = #{phone},
            qq = #{qq}, wx = #{wx}, blog = #{blog}, `desc` = #{desc}
            """)
    void saveInfo(AccountInfo info);

    @Update("update db_account set username = #{name} where id = #{uid}")
    void updateUsername(String name, int uid);

    @Select("select * from db_account_info left join db_account on id = uid where id = #{uid}")
    AccountInfo findInfoById(int uid);

    @Update("update db_account set email = #{email} where id = #{uid}")
    void updateEmail(String email, int uid);

    @Update("update db_account set password = #{password} where id = #{uid}")
    void updatePassword(String password, int uid);

    @Insert("""
            insert db_account_privacy (uid, email, sex, phone, qq, wx, blog)
            values (#{uid}, #{email}, #{sex}, #{phone}, #{qq}, #{wx}, #{blog})
            on duplicate key update uid = #{uid}, email = #{email}, sex = #{sex},
            phone = #{phone}, qq = #{qq}, wx = #{wx}, blog = #{blog}
            """)
    void savePrivacy(AccountPrivacy privacy);

    @Select("select * from db_account_privacy where uid = #{uid}")
    AccountPrivacy findPrivacyById(int uid);

}

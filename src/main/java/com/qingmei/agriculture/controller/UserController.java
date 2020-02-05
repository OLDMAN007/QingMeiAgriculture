package com.qingmei.agriculture.controller;

import com.qingmei.agriculture.entity.User;
import com.qingmei.agriculture.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>FileName: UserController</p>
 * <p>Description: UserController</p>
 * <p>Email: zhangwb-s@qq.com</p>
 *
 * @author harper
 * @version 0.0.1
 * @date 2020/2/4
 */
@Controller
public class UserController {
    final
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 登錄
     * @param userName
     * @param passWord
     * @return
     */
    @PostMapping("/loginPost")
    public String loginPost(String userName, String passWord, HttpSession session){
        try {
            User user = userRepository.findByUserNameAndPassWord(userName, passWord);
            if( user != null){
                session.setAttribute("user", user);
                return "index";
            } else {
                session.setAttribute("loginMessage","用戶名或密碼錯誤！");
                return "login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("loginMessage","用戶名或密碼錯誤！");
//            return "redirect:login";
            return "login";
        }
    }

    /**
     * 返回主頁
     * @return
     */
    @GetMapping("index")
    public String index(){
        return "index";
    }

    /**
     *
     * @return
     */
    @GetMapping("login")
    public String login(){
        return "login";
    }


}

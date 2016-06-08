package org.interfacedesign.controller.auth;

import org.interfacedesign.assembler.UserInfo;
import org.interfacedesign.auth.domain.model.Users;
import org.interfacedesign.auth.domain.model.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lishaohua on 16-6-2.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public UserInfo login(@RequestParam(defaultValue="") String username,
                          @RequestParam(defaultValue="") String password,
                          HttpServletRequest request){
        System.out.printf("username:" + username + ", password:" + password);
        return null;
    }

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public Page<Users> getUsers(Pageable pageable) {
        return usersService.getAllUsers(pageable);
    }
}

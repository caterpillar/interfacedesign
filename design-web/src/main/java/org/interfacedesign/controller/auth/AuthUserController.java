package org.interfacedesign.controller.auth;

import org.interfacedesign.assembler.UserInfo;
import org.interfacedesign.auth.domain.model.AuthUserService;
import org.interfacedesign.auth.domain.model.PasswordAuthUser;
import org.interfacedesign.base.exception.CantFindResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lishaohua on 16-5-26.
 */
@Controller
@RequestMapping("/user")
public class AuthUserController {
    @Autowired
    private AuthUserService authUserService;

    @RequestMapping(value = "/{userName}/{password}", method = RequestMethod.GET)
    public @ResponseBody UserInfo getPasswordAuthUser(@PathVariable String userName, @PathVariable String password) {
        PasswordAuthUser authUser = authUserService.getPasswordAuthUser(userName, password);
        if(authUser == null) {
            throw new CantFindResourceException("您输入的用户名或密码错误");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(authUser.getId());
        userInfo.setUserName(authUser.getUserName());
        userInfo.setJoinTime(authUser.getJoinTime());
        return userInfo;
    }

}

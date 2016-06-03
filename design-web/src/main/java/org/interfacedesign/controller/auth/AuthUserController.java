package org.interfacedesign.controller.auth;

import org.interfacedesign.assembler.UserInfo;
import org.interfacedesign.auth.domain.model.AuthUserService;
import org.interfacedesign.auth.domain.model.PasswordAuthUser;
import org.interfacedesign.base.exception.NotFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lishaohua on 16-5-26.
 */
@Controller
@RequestMapping("/authUser")
public class AuthUserController {
    @Autowired
    private AuthUserService authUserService;

    @RequestMapping(value = "/{userName}/{password}", method = RequestMethod.GET)
    public @ResponseBody UserInfo getPasswordAuthUser(HttpServletRequest request, @PathVariable String userName, @PathVariable String password) {
        PasswordAuthUser authUser = authUserService.getPasswordAuthUser(userName, password);
        if(authUser == null) {
            throw new NotFindException("您输入的用户名或密码错误");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(authUser.getId());
        userInfo.setUserName(authUser.getUserName());
        userInfo.setJoinTime(authUser.getJoinTime());
        userInfo.setToken(request.getRequestedSessionId());
        request.getSession().setAttribute(request.getRequestedSessionId(), userInfo);
        return userInfo;
    }

    @RequestMapping(value = "/{userName}/{password}", method = RequestMethod.POST)
    public @ResponseBody void savePasswordAuthUser(@PathVariable String userName, @PathVariable String password) {
        authUserService.addAuthUser(userName, password);
    }


    public void test() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
        } else {
            String username = principal.toString();
        }
    }

}

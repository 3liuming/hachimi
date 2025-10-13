package com.ruoyi.hachimiCli.controller;

import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.hachimiCli.domaindto.UserDto;
import com.ruoyi.hachimiCli.service.UserService_Cli;
import com.ruoyi.tool.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController_Cli {

    @Autowired
    private UserService_Cli userService;

    // 登录接口
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {
            UserDto user = userService.login(userDto);
            if (user != null) {
                // 生成JWT
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                String token = JwtTokenUtil.generateToken(user.getUserId(), user.getUsername());

                Long id = JwtTokenUtil.getUserIdFromToken(token);
                String name = JwtTokenUtil.getUsernameFromToken(token);
                System.out.println(id);
                System.out.println(name);
                // 返回用户信息和token
                return new ResponseEntity<>(Map.of("hachimitoken", token, "user", user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("用户名或密码错误", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("登录失败，请重试", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 注册接口
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto user) {
        try {
            UserDto registerUser = userService.register(user);
            if (registerUser != null) {
                // 生成JWT
                String token = JwtTokenUtil.generateToken(registerUser.getUserId(), registerUser.getUsername());

                // 返回注册的用户信息和token
                return new ResponseEntity<>(Map.of("hachimitoken", token, "user", registerUser), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("用户名已存在", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("注册失败，请重试", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 检查用户名是否重复
    @GetMapping("/checkUsername")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        try {
            boolean isTaken = userService.isUsernameTaken(username);
            return new ResponseEntity<>(!isTaken, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

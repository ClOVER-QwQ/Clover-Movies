package com.clover.service;

import com.clover.dao.UserRepository;
import com.clover.pojo.User;
import com.clover.pojo.dto.LoginRequest;
import com.clover.pojo.dto.RegisterRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("邮箱已被注册");
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("用户名已存在");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // 直接存储明文密码
        user.setEmail(request.getEmail());
        user.setAvatar("../assets/no-image.png");

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("用户名不存在");
        }

        User user = userOpt.get();
        if (!user.getPassword().equals(request.getPassword())) { // 明文比较密码
            throw new RuntimeException("密码错误");
        }

        return user;
    }

    public boolean checkEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
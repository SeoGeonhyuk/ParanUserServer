package com.example.UserServer.Service;

import com.example.UserServer.DTO.UserDto;
import com.example.UserServer.Entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser(UserDto userDto); // 회원가입

    String findPasswordByEmail(String email); // 비밀번호 찾기
    User login(String email, String password); // 로그인
    Optional<User> getUserById(Long id); // 사용자 정보 조회
    Optional<User> getUserByUsername(String username); // 사용자 정보 조회
}
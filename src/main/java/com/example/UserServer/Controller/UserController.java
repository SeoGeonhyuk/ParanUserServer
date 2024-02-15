package com.example.UserServer.Controller;

import java.util.Optional;

import com.example.UserServer.DTO.UserDto;
import com.example.UserServer.Entity.User;
import com.example.UserServer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        // UserService를 통해 사용자 등록 로직 수행
        userService.registerUser(userDto);
        return ResponseEntity.ok().body("User registered successfully");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        // UserService를 통해 로그인 로직 수행
        User loginUser = userService.login(userDto.getEmail(), userDto.getPassword());
        if (loginUser != null) {
            return ResponseEntity.ok().body("User logged in successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }

    // 사용자 정보 조회
    @GetMapping("/profile/username")
    public ResponseEntity<?> getUserProfileByName(@RequestParam("username") String username) {
        // UserService를 통해 사용자 정보 조회 로직 수행
    	Optional<User> userOptional = userService.getUserByUsername(username);
    	if (!userOptional.isPresent()) {
            // user가 존재하지 않는 경우 404 Not Found 반환
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userOptional.get());
    }
    
    // 사용자 정보 조회
    @GetMapping("/profile/id")
    public ResponseEntity<?> getUserProfileById(@RequestParam("id") long id) {
        // UserService를 통해 사용자 정보 조회 로직 수행
        Optional<User> userOptional = userService.getUserById(id);
        if (!userOptional.isPresent()) {
            // user가 존재하지 않는 경우 404 Not Found 반환
            return ResponseEntity.notFound().build();
        }
        // user가 존재하는 경우, user 정보를 포함하여 200 OK 반환
        return ResponseEntity.ok().body(userOptional.get());
    }
    
    // 비밀번호 찾기
    @GetMapping("/find-password")
    public ResponseEntity<?> findPassword(@RequestParam("email") String email) {
        // 비밀번호 찾기 로직은 간단한 예시로만 처리
    	String password = userService.findPasswordByEmail(email);
    	if (password == null) {
            return ResponseEntity.notFound().build();
    	}
        return ResponseEntity.ok().body(password);
    }
}

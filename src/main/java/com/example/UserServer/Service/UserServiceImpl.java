package com.example.UserServer.Service;

import java.util.Optional;

import com.example.UserServer.DTO.UserDto;
import com.example.UserServer.Entity.User;
import com.example.UserServer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public User registerUser(UserDto userDto) {
        // 사용자 등록 로직
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        return userRepository.save(user); // 사용자 정보 저장
    }

    @Override
    public String findPasswordByEmail(String email) {
    	// 이메일 기반 비밀번호 찾기
    	Optional<User> userOptional = userRepository.findByEmail(email);
    	if (!userOptional.isPresent()) {
    		return null;
    	}
        return userOptional.get().getPassword();
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // 비밀번호가 일치하면 해당 사용자 객체 반환
            return user;
        }
        // 사용자가 없거나 비밀번호가 일치하지 않는 경우 null 반환
        return null;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        // 사용자 정보 조회
        return userRepository.findById(id);
    }
    
    @Override
    public Optional<User> getUserByUsername(String username) {
    	// 유저이름 기반 사용자 정보 조회
    	return userRepository.findByUsername(username);
    }
}
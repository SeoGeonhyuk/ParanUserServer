package com.example.UserServer.Repository;
import java.util.Optional;

import com.example.UserServer.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 이름으로 사용자 찾기
    Optional<User> findByUsername(String username);

    // 이메일로 사용자 찾기
    Optional<User> findByEmail(String email);

    // 사용자 이름이 존재하는지 확인
    boolean existsByUsername(String username);

    // 이메일이 존재하는지 확인
    boolean existsByEmail(String email);
}

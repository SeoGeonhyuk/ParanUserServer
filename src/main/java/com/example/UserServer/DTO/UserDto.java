package com.example.UserServer.DTO;

public class UserDto {
    private String username;
    private String email;
    private String password;

    // 기본 생성자
    public UserDto() {
    }

    // 모든 필드를 포함한 생성자
    public UserDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getter와 Setter 메소드
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    
    // toString, equals, hashCode 메소드는 필요에 따라 구현합니다.
}

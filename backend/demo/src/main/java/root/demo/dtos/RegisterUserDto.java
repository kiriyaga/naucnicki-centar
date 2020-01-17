package root.demo.dtos;

import root.demo.model.RoleEnum;

public class RegisterUserDto {

    private String username;
    private String token;
    private RoleEnum role;

    public RegisterUserDto(String username, String token, RoleEnum role) {
        this.username = username;
        this.token = token;
        this.role = role;
    }
    public  RegisterUserDto(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}

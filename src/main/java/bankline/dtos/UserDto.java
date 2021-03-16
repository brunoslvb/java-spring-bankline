package bankline.dtos;

import bankline.model.User;

public class UserDto {
    private String cpf;
    private String login;
    private String name;
    private String password;
    
    public UserDto(User user) {
        this.cpf = user.getCpf();
        this.login = user.getLogin();
        this.name = user.getName();
        this.password = user.getPassword();
    }

    public String getCpf() {
        return cpf;
    }
    public String getLogin() {
        return login;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

}

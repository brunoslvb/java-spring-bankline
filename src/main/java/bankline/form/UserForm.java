package bankline.form;

import javax.validation.Valid;

import bankline.model.User;

public class UserForm {
    private String cpf;
    private String login;
    private String name;
    private String password;
    

    public UserForm() {

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
    public void setPassword(String password) {
        this.password = password;
    }

    public User converter(@Valid UserForm userForm) {
        return new User(userForm);
    }

}

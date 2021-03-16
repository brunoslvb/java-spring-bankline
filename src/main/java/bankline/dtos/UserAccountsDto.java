package bankline.dtos;

public class UserAccountsDto {
    private UserDto user;
    private AccountDto accountD;
    private AccountDto accountC;
    private String token;

    public UserAccountsDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }
    public AccountDto getAccountD() {
        return accountD;
    }
    public void setAccountD(AccountDto accountD) {
        this.accountD = accountD;
    }
    public AccountDto getAccountC() {
        return accountC;
    }
    public void setAccountC(AccountDto accountC) {
        this.accountC = accountC;
    }

}

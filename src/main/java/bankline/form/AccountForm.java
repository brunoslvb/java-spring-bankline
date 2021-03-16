package bankline.form;

import bankline.model.Account;
import bankline.model.AccountType;
import bankline.model.User;
import bankline.repository.UserRepository;

public class AccountForm {
    private String number;
    private String userCpf;
    private AccountType type;
    private Double balance;

    public String getNumber() {
        return number;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public AccountType getType() {
        return type;
    }
    public void setType(AccountType type) {
        this.type = type;
    }
    public String getUserCpf() {
        return userCpf;
    }
    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public Account converter(UserRepository repository, String number, AccountType type, Double balance, String userCpf) {
        User user = repository.findByCpf(userCpf);
        return new Account(number, user, type, balance);
    }
}

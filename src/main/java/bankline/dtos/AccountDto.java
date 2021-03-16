package bankline.dtos;

import bankline.model.Account;
import bankline.model.AccountType;

public class AccountDto {
    private Integer id;
    private String number;
    private AccountType type;
    private Double balance;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.type = account.getType();
        this.balance = account.getBalance();
    }

    public Integer getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public AccountType getType() {
        return type;
    }
    public Double getBalance() {
        return balance;
    }

}

package bankline.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Account {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    //login + ContTipo.Credito
    private String number;
	
	@ManyToOne
	private User user;

    @Enumerated(EnumType.STRING)
    private AccountType type;
	
	private Double balance = 0.0;

    public Account() {
    }

	public Account(String number, User user, AccountType type, Double balance) {
		this.number = number;
		this.user = user;
		this.type = type;
		this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Account(User user) {
        this.user = user;
    }
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
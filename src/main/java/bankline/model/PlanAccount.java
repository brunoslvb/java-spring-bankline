package bankline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import bankline.form.PlanAccountForm;

@Entity
public class PlanAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull @NotEmpty
    private String descricao;
    
    @ManyToOne
    private User user;
    
    private PlanAccountType type;

    public PlanAccount() {}
    
    public PlanAccount(PlanAccountForm pAccountForm, PlanAccountType planAccountType, User user2) {
        this.descricao = pAccountForm.getDescricao();
        this.type = planAccountType;
        this.user = user2;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PlanAccountType getType() {
        return type;
    }

    public void setType(PlanAccountType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

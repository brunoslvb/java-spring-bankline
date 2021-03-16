package bankline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PlanAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    
    @ManyToOne
    private User user;
    
    private Boolean padrao;
    private PlanAccountType type;
    
    public String getNome() {
        return nome;
    }
    public PlanAccountType getType() {
        return type;
    }
    public void setType(PlanAccountType type) {
        this.type = type;
    }
    public Boolean getPadrao() {
        return padrao;
    }
    public void setPadrao(Boolean padrao) {
        this.padrao = padrao;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}

package bankline.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lancamento{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private Date date;
    private Double valor;
    @ManyToOne
    private PlanAccount planAccount;
    
    public Date getDate() {
        return date;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public PlanAccount getPlanAccount() {
        return planAccount;
    }
    public void setPlanAccount(PlanAccount planAccount) {
        this.planAccount = planAccount;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public void setDate(Date date) {
        this.date = date;
    }


}

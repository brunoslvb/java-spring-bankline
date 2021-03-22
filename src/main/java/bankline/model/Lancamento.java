package bankline.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import bankline.form.LancamentoForm;

@Entity
public class Lancamento{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull @NotEmpty
    private String descricao;

    @NotNull
    private Date date;
    
    @NotNull
    private Double valor;
    
    @ManyToOne
    private PlanAccount planAccount;
    @ManyToOne
    private Account account;
    private String accountDestino;

    public Lancamento() {}

    public Lancamento(LancamentoForm lancamentoForm, Account account, PlanAccount planAccount) {
        this.descricao = lancamentoForm.getDescricao();
        this.date = lancamentoForm.getData();
        this.account = account;
        this.valor = lancamentoForm.getValor();
        this.planAccount = planAccount;
        this.accountDestino = lancamentoForm.getContaDestino();
    }
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
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public String getAccountDestino() {
        return accountDestino;
    }
    public void setAccountDestino(String accountDestino) {
        this.accountDestino = accountDestino;
    }


}

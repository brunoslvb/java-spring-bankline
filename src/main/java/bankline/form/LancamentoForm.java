package bankline.form;

import java.util.Date;

import bankline.model.Account;
import bankline.model.Lancamento;
import bankline.model.PlanAccount;

public class LancamentoForm {
    private Integer conta;
    private String ContaDestino;
    private Date data;
    private String descricao;
    private Integer planAccount;
    private Double valor;
    
    public Integer getConta() {
        return conta;
    }
    public String getContaDestino() {
        return ContaDestino;
    }
    public void setContaDestino(String contaDestino) {
        this.ContaDestino = contaDestino;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Integer getPlanAccount() {
        return planAccount;
    }
    public void setPlanAccount(Integer planAccount) {
        this.planAccount = planAccount;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public void setConta(Integer conta) {
        this.conta = conta;
    }
    public Lancamento converter(LancamentoForm lancamentoForm, Account account, PlanAccount pAccount){
        
        Lancamento lancamento = new Lancamento(lancamentoForm, account, pAccount);

        return lancamento;
    }   
}

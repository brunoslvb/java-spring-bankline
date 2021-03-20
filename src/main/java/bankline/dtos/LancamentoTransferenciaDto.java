package bankline.dtos;

import java.util.Date;
import java.util.List;


import bankline.model.Lancamento;
import bankline.model.PlanAccountType;

public class LancamentoTransferenciaDto {
    private Integer id;
    private String contaDestino;
    private Date data;
    private Double valor;
    private Integer conta;
    private String descricao;
    private Integer planAccount;
    private PlanAccountType planAccountType;

    public LancamentoTransferenciaDto(Lancamento lancamento, PlanAccountType planAccountTypes) {
        this.id = lancamento.getId();
        this.contaDestino = lancamento.getAccountDestino();
        this.data = lancamento.getDate();
        this.valor = lancamento.getValor();
        this.conta = lancamento.getAccount().getId();
        this.descricao = lancamento.getDescricao();
        this.planAccount = lancamento.getPlanAccount().getId();
        this.planAccountType = planAccountTypes;
    }
    
    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Integer getId() {
        return id;
    }
    public PlanAccountType getPlanAccountType() {
        return planAccountType;
    }
    public void setPlanAccountType(PlanAccountType planAccountType) {
        this.planAccountType = planAccountType;
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
    public Integer getConta() {
        return conta;
    }
    public void setConta(Integer conta) {
        this.conta = conta;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public static LancamentoTransferenciaDto converter(List<Lancamento> lancamentoD, List<PlanAccountType> planAccountTypes, int i) {
        //List<LancamentoDto> lancamentoDtos = new ArrayList<>();
        
        LancamentoTransferenciaDto lancamentoTranferenciaDto = new LancamentoTransferenciaDto(lancamentoD.get(i), planAccountTypes.get(i));

        return lancamentoTranferenciaDto;
    }
}

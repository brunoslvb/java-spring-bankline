package bankline.dtos;

import java.util.List;
import java.util.stream.Collectors;

import bankline.model.PlanAccount;
import bankline.model.PlanAccountType;

public class PlanAccountDto {
    private Integer id;
    private String descricao;
    private String login;
    private PlanAccountType tipoMovimento;

    public PlanAccountDto(PlanAccount pAccount) {
        this.descricao = pAccount.getDescricao();
        this.id = pAccount.getId();
        this.login = pAccount.getUser().getLogin();
        this.tipoMovimento = pAccount.getType();
    }

    public Integer getId() {
        return id;
    }
    public PlanAccountType getTipoMovimento() {
        return tipoMovimento;
    }
    public void setTipoMovimento(PlanAccountType tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public static List<PlanAccountDto> converter(List<PlanAccount> planList) {
        return planList.stream().map(PlanAccountDto::new).collect(Collectors.toList());
    }
}

package bankline.form;

public class PlanAccountForm {
    private String descricao;
    private String login;
    private String tipoMovimento;
    private String numeroContaDestino;
    
    public String getDescricao() {
        return descricao;
    }
    public String getNumeroContaDestino() {
        return numeroContaDestino;
    }
    public void setNumeroContaDestino(String numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }
    public String getTipoMovimento() {
        return tipoMovimento;
    }
    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

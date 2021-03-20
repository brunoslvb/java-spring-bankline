package bankline.dtos;

import java.util.List;

public class DashBoardDto {
    private AccountDto accountD;
    private AccountDto accountC;
    private List<LancamentoDto> lancamentosAccountD;
    private List<LancamentoDto> lancamentosAccountC;
    private List<LancamentoTransferenciaDto> transferencias;

    public DashBoardDto(AccountDto accountDtoD, AccountDto accountDtoC, 
    List<LancamentoDto> lancamentosAccountD, List<LancamentoDto> lancamentosAccountC, List<LancamentoTransferenciaDto> transferencias) {
        this.accountD = accountDtoD;
        this.accountC = accountDtoC;
        this.lancamentosAccountD = lancamentosAccountD;
        this.lancamentosAccountC = lancamentosAccountC;
        this.transferencias = transferencias;
    }
    

    public List<LancamentoTransferenciaDto> getTransferencias() {
        return transferencias;
    }


    public void setTransferencias(List<LancamentoTransferenciaDto> transferencias) {
        this.transferencias = transferencias;
    }


    public AccountDto getAccountD() {
        return accountD;
    }
    public List<LancamentoDto> getLancamentosAccountC() {
        return lancamentosAccountC;
    }
    public void setLancamentosAccountC(List<LancamentoDto> lancamentosAccountC) {
        this.lancamentosAccountC = lancamentosAccountC;
    }
    public List<LancamentoDto> getLancamentosAccountD() {
        return lancamentosAccountD;
    }
    public void setLancamentosAccountD(List<LancamentoDto> lancamentosAccountD) {
        this.lancamentosAccountD = lancamentosAccountD;
    }
    public AccountDto getAccountC() {
        return accountC;
    }
    public void setAccountC(AccountDto accountC) {
        this.accountC = accountC;
    }
    public void setAccountD(AccountDto accountD) {
        this.accountD = accountD;
    }
}

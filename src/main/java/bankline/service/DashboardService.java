package bankline.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import bankline.dtos.AccountDto;
import bankline.dtos.DashBoardDto;
import bankline.dtos.LancamentoDto;
import bankline.dtos.LancamentoTransferenciaDto;
import bankline.model.Lancamento;
import bankline.model.PlanAccount;
import bankline.model.PlanAccountType;
import bankline.model.User;
import bankline.repository.AccountRepository;
import bankline.repository.LancamentoRepository;
import bankline.repository.PlanAccountRepository;
import bankline.repository.UserRepository;

public class DashboardService {

    public DashBoardDto servico(Date inicio, Date fim, String login, UserRepository uRepository, AccountRepository aRepository,
            LancamentoRepository lRepository, PlanAccountRepository pRepository) {
        
        User user = uRepository.findByLogin(login);

        AccountDto accountDebito = new AccountDto(aRepository.findByNumber(user.getLogin() + "D"));
        AccountDto accountCredito = new AccountDto(aRepository.findByNumber(user.getLogin() + "C"));

        List<Lancamento> lancamentoD = lRepository.findByAccountId(accountDebito.getId());
        List<Lancamento> lancamentoC = lRepository.findByAccountId(accountCredito.getId());

        List<Lancamento> lancamentosPorDataD = new ArrayList<>();
        List<Lancamento> lancamentosPorDataC = new ArrayList<>();

        SimpleDateFormat ano = new SimpleDateFormat("yyyy");
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        SimpleDateFormat dia = new SimpleDateFormat("dd");

        String dataInicio = ano.format(inicio) + mes.format(inicio) + dia.format(inicio);
        int intDataInicio = Integer.parseInt(dataInicio);

        String dataFim = ano.format(fim) + mes.format(fim) + dia.format(fim);
        int intDataFim = Integer.parseInt(dataFim);

        for (int i = 0; i < lancamentoD.size(); i++) {
            String dataLancamento = ano.format(lancamentoD.get(i).getDate()) + mes.format(lancamentoD.get(i).getDate()) + dia.format(lancamentoD.get(i).getDate());
            int intDataLancamento = Integer.parseInt(dataLancamento);
            intDataLancamento++;

            System.out.println(intDataInicio + " " + intDataLancamento);
            if(intDataLancamento >= intDataInicio && intDataLancamento <= intDataFim) {
                lancamentosPorDataD.add(lancamentoD.get(i));
                System.out.println("mano perai");
            }
        }

        for (int i = 0; i < lancamentoC.size(); i++) {
            String dataLancamento = ano.format(lancamentoC.get(i).getDate()) + mes.format(lancamentoC.get(i).getDate()) + dia.format(lancamentoC.get(i).getDate());
            int intDataLancamento = Integer.parseInt(dataLancamento);
            intDataLancamento++;

            if(intDataLancamento >= intDataInicio && intDataLancamento <= intDataFim) {
                lancamentosPorDataC.add(lancamentoC.get(i));
            }
        }   
        

        List<PlanAccountType> planAccountTypesD = new ArrayList<>();
        for (int i = 0; i < lancamentoD.size(); i++) { 
            Optional<PlanAccount> pAccount =  pRepository.findById(lancamentoD.get(i).getPlanAccount().getId());
            planAccountTypesD.add(pAccount.get().getType());
        }

        List<PlanAccountType> planAccountTypesC = new ArrayList<>();
        for (int i = 0; i < lancamentoC.size(); i++) {
            Optional<PlanAccount> pAccount =  pRepository.findById(lancamentoD.get(i).getPlanAccount().getId());
            planAccountTypesD.add(pAccount.get().getType());  
        }

        LancamentoDto lancamentoDtoD; 
        List<LancamentoDto> lancamentoDList = new ArrayList<>();
        for (int i = 0; i < lancamentosPorDataD.size(); i++) {
            if(planAccountTypesD.get(i) == PlanAccountType.TRANSFER) {
                continue;
            }
            lancamentoDtoD = LancamentoDto.converter(lancamentosPorDataD, planAccountTypesD, i);
            lancamentoDList.add(lancamentoDtoD);
        }

        LancamentoDto lancamentoDtoC; 
        List<LancamentoDto> lancamentoCList = new ArrayList<>();
        for (int i = 0; i < lancamentosPorDataC.size(); i++) {
            lancamentoDtoC = LancamentoDto.converter(lancamentosPorDataC, planAccountTypesC, i);
            lancamentoCList.add(lancamentoDtoC);
        }

        LancamentoTransferenciaDto trasferencias;
        List<LancamentoTransferenciaDto> lancamentosTransferencias = new ArrayList<>();
        for (int i = 0; i < lancamentosPorDataD.size(); i++) {
            trasferencias = LancamentoTransferenciaDto.converter(lancamentosPorDataD, planAccountTypesD, i);
            if(trasferencias.getContaDestino() != null){
                lancamentosTransferencias.add(trasferencias);
            } 
        }

        return new DashBoardDto(accountDebito, accountCredito, lancamentoDList, lancamentoCList, lancamentosTransferencias);
    }
    
}

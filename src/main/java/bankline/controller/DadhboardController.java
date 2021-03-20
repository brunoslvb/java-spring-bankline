package bankline.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bankline.dtos.DashBoardDto;
import bankline.dtos.LancamentoDto;
import bankline.dtos.LancamentoTransferenciaDto;
import bankline.dtos.AccountDto;
import bankline.model.Lancamento;
import bankline.model.PlanAccount;
import bankline.model.PlanAccountType;
import bankline.model.User;
import bankline.repository.AccountRepository;
import bankline.repository.LancamentoRepository;
import bankline.repository.PlanAccountRepository;
import bankline.repository.UserRepository;

@RestController
@RequestMapping("/dashboard")
public class DadhboardController {

    @Autowired
    private UserRepository uRepository;

    @Autowired
    private AccountRepository aRepository;
    
    @Autowired
    private LancamentoRepository lRepository;

    @Autowired
    private PlanAccountRepository pRepository;
    
    @GetMapping
    private ResponseEntity<DashBoardDto> dashLancamentos(@DateTimeFormat(pattern="yyyy-MM-dd")Date inicio, @DateTimeFormat(pattern="yyyy-MM-dd") Date fim, String login) {
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

        //String dataFim = ano.format(fim) + mes.format(fim) + dia.format(fim);
        //int intDataFim = Integer.parseInt(dataFim);

        for (int i = 0; i < lancamentoD.size(); i++) {
            String dataLancamento = ano.format(lancamentoD.get(i).getDate()) + mes.format(lancamentoD.get(i).getDate()) + dia.format(lancamentoD.get(i).getDate());
            int intDataLancamento = Integer.parseInt(dataLancamento);

            System.out.println(intDataInicio + " " + intDataLancamento);
            if(intDataLancamento >= intDataInicio) {
                lancamentosPorDataD.add(lancamentoD.get(i));
                System.out.println("mano perai");
            }
        }

        for (int i = 0; i < lancamentoC.size(); i++) {
            String dataLancamento = ano.format(lancamentoC.get(i).getDate()) + mes.format(lancamentoC.get(i).getDate()) + dia.format(lancamentoC.get(i).getDate());
            int intdataLancamento = Integer.parseInt(dataLancamento);

            if(intdataLancamento >= intDataInicio) {
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

        DashBoardDto dashBoardDto = new DashBoardDto(accountDebito, accountCredito, lancamentoDList, lancamentoCList, lancamentosTransferencias);
        
        return ResponseEntity.ok().body(dashBoardDto);
    }

}

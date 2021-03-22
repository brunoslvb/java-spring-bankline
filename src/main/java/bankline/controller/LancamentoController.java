package bankline.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bankline.form.LancamentoForm;
import bankline.model.Account;
import bankline.model.Lancamento;
import bankline.model.PlanAccount;
import bankline.repository.AccountRepository;
import bankline.repository.LancamentoRepository;
import bankline.repository.PlanAccountRepository;
import bankline.service.LancamentoService;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {
    
    @Autowired
    private AccountRepository aRepository;
    
    @Autowired
    private LancamentoRepository lRepository;

    @Autowired
    private PlanAccountRepository pRepository;

    @PostMapping
    private void  lancamento(@Valid @RequestBody LancamentoForm lancamentoForm){
        Optional<Account> account = aRepository.findById(lancamentoForm.getConta());
        Optional<PlanAccount> pAccount =  pRepository.findById(lancamentoForm.getPlanAccount());

        Lancamento lancamento = lancamentoForm.converter(lancamentoForm, account.get(), pAccount.get());

        LancamentoService lancamentoService = new LancamentoService();
        lancamentoService.gravar(lancamentoForm, aRepository, pRepository);

        lRepository.save(lancamento);      
    }
     
}

package bankline.service;

import java.util.Optional;

import bankline.form.LancamentoForm;
import bankline.model.Account;
import bankline.model.PlanAccount;
import bankline.model.PlanAccountType;
import bankline.repository.AccountRepository;
import bankline.repository.PlanAccountRepository;

public class LancamentoService {

    public void gravar(LancamentoForm form, AccountRepository aRepository, PlanAccountRepository pRepository){
        System.out.println(form.getConta());
        Optional<Account> account = aRepository.findById(form.getConta());
        Account realAccount = account.get();
        Optional<PlanAccount> pAccount =  pRepository.findById(form.getPlanAccount());

        //somar o saldo ou diminuir o saldo

        //Double valor = dto.getValor;

        //PlanAccount pa = null; //dto.idPlanAccount;

        System.out.println(pAccount.get().getType()); 
        
        if(pAccount.get().getType() == PlanAccountType.REVENUE) {
            realAccount.setBalance(realAccount.getBalance() + form.getValor());
        } else if(pAccount.get().getType() == PlanAccountType.CHARGE) {
            realAccount.setBalance(realAccount.getBalance() - form.getValor());
        }else if(pAccount.get().getType() == PlanAccountType.TRANSFER) {
            System.out.println("acertou");
            Account contaDestino = aRepository.findByNumber(form.getContaDestino());
            realAccount.setBalance(realAccount.getBalance() - form.getValor());
            contaDestino.setBalance(contaDestino.getBalance() + form.getValor());
        }

        System.out.println(realAccount.getBalance());
        aRepository.save(realAccount);

        //transferencia entre users
        //buscar a outra conta credito do usuario
        
        

    }
}

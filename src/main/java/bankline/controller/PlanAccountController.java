package bankline.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bankline.dtos.PlanAccountDto;
import bankline.form.PlanAccountForm;
import bankline.model.PlanAccount;
import bankline.model.PlanAccountType;
import bankline.model.User;
import bankline.repository.PlanAccountRepository;
import bankline.repository.UserRepository;

@RestController
@RequestMapping("/lancamento/plan-account")
public class PlanAccountController {

    @Autowired
    private PlanAccountRepository planAccountRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping    
    private ResponseEntity<PlanAccountForm> criarPlan(@Valid @RequestBody PlanAccountForm pAccountForm) {
        User user = userRepository.findByLogin(pAccountForm.getLogin());
        
        PlanAccountType tipo = PlanAccountType.CHARGE;

        switch (pAccountForm.getTipoMovimento()) {
            case "R":
                tipo = PlanAccountType.REVENUE;
                break;
        
            case "T":
                tipo = PlanAccountType.TRANSFER;
                break;
        }

        System.out.println(tipo);
        
        PlanAccount planAccount = new PlanAccount(pAccountForm, tipo, user);

        planAccountRepository.save(planAccount);
        return null;
    }

    @GetMapping
    private List<PlanAccountDto> getPlans(String login) {
        List<PlanAccount> planList = planAccountRepository.findAllByUserLogin(login);
 
        return PlanAccountDto.converter(planList);
    }
}

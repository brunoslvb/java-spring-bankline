package bankline.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bankline.config.security.TokenServ;
import bankline.dtos.AccountDto;
import bankline.dtos.UserAccountsDto;
import bankline.dtos.UserDto;
import bankline.form.AccountForm;
import bankline.form.LoginForm;
import bankline.form.UserForm;
import bankline.model.Account;
import bankline.model.AccountType;
import bankline.model.User;
import bankline.repository.AccountRepository;
import bankline.repository.UserRepository;
import bankline.service.UserCpfValidation;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenServ tokenServ;
	
    @RequestMapping(value = "/login-user", method = RequestMethod.POST)
    public ResponseEntity<UserAccountsDto> login(@RequestBody LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String tokenCrypt = tokenServ.gerarToken(authentication);

            String token = "Bearer " + tokenCrypt;

            User userTotal = userRepository.findByLogin(form.getLogin());

            UserDto userDto = new UserDto(userTotal);

            AccountDto accountDebito = new AccountDto(accountRepository.findByNumber(userTotal.getLogin() + "D"));
            AccountDto accountCredito = new AccountDto(accountRepository.findByNumber(userTotal.getLogin() + "C"));

            UserAccountsDto userAccountsDto = new UserAccountsDto();
        
            userAccountsDto.setUser(userDto);
            userAccountsDto.setAccountD(accountDebito);
            userAccountsDto.setAccountC(accountCredito);
            userAccountsDto.setToken(token);

            return ResponseEntity.ok().body(userAccountsDto);
        } catch (org.springframework.security.core.AuthenticationException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody @Valid UserForm userForm) {

        boolean isCpf = UserCpfValidation.isCPF(userForm.getCpf());

        if(isCpf == false) {
            return ResponseEntity.badRequest().body("CPF inválido");
        }
        
        Optional<User> userCheck = userRepository.findById(userForm.getCpf());
        
        if (userCheck.isPresent() && userCheck.get().getCpf() == userForm.getCpf()) {
            return ResponseEntity.badRequest().body("CPF já cadastrado");
        } else if (userCheck.isPresent() && userCheck.get().getLogin() == userForm.getLogin()) {
            return ResponseEntity.badRequest().body("Login já cadastrado");
        }

        String senhaBcrypt = new BCryptPasswordEncoder().encode(userForm.getPassword());

        User user = userForm.converter(userForm);
        user.setPassword(senhaBcrypt);

        userRepository.save(user);


        AccountForm accountForm = new AccountForm();
        accountForm.setBalance(0.0);
        accountForm.setUserCpf(user.getCpf());
        accountForm.setType(AccountType.DEBITO);
        accountForm.setNumber(user.getLogin() + "D");

        Account account = accountForm.converter(userRepository, accountForm.getNumber(), accountForm.getType(), accountForm.getBalance(), accountForm.getUserCpf());

        accountRepository.save(account);

        AccountForm accountForm2 = new AccountForm();
        accountForm2.setType(AccountType.CREDITO);
        accountForm2.setNumber(user.getLogin() + "C");

        Account account2 = accountForm.converter(userRepository, accountForm2.getNumber(), accountForm2.getType(), accountForm.getBalance(), accountForm.getUserCpf());

        accountRepository.save(account2);

        return ResponseEntity.ok().build();
    }

}

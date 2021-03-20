package bankline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bankline.model.PlanAccount;

public interface PlanAccountRepository extends JpaRepository<PlanAccount, Integer> {

    List<PlanAccount> findAllByUserLogin(String login);
    
}

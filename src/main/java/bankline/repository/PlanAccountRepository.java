package bankline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bankline.model.PlanAccount;

public interface PlanAccountRepository extends JpaRepository<PlanAccount, Integer> {
    
}

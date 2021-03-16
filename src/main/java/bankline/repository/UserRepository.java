package bankline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bankline.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByCpf(String cpf);

    User findByLogin(String login);
    
}

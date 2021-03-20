package bankline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bankline.model.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByCpf(String cpf);

    User findByLogin(String login);

    List<User> findAllByLogin(String login);
    
}

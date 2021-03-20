package bankline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bankline.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

    List<Lancamento> findByAccountId(Integer id);
    
}

package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Conta;
import orion.zenite.entidades.Linha;
import orion.zenite.entidades.Motorista;

import java.util.List;
import java.util.Optional;

public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {
    @Query(value = "select max(m.id) from Motorista m")
    int lastId();

    Optional<Motorista> findByConta(Conta conta);

    List<Motorista> findAllByNomeContaining(String nome);

    Motorista findByCpf(String cpf);
}

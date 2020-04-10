package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Conta;
import orion.zenite.entidades.Motorista;

public interface MotoristaRepository extends JpaRepository<Motorista, Integer> {
    @Query(value = "select max(m.id) from Motorista m")
    int lastId();

    Motorista findByConta(Conta conta);

    Motorista findByCpf(String cpf);
}

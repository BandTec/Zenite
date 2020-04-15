package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Conta;
import orion.zenite.entidades.Dispositivo;
import orion.zenite.entidades.Fiscal;

import java.util.Optional;

public interface FiscalRepository extends JpaRepository<Fiscal, Integer> {

    @Query(value = "select max(f.id) from Fiscal f")
    int lastId();

    Optional<Fiscal> findByDispositivo(Dispositivo dispositivo);

    Optional<Fiscal> findByConta(Conta conta);

    Optional<Fiscal> findByCpf(String cpf);
}

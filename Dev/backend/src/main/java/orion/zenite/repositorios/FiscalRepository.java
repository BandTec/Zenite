package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Conta;
import orion.zenite.entidades.Dispositivo;
import orion.zenite.entidades.Fiscal;

public interface FiscalRepository extends JpaRepository<Fiscal, Integer> {

    @Query(value = "select max(f.id) from Fiscal f")
    int lastId();

    Fiscal findByDispositivo(Dispositivo dispositivo);

    Fiscal findByConta(Conta conta);

    Fiscal findByConta(String cpf);
}

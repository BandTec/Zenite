package orion.zenite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Conta;
import orion.zenite.models.Dispositivo;
import orion.zenite.models.Fiscal;

public interface FiscalDao extends JpaRepository<Fiscal, Integer> {

    @Query(value = "select max(f.id) from Fiscal f")
    int lastId();

    Fiscal findByDispositivo(Dispositivo dispositivo);

    Fiscal findByConta(Conta conta);

    Fiscal findByConta(String cpf);
}

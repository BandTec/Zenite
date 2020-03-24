package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Conta;
import orion.zenite.models.Gerente;

public interface GerenteDao extends JpaRepository<Gerente, Integer> {
    @Query(value = "select max(g.id) from Gerente g")
    int lastId();

    Gerente findByConta(Conta conta);

    Gerente findByConta(String cpf);
}

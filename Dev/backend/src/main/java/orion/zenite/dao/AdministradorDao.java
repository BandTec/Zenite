package orion.zenite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Administrador;
import orion.zenite.models.Conta;

public interface AdministradorDao extends JpaRepository<Administrador, Integer> {

    @Query(value = "select max(a.id) from Administrador a")
    int lastId();

    Administrador findByConta(Conta conta);

    //Administrador findById(int id);
}

package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Administrador;
import orion.zenite.entidades.Conta;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    @Query(value = "select max(a.id) from Administrador a")
    int lastId();

    Administrador findByConta(Conta conta);

    //Administrador findById(int id);
}

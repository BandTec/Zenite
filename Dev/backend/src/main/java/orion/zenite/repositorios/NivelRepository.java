package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Nivel;

public interface NivelRepository extends JpaRepository<Nivel, Integer> {
    @Query(value = "select max(n.id) from Nivel n")
    int lastId();

    Nivel findByDescricao(String descricao);
}

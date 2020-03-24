package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.TipoDispositivo;

public interface TipoDispositivoDao extends JpaRepository<TipoDispositivo, Integer> {
    @Query(value = "select max(t.id) from TipoDispositivo t")
    int lastId();

    TipoDispositivo findByDescricao(String descricao);
}

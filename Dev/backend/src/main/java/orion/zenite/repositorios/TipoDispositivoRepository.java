package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.TipoDispositivo;

public interface TipoDispositivoRepository extends JpaRepository<TipoDispositivo, Integer> {
    @Query(value = "select max(t.id) from TipoDispositivo t")
    int lastId();

    TipoDispositivo findByDescricao(String descricao);
}

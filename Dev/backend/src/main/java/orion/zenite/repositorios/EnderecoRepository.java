package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    //@Query(value = "select max(id_endereco) as id_endereco from tbl_endereco", nativeQuery = true)
    @Query(value = "select max(e.id) from Endereco e")
    int lastId();

}

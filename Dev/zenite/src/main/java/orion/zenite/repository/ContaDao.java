package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Conta;
import orion.zenite.models.Nivel;

import java.util.List;
import java.util.Optional;

public interface ContaDao extends JpaRepository<Conta, Integer> {

    Conta findByEmailAndSenha(String email, String senha);

    Optional<Conta> findByEmail(String email);

    List<Conta> findByNivel(Nivel nivel);

    boolean existsByNivel(Nivel nivel);

    @Query(value = "select max(c.idConta) from Conta c")
    int lastId();

    /*
    @Query(value = "select c.*, n.descricao, n.idDescricao from Conta c inner join Nivel n where n.descricao = :nivel")
    @Query(value = "select * from Conta", nativeQuery = true)
    List<Conta> encontrarPorNivel(@Param("nivel") String nivel);
     */
}

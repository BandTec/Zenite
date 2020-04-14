package orion.zenite.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.Conta;
import orion.zenite.entidades.Nivel;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

    Conta findByEmailAndSenha(String email, String senha);

    Optional<Conta> findByEmail(String email);

    List<Conta> findByNivel(Nivel nivel);

    boolean existsByNivel(Nivel nivel);

    @Query(value = "select max(c.idConta) from Conta c")
    int lastId();

 /*
    * EXEMPLO DE CONSULTA COM JQL - jpa query language
    @Query(value = "select c.*, n.descricao, n.idDescricao from Conta c inner join Nivel n where n.descricao = :nivel")
    List<Conta> encontrarPorNivel(@Param("nivel") String nivel);
    *
    * EXEMPLO DE CONSULTA COM SQL NATIVO
    @Query(value = "select c.*, n.descricao from tbl_conta as c inner join tbl_nivel on fk_nivel as n = id_nivel where n.descricao = :nivel", nativeQuery = true)
    List<Conta> encontrarPorNivel(@Param("nivel") String nivel);
    */
}

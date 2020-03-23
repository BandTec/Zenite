package orion.zenite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.models.Conta;

public interface ContaDao extends JpaRepository<Conta, Integer> {
    
    Conta findByEmailAndSenha(String email, String senha);

    Conta findByEmail(String email);

    Boolean existsByNivel(String tipoNivel);

    @Query(value = "select max(c.idConta) from Conta c")
    int lastId();

    /*
    @Query(value = "select c.*, n.descricao, n.idDescricao from Conta c inner join Nivel n where n.descricao = :nivel")
    @Query(value = "select * from Conta", nativeQuery = true)
    List<Conta> encontrarPorNivel(@Param("nivel") String nivel);
     */
}

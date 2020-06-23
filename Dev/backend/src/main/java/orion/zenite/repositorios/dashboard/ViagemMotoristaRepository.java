package orion.zenite.repositorios.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import orion.zenite.entidades.Dashboard.ViagemMotorista;

import java.util.List;


public interface ViagemMotoristaRepository extends JpaRepository<ViagemMotorista, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM vwTempoViagemPorMotorista where id_linha = :idLinha ")
    List<ViagemMotorista> findViagemMotorista(@Param("idLinha") Integer idLinha);

    @Query(nativeQuery = true, value = "select COUNT(id_carro)  AS onibusAlocados from [dbo].[tbl_carro_linha] where id_linha = :idLinha")
    Integer findOnibusAlocados(@Param("idLinha") Integer idLinha);

    @Query(nativeQuery = true, value = "select COUNT(id_motorista) from [dbo].[tbl_motorista_carro] m INNER JOIN [dbo].[tbl_carro_linha] c on c.id_carro = m.id_carro where id_linha = :idLinha")
    Integer findMotoristasAlocados(@Param("idLinha") Integer idLinha);
}

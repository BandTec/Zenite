package orion.zenite.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import orion.zenite.entidades.CronogramaHorarios;
import orion.zenite.entidades.Linha;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CronogramaHorariosRepository extends JpaRepository<CronogramaHorarios, Integer> {

    //@Query(value = "select max(id_cronograma_horarios) as id_cronograma_horarios from tbl_cronograma_horarios",
    //nativeQuery = true)
    @Query(value = "select max(e.id) from CronogramaHorarios e")
    int lastId();

    @Query(value = "select top 1 * from tbl_cronograma_horarios ch where ch.id_motorista = ?1  and hora_prevista_saida <= ?2 and (ch.viagem_status = 2 or ch.viagem_status = 1);", nativeQuery = true)
    Optional<CronogramaHorarios> findActualOrNextViagem(int id, LocalDateTime data);

    @Query(value = "SELECT COUNT(ch.viagem_status) as ViagensRealizadas FROM tbl_cronograma c INNER JOIN tbl_cronograma_horarios ch ON c.id_cronograma = ch.id_cronograma  WHERE c.data_cronograma = ?2 AND ch.id_motorista = ?1 AND ch.viagem_status = 3", nativeQuery = true)
    int getViagensRealizadas(int id, LocalDate data);

    @Query(value = "SELECT COUNT(ch.viagem_status) as ViagensRestantes FROM tbl_cronograma c INNER JOIN tbl_cronograma_horarios ch ON c.id_cronograma = ch.id_cronograma  WHERE c.data_cronograma = ?2 AND ch.id_motorista = ?1 AND ch.viagem_status = 1", nativeQuery = true)
    int getViagensRestantes(int id, LocalDate data);

    @Query(value = "SELECT ch.* FROM tbl_cronograma c INNER JOIN tbl_cronograma_horarios ch ON c.id_cronograma = ch.id_cronograma  WHERE c.data_cronograma = ?2 AND ch.id_motorista = ?1", nativeQuery = true)
    Optional<List<CronogramaHorarios>> getViagensDoDia(int id, LocalDate data);

    List<CronogramaHorarios> findByLinha(Linha linha);
    @Query(value = "SELECT ch.* FROM tbl_cronograma_horarios ch INNER JOIN tbl_cronograma c ON c.id_cronograma = ch.id_cronograma INNER JOIN tbl_fiscal f ON f.id_Fiscal = c.fk_fiscal WHERE f.id_Fiscal = ?1 AND c.data_cronograma = '2020-10-24' AND ch.hora_prevista_saida  BETWEEN ?2 AND ?3",nativeQuery = true)
    List<CronogramaHorarios> getViagensProximaHora(int idFiscal, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);
}

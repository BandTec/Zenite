package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import orion.zenite.mappers.CarroMapper;
import orion.zenite.models.Carro;
import orion.zenite.models.Linha;

import java.util.List;

@Repository
public class CarroDao implements Dao {

    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object buscarPorId(int id) {
        try {
            this.consulta = "SELECT c.idCarro, c.numeroCarro, c.fkLinha, " +
                    "d.idDispositivo, d.codigoDispostivo, d.fkTipo " +
                    "FROM tblCarro AS c " +
                    "INNER JOIN tblDispositivo AS d " +
                    "ON c.fkDispositivo = d.idDispositivo " +
                    "WHERE idCarro = ?";

            return jdbcTemplate.queryForObject(this.consulta, new CarroMapper(), id);

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public Object buscarPorNumeroCarro(String numeroCarro) {
        try {
            this.consulta = "SELECT c.idCarro, c.numeroCarro, c.fkLinha, " +
                    "d.idDispositivo, d.codigoDispostivo, d.fkTipo " +
                    "FROM tblCarro AS c " +
                    "INNER JOIN tblDispositivo AS d " +
                    "ON c.fkDispositivo = d.idDispositivo " +
                    "WHERE numeroCarro = ?";

            return jdbcTemplate.queryForObject(this.consulta, new CarroMapper(), numeroCarro);

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public boolean existePorNumeroCarro(String numeroCarro){
        try{
            Carro carro = (Carro) buscarPorNumeroCarro(numeroCarro);
            if(carro != null){
                return true;
            }else {
                return false;
            }
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<?> buscarTodos() {
        try {
            this.consulta = "SELECT c.idCarro, c.numeroCarro, c.fkLinha, " +
                    "d.idDispositivo, d.codigoDispostivo, d.fkTipo " +
                    "FROM tblCarro AS c " +
                    "INNER JOIN tblDispositivo AS d " +
                    "ON c.fkDispositivo = d.idDispositivo";

            return jdbcTemplate.query(this.consulta, new CarroMapper());

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int ultimoId() {
        try {
            this.consulta = "SELECT TOP 1 idCarro FROM tblCarro ORDER BY idCarro DESC";
            return jdbcTemplate.queryForObject(this.consulta, Integer.class);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean inserir(Object obj) {
        try {
            Carro carro = (Carro) obj;
            this.consulta = "INSERT INTO tblCarro (numeroCarro, fkLinha, fkDispositivo) values (?, ?, ?)";
            jdbcTemplate.update(this.consulta,
                    carro.getNumero(),
                    carro.getLinha().getId(),
                    carro.getDispositivo().getId()
                    );
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alterar(Object obj) {
        try {
            Carro carro = (Carro) obj;
            this.consulta = "UPDATE tblCarro " +
                    "SET numeroCarro = ?, fkLinha = ?, fkDispositivo = ? " +
                    "WHERE idCarro = ?";

            jdbcTemplate.update(this.consulta,
                    carro.getNumero(),
                    carro.getLinha().getId(),
                    carro.getDispositivo().getId(),
                    carro.getId()
            );
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        try {
            this.consulta = "DELETE FROM tblCarro " +
                    "WHERE idCarro = ?";

            jdbcTemplate.update(this.consulta, id);
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }
}

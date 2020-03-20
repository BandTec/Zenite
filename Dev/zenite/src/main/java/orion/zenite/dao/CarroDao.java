package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import orion.zenite.mappers.CarroMapper;
import orion.zenite.models.Carro;

import java.util.List;

@Repository
public class CarroDao implements Dao {

    private String consulta;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Object buscarPorId(int id) {
        try {
            this.consulta = "EXEC spCarro_BuscaPorId @idCarro = ?";

            return jdbcTemplate.queryForObject(this.consulta, new CarroMapper(), id);

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public Object buscarPorNumeroCarro(String numeroCarro) {
        try {
            this.consulta = "EXEC spCarro_BuscaPorNumeroCarro @numeroCarro = ?";

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
            this.consulta = "EXEC spCarro_BuscarTodos";

            return jdbcTemplate.query(this.consulta, new CarroMapper());

        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int ultimoId() {
        try {
            this.consulta = "EXEC spCarro_BuscaUltimoId";
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
            this.consulta = "EXEC spCarro_Inserir @numeroCarro = ?, @fkDispositivo = ?";
            jdbcTemplate.update(this.consulta,
                    carro.getNumero(),
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
            this.consulta = "EXEC spCarro_Alterar @numeroCarro = ?, @fkDispositivo = ?, @idCarro = ?";

            jdbcTemplate.update(this.consulta,
                    carro.getNumero(),
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
            this.consulta = "EXEC spCarro_Deletar @idCarro = ?";

            jdbcTemplate.update(this.consulta, id);
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }
}

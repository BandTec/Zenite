package orion.zenite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import orion.zenite.models.Conta;
import orion.zenite.models.PontoFinal;

import java.util.List;

@Repository
public class PontoFinalDao implements Dao {

    private String consulta;


    //  tblPontoFinal
    //  "idPontoFinal", "nomeTerminal";


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public boolean inserir(Object obj) {
        try {
            PontoFinal ponto = (PontoFinal) obj;

            this.consulta = "INSERT INTO tblPontoFinal (nomeTerminals) values (?)";

            jdbcTemplate.update(consulta, ponto.getNome());

            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }


    @Override
    public Object buscarPorId(int id) {
        return null;
    }

    @Override
    public List<?> buscarTodos() {
        return null;
    }

    @Override
    public int ultimoId() {
        return 0;
    }

    @Override
    public boolean alterar(Object obj) {
        return false;
    }

    @Override
    public boolean deletar(int id) {
        return false;
    }
}

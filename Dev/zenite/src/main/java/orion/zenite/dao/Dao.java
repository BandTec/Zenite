package orion.zenite.dao;

import java.util.List;

public interface Dao {

    Object buscarPorId(int id);

    List<?> buscarTodos();

    int ultimoId();

    boolean inserir(Object obj);

    boolean alterar(Object obj);

    boolean deletar(int id);
}

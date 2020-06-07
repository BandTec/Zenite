package orion.zenite.modelos;

import org.springframework.data.domain.Page;

import java.util.List;

public class ConsultaPaginada {

    private List<Object> lista;
    private Integer totalPaginas;
    private Long totalItens;
    private Integer paginaAtual;

    public ConsultaPaginada(Page paginado) {
        lista = paginado.getContent();
        totalPaginas = paginado.getTotalPages();
        totalItens = paginado.getTotalElements();
        paginaAtual = paginado.getPageable().getPageNumber() + 1;
    }

    public Integer getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(Integer paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public List<Object> getLista() {
        return lista;
    }

    public void setLista(List<Object> lista) {
        this.lista = lista;
    }

    public Integer getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(Integer totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public Long getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(Long totalItens) {
        this.totalItens = totalItens;
    }
}

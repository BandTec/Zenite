package orion.zenite.dto;

/*
    * Classe utilizada para retornar ao usuário da api
    * o resultado de sua requisição, manda uma mensagem explicativa e
    * um campo booleano, e se for o caso, um objeto com os dados requisitados.
    *
    * Como a classe Object é classe mãe de todas as classes no Java pode ser
    * adicionado então qualquer uma das classes da pasta /models
 */
public class ResponstaApi {
    private String message;
    private Object value;

    public ResponstaApi(String message, Object value) {
        this.message = message;
        this.value = value;
    }

    public ResponstaApi(String message) {
        this.message = message;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

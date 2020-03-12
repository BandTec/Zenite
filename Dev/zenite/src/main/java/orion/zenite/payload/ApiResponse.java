package orion.zenite.payload;

/*
    * Classe utilizada para retornar ao usuário da api
    * o resultado de sua requisição, mandar uma mensagem explicativa
    * um campo booleano, e for o caso um objeto com os dados requisitados.
    *
    * Como a classe Object é classe mãe de todas as classes no Java pode ser
    * adicionado então qualquer uma das classes da pasta /models
 */
public class ApiResponse {
    private Boolean sucess;
    private String message;
    private Object value;

    public ApiResponse(Boolean sucess, String message, Object value) {
        this.sucess = sucess;
        this.message = message;
        this.value = value;
    }

    public ApiResponse(Boolean sucess, String message) {
        this.sucess = sucess;
        this.message = message;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean getSucess() {
        return sucess;
    }

    public void setSucess(Boolean sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package orion.projetoinovacao.payload;

public class ApiResponse {
    private Boolean sucess;
    private String message;

    public ApiResponse(Boolean sucess, String message) {
        this.sucess = sucess;
        this.message = message;
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

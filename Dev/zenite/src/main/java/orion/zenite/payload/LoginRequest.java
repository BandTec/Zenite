package orion.zenite.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/*
    * Classe usada para ser o padrão do corpo da requisição da rota /autentica/login
 */
public class LoginRequest {

    private String email;

    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}

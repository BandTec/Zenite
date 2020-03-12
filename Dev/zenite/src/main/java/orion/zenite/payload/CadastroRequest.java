package orion.zenite.payload;

import javax.validation.constraints.*;

public class CadastroRequest {

    private String email;

    private String senha;

    private int idNivel;

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

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
        return "CadastroRequest{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", idNivel=" + idNivel +
                '}';
    }
}

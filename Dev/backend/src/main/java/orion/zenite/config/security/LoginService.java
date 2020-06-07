package orion.zenite.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import orion.zenite.exceptions.SenhaInvalidaExcepton;
import orion.zenite.entidades.Conta;
import orion.zenite.entidades.Nivel;
import orion.zenite.modelos.LoginRequest;
import orion.zenite.repositorios.ContaRepository;

/*
    * Essa classe recebe o email do usuário da classe JwtFilter e faz a pesquisa
    * no banco de dados para verificar se o usuário existe e monta um User
    * com as informações do usuário e seu nivel de acesso
 */
@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ContaRepository contaBD;

    public UserDetails autenticar(LoginRequest conta){
        UserDetails usuario = loadUserByUsername(conta.getEmail());
        boolean senhaCorreta = passwordEncoder.matches(conta.getSenha(), usuario.getPassword());
        if(senhaCorreta){
            return usuario;
        }
        throw new SenhaInvalidaExcepton();
    }

    // Recebe email que está no token atráves da classe JwtFilter
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Conta conta = contaBD.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        Nivel nivel = conta.getNivel();

        //String[] roles = nivel.getDescricao() == "ADMIN" ? allRoles : nivel.getDescricao();

        return User
                .builder()
                .username(conta.getEmail())
                .password(conta.getSenha())
                .roles(nivel.getDescricao())
                .build();
    }
}

package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orion.zenite.dao.ContaDao;
import orion.zenite.dao.EnderecoDao;
import orion.zenite.models.Endereco;
import orion.zenite.payload.ApiResponse;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
    * Todas as rotas que começam com /api/alguma-coisa
    * estão protegidas pelo JWToken.
    * Todas as URI então recebem o token decodificado
    * como um atributo email da requisição
    *
    * a decodificação ocorre na classe /security/JwtFilter
 */
@RestController
@RequestMapping("/api/fiscal")
public class FiscalController {

    // Classes que realiza consulta no banco de dados
    @Autowired
    private EnderecoDao enderecoBD = new EnderecoDao();

    @Autowired
    private ContaDao contaBD = new ContaDao();


    //    * Rota de exemplo pegando email da requsição
    @GetMapping("/teste")
    public ResponseEntity<?> getAll(ServletRequest req){

        HttpServletRequest request = (HttpServletRequest) req;
        String email = request.getAttribute("email").toString();
        boolean existe = contaBD.verificarSeExistePorEmail(email);

        if(existe) {
            List<Endereco> enderecos = enderecoBD.buscarTodos();

            if (enderecos != null) {
                return new ResponseEntity<>(
                        new ApiResponse(true, "Requisição concluída", enderecos),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        new ApiResponse(false, "Sua requisição não teve retorno"),
                        HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Não autorizado, verifique sua credenciais/nível."),
                    HttpStatus.UNAUTHORIZED);
        }
    }


}

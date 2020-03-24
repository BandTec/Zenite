package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.models.Conta;
import orion.zenite.payload.ApiResponse;
import orion.zenite.repository.ContaDao;
import orion.zenite.repository.LinhaDao;
import orion.zenite.repository.ViagemDao;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/viagem")
public class ViagemController {

    @Autowired
    private ViagemDao viagemBD;

    @Autowired
    private ContaDao contaBD;

    @GetMapping("consulta")
    public ResponseEntity<?> consulta(ServletRequest req) {
        try {
            HttpServletRequest request = (HttpServletRequest) req;

            String email = request.getAttribute("email").toString();
            Conta conta = contaBD.findByEmail(email);

            if (conta == null) {
                return new ResponseEntity<>(
                        new ApiResponse(
                                false,
                                "Não autorizado, verifique sua credenciais/nível."
                        ),
                        HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(
                        new ApiResponse(
                                true,
                                "Requisição concluída com sucesso.",
                                viagemBD.findAll()
                        ),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(
                            false,
                            "Erro na consulta da viagem: " + e.getMessage()
                    ),
                    HttpStatus.OK);
        }
    }
}

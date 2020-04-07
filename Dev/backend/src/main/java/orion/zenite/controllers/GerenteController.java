package orion.zenite.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(description = "Operações relacionadas ao gerente", tags = "gerente")
@RestController
@RequestMapping("/api/gerente")
public class GerenteController {
}

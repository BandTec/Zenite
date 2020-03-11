package orion.zenite.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/fiscal")
public class FiscalController {

    @GetMapping("testandojwt")
    public String testar(ServletRequest req) {
        HttpServletRequest request = (HttpServletRequest) req;
        return request.getAttribute("email").toString();
    }
}

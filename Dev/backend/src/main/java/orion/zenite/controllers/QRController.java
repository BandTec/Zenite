package orion.zenite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.entidades.Carro;
import orion.zenite.entidades.Motorista;
import orion.zenite.entidades.QRGenerator;
import orion.zenite.entidades.Viagem;
import orion.zenite.repositorios.CarroRepository;
import orion.zenite.repositorios.MotoristaCarroRepository;
import orion.zenite.repositorios.MotoristaRepository;
import orion.zenite.repositorios.ViagemRepository;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/qrcode")
public class QRController {

    @Autowired
    private MotoristaRepository motoristaBD;

    @Autowired
    private ViagemRepository repository;

    @GetMapping(path = "{id}",produces=MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity gerarQr(@PathVariable("id") Integer id){
        Optional<Motorista> motorista = this.motoristaBD.findById(id);
        try {
            if(motorista.isPresent()){
                List<Viagem> viagemList = repository.findByMotorista(motorista.get());
                Viagem viagem = viagemList.get(viagemList.size() - 1);
                String qrcodeMessage = "";

                if(viagem.getHoraChegada() == null){
                    qrcodeMessage = "{ \"id\": " + motorista.get().getId() + ", \"iniciarViagem\": false, \"idViagem\":" + viagem.getId()+ "}";
                } else {
                    qrcodeMessage = "{\"id\": " + motorista.get().getId() + ", \"iniciarViagem\": true, \"idViagem\": null }";
                }

                return ok(QRGenerator.generateQRCodeImage(qrcodeMessage));
            }else{
                return notFound().build();
            }

        } catch (Exception e) {
            return badRequest().body(e.getMessage());
        }
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}

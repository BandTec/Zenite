package orion.zenite.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import orion.zenite.entidades.PontoFinal;
import orion.zenite.entidades.TempLinha;
import orion.zenite.repositorios.PontoFinalRepository;
import orion.zenite.repositorios.TempLinhaRepository;
import orion.zenite.repositorios.ViagemRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Api(description = "Operações ligadas a importação de arquivos",  tags = "Importacao")
@RestController
@RequestMapping("/api/importacao")
public class ImportacaoController {

    @Autowired
    private TempLinhaRepository linhaBD;

    @Autowired
    private PontoFinalRepository pontoRepo;

    @Autowired
    private ViagemRepository viagemRepository;

    @ApiOperation("Importação de linhas por arquivo .txt")
    @PostMapping("/linha")
    public ResponseEntity importarLinha(@RequestParam MultipartFile txt){
        String caminhoRaiz = "src/main/resources/zenite_linhas.txt";
        try{
            salvarArquivo(txt);

            BufferedReader entrada = new BufferedReader(new FileReader(caminhoRaiz));
            String registro = "";
            String tipoRegistro;
            String numero, nomeTerminalIda, nomeTerminalVolta;
            int qtdRegistros = 0;

            try{
                registro = entrada.readLine();

                while(registro != null){
                    tipoRegistro = registro.substring(0,3);

                    if(tipoRegistro.equals("L00")){

                    }else if(tipoRegistro.equals("L10")){
                        qtdRegistros++;

                        numero = registro.substring(3,9);
                        nomeTerminalIda = registro.substring(10, 90);
                        nomeTerminalVolta = registro.substring(90, 151);

                        Integer idaId = pontoRepo.findByNomeV2(nomeTerminalIda);
                        Integer voltaId = pontoRepo.findByNomeV2(nomeTerminalVolta);

                        Optional<PontoFinal> pontoFinalIda = pontoRepo.findById(idaId);
                        Optional<PontoFinal> pontoFinalVolta = pontoRepo.findById(voltaId);

                        TempLinha linha = new TempLinha();

                        linha.setPontoIda(pontoFinalIda.get());
                        linha.setPontoVolta(pontoFinalVolta.get());
                        linha.setNumero(numero);
                        linhaBD.save(linha);
                    }else{
                        ResponseEntity.ok("Leiaute de arquivo inválido!");
                    }

                    registro = entrada.readLine();
                }

            }catch(IOException e){
                e.printStackTrace();
            }catch (StringIndexOutOfBoundsException err){
                ResponseEntity.ok("Arquivo finalizado");
            }

            return ResponseEntity.ok("Foram inseridas " + qtdRegistros + " linhas de ônibus!");
        }catch(Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.noContent().build();
    }

    public void salvarArquivo(MultipartFile txt){
        String caminhoRaiz = "src/main/resources";
        //String caminhoRaiz = "C:\\Users\\Ultim\\Desktop\\BandTec\\PI\\SM ULTIMATE\\git\\OrionConecta\\Dev\\backend\\src\\main\\resources";
        Path diretorioPath = Paths.get(caminhoRaiz);
        Path arquivoPath = diretorioPath.resolve(txt.getOriginalFilename());

        try{
            Files.createDirectories(diretorioPath);
            txt.transferTo(arquivoPath.toFile());

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

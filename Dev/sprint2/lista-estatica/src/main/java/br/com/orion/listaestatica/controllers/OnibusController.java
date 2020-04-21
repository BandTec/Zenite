package br.com.orion.listaestatica.controllers;

import br.com.orion.listaestatica.ListaObj;
import br.com.orion.listaestatica.Onibus;
import br.com.orion.listaestatica.helpers.GravarArquivo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onibus")
public class OnibusController {

    ListaObj<Onibus> listaOnibus;
    GravarArquivo gravarArquivo = new GravarArquivo();

    @PostMapping("/{tamanhoLista}")
    public ResponseEntity criarLista(@PathVariable int tamanhoLista){
        if (tamanhoLista < 0){
            return ResponseEntity.badRequest().build();
        }
        listaOnibus = new ListaObj<>(tamanhoLista);
        return ResponseEntity.created(null).build();
    }

    @PostMapping
    public ResponseEntity cadastrarOnibus(@RequestBody Onibus novoOnibus){
        if(!listaInicializada()){
            return ResponseEntity.status(409).body("Primeiro inicie a lista " +
                    "na rota /onibus/{tamanhoLista}");
        }else{
            if(listaOnibus.getTamanho() < listaOnibus.getTamanhoTotal()) {
                listaOnibus.adiciona(novoOnibus);
                return ResponseEntity.created(null).build();
            }else{
                return ResponseEntity.badRequest().body("Lista de onibus completa");
            }
        }
    }

    @GetMapping
    public ResponseEntity exibirLista(){
        if(listaOnibus.getTamanho() == 0 || !listaInicializada()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaOnibus.exibeJSON());
        }
    }

    @PostMapping("/gerarCSV/{linha}")
    public ResponseEntity gerarCsv(@PathVariable String linha){
        if(listaOnibus.getTamanho() == 0 || !listaInicializada()){
            return ResponseEntity.status(409).body("Primeiro inicie a lista " +
                    "na rota /onibus/{tamanhoLista}");
        }else{
            gravarArquivo.gravaLista(listaOnibus, true, linha);
            listaOnibus.limpar();
            return ResponseEntity.created(null).body("Arquivo 'CarrosLinha"+linha+".csv' foi criado!");
        }
    }
    @PostMapping("/gerarTXT/{linha}")
    public ResponseEntity gerarTxt(@PathVariable String linha){
        if(listaOnibus.getTamanho() == 0 || !listaInicializada()){
            return ResponseEntity.status(409).body("Primeiro inicie a lista " +
                    "na rota /onibus/{tamanhoLista}");
        }else{
            gravarArquivo.gravaLista(listaOnibus, false, linha);
            listaOnibus.limpar();
            return ResponseEntity.created(null).body("Arquivo 'CarrosLinha"+linha+".txt' foi criado!");
        }
    }

    private boolean listaInicializada(){
        if (listaOnibus == null){
            return false;
        }else{
            return true;
        }
    }

}

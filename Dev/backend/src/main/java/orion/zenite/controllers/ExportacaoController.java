package orion.zenite.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orion.zenite.entidades.*;
import orion.zenite.repositorios.LinhaRepository;
import orion.zenite.repositorios.ViagemRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api(description = "Operações ligadas a exportação de arquivos")
@RestController
@RequestMapping("/api/exportacao")
public class ExportacaoController {

    @Autowired
    private LinhaRepository linhaBD;

    @Autowired
    private ViagemRepository viagemRepository;

    //Exporta um arquivo TXT com os dados de linha de acordo com o Leiaute de arquivo v1 do Zenite
    @GetMapping("/linha")
    public HttpEntity<byte[]> exportarLinha(){
        String caminhoDoArquivo = "src/main/resources/txt.txt";
        List<Linha> consulta = linhaBD.findAll();

        File file = new File(caminhoDoArquivo);

        try(Writer writer = new BufferedWriter(new FileWriter(file))){

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataReferencia = (c.get(Calendar.MONTH) + 1 ) < 10 ? "0" + (c.get(Calendar.MONTH) + 1) : ""+(c.get(Calendar.MONTH) + 1);
            dataReferencia += c.get(Calendar.YEAR);
            String dataFormatada = dateFormat.format(new Date());

            escreverHeader("L00", "Linha", dataReferencia, dataFormatada,
                    "v1", writer);

            int qtdRegistros = 0;

            for(Linha l : consulta){
                PontoFinal pontoIda = l.getPontoIda();
                PontoFinal pontoVolta = l.getPontoVolta();

                escreverCorpo("L10", writer, 3, false);
                escreverCorpo(l.getNumero(), writer, 7, false);
                escreverCorpo(pontoIda.getNome(), writer, 80, false);
                escreverCorpo(pontoVolta.getNome(), writer, 80, true);

                qtdRegistros++;

            }

            escreverFooter("L90", qtdRegistros, writer);

            byte[] arquivo = Files.readAllBytes(Paths.get(caminhoDoArquivo));

            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.add("Content-Disposition", "attachment;filename=\"zenite_linhas.txt\"");

            HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

            return entity;
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;

    }

    //Exporta um arquivo TXT com os dados de viagens de acordo com o Leiaute de arquivo v1 do Zenite
    @GetMapping("/viagem")
    public HttpEntity<byte[]> exportarViagem(){
        String caminhoDoArquivo = "src/main/resources/txt.txt";
        List<Viagem> viagem = viagemRepository.findAll();

        File file = new File(caminhoDoArquivo);

        try(Writer writer = new BufferedWriter(new FileWriter(file))){

            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataReferencia = (c.get(Calendar.MONTH) + 1 ) < 10 ? "0" + (c.get(Calendar.MONTH) + 1) : ""+(c.get(Calendar.MONTH) + 1);
            dataReferencia += c.get(Calendar.YEAR);
            String dataFormatada = dateFormat.format(new Date());

            escreverHeader("V00", "Viagem", dataReferencia, dataFormatada,
                    "v1", writer);

            int qtdRegistros = 0;

            for(Viagem v : viagem){
                Carro carro = v.getCarro();
                Fiscal fiscalIda = v.getFiscal();
                Fiscal fiscalVolta = v.getFiscalVolta();
                Linha linha = v.getLinha();
                Motorista motorista = v.getMotorista();
                escreverCorpo("V10", writer, 3, false);
                escreverCorpo(""+v.getId(), writer, 6, false);
                String horaChegada = v.getHoraChegada().toString().replace("T", " ");
                String horaSaida = v.getHoraSaida().toString().replace("T", " ");
                escreverCorpo(horaChegada.substring(0,18), writer, 19, false);
                escreverCorpo(horaSaida.substring(0,18), writer, 19, false);
                escreverCorpo(""+v.getQtdPassageiros(), writer, 3, false);
                escreverCorpo(carro.getNumero(), writer, 8, false);
                escreverCorpo(fiscalIda.getNome(), writer, 50, false);
                escreverCorpo(linha.getNumero(), writer, 7, false);
                escreverCorpo(motorista.getNome(), writer, 50, false);
                escreverCorpo(fiscalVolta.getNome(), writer, 50, true);

                qtdRegistros++;
            }

            escreverFooter("V90", qtdRegistros, writer);

            byte[] arquivo = Files.readAllBytes(Paths.get(caminhoDoArquivo));

            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.add("Content-Disposition", "attachment;filename=\"zenite_viagens.txt\"");

            HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

            return entity;

        }catch (IOException e){
            e.printStackTrace();
        }

        return null;

    }

    //Excreve os dados no arquivo determinado
    private void escreverCorpo(String texto, Writer escritor, int tamanhoCampo, boolean nextLine){

        String textoFormatado = String.format("%-" + tamanhoCampo + "s", texto);

        try{
            escritor.append(textoFormatado);

            if(nextLine)
                escritor.append("\n");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Escreve os dados do Header do arquivo
    private void escreverHeader(String tipoRegistroT, String tipoArquivoT, String dataReferenciaT,
                                String DataArquivoT, String versaoLeiauteT,
                                Writer escritor){

        String tipoRegistro = String.format("%-3s", tipoRegistroT);
        String tipoArquivo = String.format("%-5s", tipoArquivoT);
        String dataReferencia = String.format("%-6s", dataReferenciaT);
        String DataArquivo = String.format("%-6s", DataArquivoT);
        String versaoLeiaute = String.format("%-6s", versaoLeiauteT);

        try{
            escritor.append(tipoRegistro);
            escritor.append(tipoArquivo);
            escritor.append(dataReferencia);
            escritor.append(DataArquivo);
            escritor.append(versaoLeiaute);

            escritor.append("\n");

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //Escreve os dados do Footer do arquivo
    private void escreverFooter(String tipoRegistroT, int qtdRegistrosT,
                                Writer writer){

        String tipoRegistro = String.format("%-3s", tipoRegistroT);
        String qtdRegistros = String.format("%-5s", qtdRegistrosT);

        try{
            writer.append(tipoRegistro);
            writer.append(qtdRegistros);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

}

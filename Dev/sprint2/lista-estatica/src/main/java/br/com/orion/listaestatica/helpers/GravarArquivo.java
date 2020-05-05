package br.com.orion.listaestatica.helpers;

import br.com.orion.listaestatica.ListaObj;
import br.com.orion.listaestatica.Onibus;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class GravarArquivo {
    public void gravaLista(ListaObj<Onibus> lista, boolean isCSV, String numLinha) {
        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;
        String nomeArquivo;

        if (isCSV) {
            nomeArquivo= "CarrosLinha"+numLinha+".csv";
        }
        else {
            nomeArquivo= "CarrosLinha"+numLinha+".txt";
        }

        try {
            arq = new FileWriter(nomeArquivo, false);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.err.println("Erro ao abrir arquivo");
            System.exit(1);
        }

        try {
            if(isCSV){
                saida.format("NumOnibus;Placa;Modelo;Fabricante;Acessivel%n");
            }else {
                saida.format("NumOnibus Placa Modelo Fabricante Acessivel%n");
            }

            for (int i=0; i< lista.getTamanho(); i++) {
                Onibus onibus = lista.getElemento(i);
                if (isCSV) {
                    saida.format("%d;%s;%s;%s;%b%n",onibus.getNumOnibus(),
                            onibus.getPlaca(),onibus.getModelo(),onibus.getFabricante(), onibus.isAcessivel());
                }
                else {
                    saida.format("%d %s %s %s %b%n",onibus.getNumOnibus(),
                            onibus.getPlaca(),onibus.getModelo(),onibus.getFabricante(), onibus.isAcessivel());
                }
            }
        }
        catch (FormatterClosedException erro) {
            System.err.println("Erro ao gravar no arquivo");
            deuRuim= true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}

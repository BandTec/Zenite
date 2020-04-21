package br.com.orion.listaestatica;

public class ListaObj<T> {

    T[] vetor;
    int nroElem;

    public ListaObj(int tam) {
        vetor = (T[]) new Object[tam];
        nroElem = 0;
    }

    public void adiciona(T elemento){
        if(nroElem < vetor.length){
            vetor[nroElem] = elemento;
            nroElem++;
        }else{
            System.out.println("A lista está cheia");
        }
    }

    public void exibe(){
        for (int i = 0; i < nroElem; i++) {
            System.out.println(vetor[i]);
        }
    }

    public T[] exibeJSON(){
        return vetor;
    }

    public int busca(T pesquisa){
        for (int i = 0; i < nroElem ; i++) {
            if(vetor[i].equals(pesquisa)){
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice){
        if(indice < 0 || indice >= nroElem){
            return false;
        }

        nroElem--;
        this.reordenar(indice);

        return true;
    }


    public boolean removeElemento(T elemento){
        int indice = busca(elemento);
        if(indice == -1){
            return false;
        }
        nroElem--;
        this.reordenar(indice);
        return true;
    }

    private void reordenar(int indiceRemovido){
        for (int i = indiceRemovido + 1; i <= nroElem; i++) {
            vetor[i-1] = vetor[i];
        }
    }

    public int getTamanho(){
        return nroElem;
    }

    public int getTamanhoTotal(){ return vetor.length; }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        }
        return vetor[indice];
    }

    public void limpar(){
        vetor = (T[]) new Object[vetor.length+1];
        nroElem = 0;
    }
}

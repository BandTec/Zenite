package orion.zenite.modelos;

public class FilaObj<T> {

    private int tamanho;
    private T[] fila;

    public FilaObj(int tamanho) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[tamanho];
    }

    public boolean isEmpty(){
        return tamanho == 0;
    }

    public boolean isFull(){
        return tamanho == fila.length;
    }

    public void insert(T info){
        if(!isFull())
            fila[tamanho++] = info;
        else
            System.out.println("Lista Cheia!");
    }

    public T peek(){
        return fila[0];
    }

    public T poll(){
        T aux = fila[0];
        if(!isEmpty()){
            for(int i = 0; i < tamanho-1;i++){
                fila[i] = fila[i+1];
            }
            fila[tamanho-1] = null;
            tamanho -= 1;
        }else{
            return null;
        }
        return aux;
    }

    public void exibe(){
        if(isEmpty())
            System.out.println("Fila Vazia!");
        else{
            for(int i = 0; i < tamanho; i++){
                System.out.println(fila[i]);
            }
        }
    }

}

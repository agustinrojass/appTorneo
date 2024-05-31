package clases;
import java.io.Serializable;
import java.util.ArrayList;
public class Contenedor<E> implements Serializable {
    //Atributos
    private ArrayList<E> lista;
    //Constructores
    public Contenedor() {
        this.lista = new ArrayList<>();
    }
    public Contenedor(ArrayList<E> listaAux) {
        this.lista = listaAux;
    }
    //Metodos
    public ArrayList<E> getContenedor() {
        return lista;
    }
    public int size() {
        return getContenedor().size();
    }
    public void add(E elemento) {
        getContenedor().add(elemento);
    }
    public E getFirst() {
        E e = null;
        if(size() != 0) {
            e = getContenedor().getFirst();
        }
        return e;
    }
    public E get(int i) {
        E e = null;
        if(getContenedor().get(i) != null) {
            e = getContenedor().get(i);
        }
        return e;
    }
}
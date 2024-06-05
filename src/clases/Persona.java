package clases;
import java.io.Serializable;
public class Persona implements Serializable {
    //Atributos
    private String nombre;
    private int edad;
    //Constructores
    public Persona(String nombreAux, int edadAux) {
        this.nombre = nombreAux;
        this.edad = edadAux;
    }
    //Metodos
    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }
    @Override
    public String toString() {
        return String.format("%-40s", nombre + " (" + edad + ")");
    }
}
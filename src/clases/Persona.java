package clases;
import java.io.Serializable;
public class Persona implements Serializable {
    //Atributos
    private final String nombre;
    private final int edad;
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
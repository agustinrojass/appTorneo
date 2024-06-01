package clases;
import java.io.Serializable;
public class Persona implements Serializable {
    //Artibutos
    private String nombre;
    private int edad;
    //Constructores
    public Persona() {
        this.nombre = "";
        this.edad = 0;
    } //se usa por Tecnico(), pero despues no se va a usar
    public Persona(String nombreAux, int edadAux) {
        this.nombre = nombreAux;
        this.edad = edadAux;
    }
    //Metodos
    @Override
    public String toString() {
        return String.format("%-40s", nombre + " (" + edad + ")");
    }
}
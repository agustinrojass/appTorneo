package Clases;
import java.util.Date;

public class Persona {
    //Artibutos
    private String nombre;
    private int edad;
    //private Date nacimiento; //trabajar la edad a partir de la fecha de nacimiento
    //Constructores
    public Persona() {
        setNombre("");
        setEdad(0);
    }
    public Persona(String nombreAux, int edadAux) {
        setNombre(nombreAux);
        setEdad(edadAux);
    }
    //Metodos
    private void setNombre(String nombreAux) {
        this.nombre = nombreAux;
    }
    private String getNombre() {
        return nombre;
    }
    private void setEdad(int edadAux) {
        this.edad = edadAux;
    }
    private int getEdad() {
        return edad;
    }
    @Override
    public String toString() {
        return String.format("%-40s", getNombre() + " (" + getEdad() + ")");
    }
}

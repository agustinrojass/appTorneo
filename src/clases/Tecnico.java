package clases;
public class Tecnico extends Persona {
    //Atributos
    private Contenedor<String> trayectoria;
    //Constructores
    public Tecnico(String nombreAux, int edadAux, Contenedor<String> trayectoriaAux) {
        super(nombreAux, edadAux);
        this.trayectoria = trayectoriaAux;
    }
    //Metodos
    public Contenedor<String> getTrayectoria() {
        return trayectoria;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
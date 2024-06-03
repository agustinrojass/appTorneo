package clases;
public class Tecnico extends Persona {
    //Atributos
    private Contenedor<String> trayectoria;
    //Constructores
    public Tecnico() {
        super();
        this.trayectoria = new Contenedor<>();
    } //por ahora se usa, pero despues no se va a usar
    public Tecnico(String nombreAux, int edadAux, Contenedor<String> trayectoriaAux) {
        super(nombreAux, edadAux);
        this.trayectoria = trayectoriaAux;
    }
    //Metodos
    @Override
    public String toString() {
        return super.toString();
    }
}
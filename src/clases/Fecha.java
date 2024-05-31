package clases;
import java.io.Serializable;
public class Fecha implements Serializable {
    //Atributos
    private int numeroFecha;
    private Contenedor<PartidoFutbol> partidos;
    //Constructores
    public Fecha() {
        this.numeroFecha = 0;
        this.partidos = new Contenedor<>();
    }
    public Fecha(int numeroFechaAux) {
        this.numeroFecha = numeroFechaAux;
        this.partidos = new Contenedor<>();
    }
    //Metodos
    public Contenedor<PartidoFutbol> getPartidos() {
        return partidos;
    } //por ahora no se usa
    public void agregarPartido(Equipo local, Equipo visitante) {
        this.partidos.add(new PartidoFutbol(local, visitante));
    } //por ahora se usa
    private int devolverIndicePartido(Equipo local, Equipo visitante) {
        int rta = -1;
        for(int i = 0; i < partidos.size() && rta == -1; i++) {
            if(partidos.get(i).getLocal().equals(local) && partidos.get(i).getVisitante().equals(visitante)) {
                rta = i;
            }
        }
        return rta;
    } //por ahora no se usa
    @Override
    public String toString() {
        String s = "Fecha: " + numeroFecha;
        for(int i = 0; i < partidos.size(); i++) {
            s += partidos.get(i) + "\n";
        }
        return s;
    }
}
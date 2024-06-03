package clases;
import java.io.Serializable;
public class PartidoFutbol implements Serializable {
    //Atributos
    private Equipo local;
    private Equipo visitante;
    private int golesL;
    private int golesV;
    //Constructores
    public PartidoFutbol(Equipo local, Equipo visitante, int golesL, int golesV) {
        this.local = local;
        this.visitante = visitante;
        this.golesL = golesL;
        this.golesV = golesV;
    }
    //Metodos
    public Equipo getLocal() {
        return local;
    }
    public Equipo getVisitante() {
        return visitante;
    }
    @Override
    public String toString() {
        String goles;
        if(golesL == -1 && golesV == -1) { //formateo para que no ponga 4000 goles por si era null en la api pq todavia no se jugo
            goles = " [" + " " + "] - [" + " " + "] ";
        }
        else {
            goles = " [" + golesL + "] - [" + golesV + "] ";
        }
        return String.format("%34s", local.getNombre()) + goles + String.format("%-39s", visitante.getNombre());
    }
}
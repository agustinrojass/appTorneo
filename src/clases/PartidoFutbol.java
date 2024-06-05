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
    public int getGolesL() {
        return golesL;
    }
    public int getGolesV() {
        return golesV;
    }
    @Override
    public String toString() {
        String goles;
        if(golesL == -1 && golesV == -1) {
            goles = " [" + " " + "] - [" + " " + "] ";
        }
        else {
            goles = " [" + golesL + "] - [" + golesV + "] ";
        }
        return String.format("%34s", local.getNombre()) + goles + String.format("%-39s", visitante.getNombre());
    }
}
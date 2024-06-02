package clases;
import java.io.Serializable;
public class PartidoFutbol implements Serializable {
    //Atributos
    private Equipo local;
    private Equipo visitante;
    private int golesL;
    private int golesV;
    //Constructores
    public PartidoFutbol() {
        this.local = new Equipo();
        this.visitante = new Equipo();
        this.golesL = 0;
        this.golesV = 0;
    } //por ahora no se usa
    public PartidoFutbol(Equipo localAux, Equipo visitanteAux) {
        this.local = localAux;
        this.visitante = visitanteAux;
        this.golesL = 0;
        this.golesV = 0;
    }

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
    public void actualizarResultado(int golesLAux, int golesVAux) {
        this.golesL = golesLAux;
        this.golesV = golesVAux;
        calcularPuntos();
    } //por ahora no se usa
    public void calcularPuntos() {
        if(golesL > golesV) {
            local.ganarPartido(golesL, golesV);
            visitante.perderPartido(golesV, golesL);
        }
        else if(golesL < golesV) {
            local.perderPartido(golesL, golesV);
            visitante.ganarPartido(golesV, golesL);
        }
        else {
            local.empatarPartido(golesL, golesV);
            visitante.empatarPartido(golesV, golesL);
        }
    } //por ahora no se usa
    @Override
    public String toString() {
        String goles = " [" + golesL + "] - [" + golesV + "] ";
        if (golesL == 4000 && golesV == 4000)       //formateo para que no ponga 4000 goles por si era null en la api pq todavia no se jugo
        {
            goles = " [" + " " + "] - [" + " " + "] ";
        }
        //return String.format("%25s", local.getNombre()) + " [" + golesL + "] - [" + golesV + "] " + visitante.getNombre();
        return String.format("%25s", local.getNombre()) + goles + visitante.getNombre();
    }
}
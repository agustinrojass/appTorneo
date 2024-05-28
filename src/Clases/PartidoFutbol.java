package Clases;

public class PartidoFutbol {
    //Atributos
    private Equipo local;
    private Equipo visitante;
    private int golesL;
    private int golesV;
    //Constructores
    public PartidoFutbol() {
        setLocal(new Equipo());
        setVisitante(new Equipo());
        setGolesL(0);
        setGolesV(0);
    }
    public PartidoFutbol(Equipo localAux, Equipo visitanteAux) {
        setLocal(localAux);
        setVisitante(visitanteAux);
        setGolesL(0);
        setGolesV(0);
    }
    //Metodos
    private void setLocal(Equipo localAux) {
        this.local = localAux;
    }
    public Equipo getLocal() {
        return local;
    }
    private void setVisitante(Equipo visitanteAux) {
        this.visitante = visitanteAux;
    }
    public Equipo getVisitante() {
        return visitante;
    }
    private void setGolesL(int golesLAux) {
        this.golesL = golesLAux;
    }
    public int getGolesL() {
        return golesL;
    }
    private void setGolesV(int golesVAux) {
        this.golesV = golesVAux;
    }
    public int getGolesV() {
        return golesV;
    }
    public void actualizarResultado(int golesLAux, int golesVAux) {
        setGolesL(golesLAux);
        setGolesV(golesVAux);
        calcularPuntos();
    }
    public void calcularPuntos() {
        if(getGolesL() > getGolesV()) {
            getLocal().ganarPartido(getGolesL(), getGolesV());
            getVisitante().perderPartido(getGolesV(), getGolesL());
        }
        else if(getGolesL() < getGolesV()) {
            getLocal().perderPartido(getGolesL(), getGolesV());
            getVisitante().ganarPartido(getGolesV(), getGolesL());
        }
        else {
            getLocal().empatarPartido(getGolesL(), getGolesV());
            getVisitante().empatarPartido(getGolesV(), getGolesL());
        }
    }
    @Override
    public String toString() {
        return String.format("%25s", getLocal().getNombre()) + " [" + getGolesL() + "] - [" + getGolesV() + "] " + getVisitante().getNombre();
    }
}

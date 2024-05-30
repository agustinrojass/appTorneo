package clases;
import java.io.Serializable;
import java.util.ArrayList;

public class Fecha implements Serializable {
    //Atributos
    private int numeroFecha;
    private ArrayList<PartidoFutbol> partidos;
    //Constructores
    public Fecha() {
        setNumeroFecha(0);
        setPartidos(new ArrayList<>());
    }
    public Fecha(int numeroFechaAux) {
        setNumeroFecha(numeroFechaAux);
        setPartidos(new ArrayList<>());
    }
    //Metodos
    private void setNumeroFecha(int numeroFechaAux) {
        this.numeroFecha = numeroFechaAux;
    }
    public int getNumeroFecha() {
        return numeroFecha;
    }
    private void setPartidos(ArrayList<PartidoFutbol> partidosAux) {
        this.partidos = partidosAux;
    }
    public ArrayList<PartidoFutbol> getPartidos() {
        return partidos;
    }
    public void agregarPartido(Equipo local, Equipo visitante) {
        getPartidos().add(new PartidoFutbol(local, visitante));
    } //probablemente no se use
    public void sacarPartido(Equipo local, Equipo visitante){
        getPartidos().remove(devolverIndicePartido(local,visitante));
    } //probablemente no se use
    private int devolverIndicePartido(Equipo local, Equipo visitante) {
        int rta = -1;
        for(int i = 0; i < getPartidos().size() && rta == -1; i++) {
            if(getPartidos().get(i).getLocal().equals(local) && getPartidos().get(i).getVisitante().equals(visitante)) {
                rta = i;
            }
        }
        return rta;
    } //probablemente no se use
    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < getPartidos().size(); i++) {
            s += getPartidos().get(i) + "\n";
        }
        return s;
    }
}

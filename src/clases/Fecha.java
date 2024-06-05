package clases;
import java.io.Serializable;
public class Fecha implements Serializable {
    //Atributos
    private int numeroFecha;
    private Contenedor<PartidoFutbol> partidos;
    //Constructores
    public Fecha(int numeroFechaAux, Contenedor<PartidoFutbol> partidosFecha) {
        this.numeroFecha = numeroFechaAux;
        this.partidos = partidosFecha;
    }
    //Metodos
    public Contenedor<PartidoFutbol> getPartidos() {
        return partidos;
    }
    public int getNumeroFecha() {
        return numeroFecha;
    }
    @Override
    public String toString() {
        String s = "\u001B[30;100m " + String.format("%-88s", " Fecha: " + numeroFecha) + "\u001B[0m\n";
        s += "\u001B[30;47m" + "                                      Local - Visitante                                  " + "\u001B[0m\n";
        for(int i = 0; i < partidos.size(); i++) {
            if(i % 2 == 0) {
                s += "\u001B[30;100m     " + partidos.get(i).toString() + "\u001B[0m\n";
            }
            else {
                s += "\u001B[30;47m     " + partidos.get(i).toString() + "\u001B[0m\n";
            }
        }
        return s;
    }
}
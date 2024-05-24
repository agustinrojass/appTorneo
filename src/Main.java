import Clases.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //fixed();

        Torneo torneo = new Torneo("Liga Profesional de Futbol 2024");
        ArrayList<Equipo> equipos = new ArrayList<>();
        agregarEquiposMenu(torneo, equipos);
        System.out.println(torneo);
    }
    public static void agregarEquiposMenu(Torneo torneo, ArrayList<Equipo> equipos) {
        Equipo argentinos = new Equipo("Argentinos");
        equipos.add(argentinos);
        Equipo atleticoTucuman = new Equipo("Atletico Tucuman");
        equipos.add(atleticoTucuman);
        Equipo banfield = new Equipo("Banfield");
        equipos.add(banfield);
        Equipo barracasCentral = new Equipo("Barracas Central");
        equipos.add(barracasCentral);
        Equipo belgrano = new Equipo("Belgrano");
        equipos.add(belgrano);
        Equipo bocaJuniors = new Equipo("Boca Juniors");
        equipos.add(bocaJuniors);
        Equipo centralCordobaSdE = new Equipo("Central Cordoba (SdE)");
        equipos.add(centralCordobaSdE);
        Equipo defensaJusticia = new Equipo("Defensa y Justicia");
        equipos.add(defensaJusticia);
        Equipo estudiantesLP = new Equipo("Estudiantes (LP)");
        equipos.add(estudiantesLP);
        Equipo gimnasiaLP = new Equipo("Gimnasia (LP)");
        equipos.add(gimnasiaLP);
        Equipo godoyCruz = new Equipo("Godoy Cruz");
        equipos.add(godoyCruz);
        Equipo huracan = new Equipo("Huracan");
        equipos.add(huracan);
        Equipo independiente = new Equipo("Independiente");
        equipos.add(independiente);
        Equipo independienteRivadavia = new Equipo("Independiente Rivadavia");
        equipos.add(independienteRivadavia);
        Equipo instituto = new Equipo("Instituto");
        equipos.add(instituto);
        Equipo lanus = new Equipo("Lanus");
        equipos.add(lanus);
        Equipo newells = new Equipo("Newells");
        equipos.add(newells);
        Equipo platense = new Equipo("Platense");
        equipos.add(platense);
        Equipo racing = new Equipo("Racing");
        equipos.add(racing);
        Equipo riestra = new Equipo("Riestra");
        equipos.add(riestra);
        Equipo riverPlate = new Equipo("River Plate");
        equipos.add(riverPlate);
        Equipo rosarioCentral = new Equipo("Rosario Central");
        equipos.add(rosarioCentral);
        Equipo sanLorenzo = new Equipo("San Lorenzo");
        equipos.add(sanLorenzo);
        Equipo sarmientoJ = new Equipo("Sarmiento (J)");
        equipos.add(sarmientoJ);
        Equipo talleresC = new Equipo("Talleres (C)");
        equipos.add(talleresC);
        Equipo tigre = new Equipo("Tigre");
        equipos.add(tigre);
        Equipo union = new Equipo("Union");
        equipos.add(union);
        Equipo velez = new Equipo("Velez");
        equipos.add(velez);
        for(int i = 0; i < equipos.size(); i++) {
            torneo.agregarEquipo(equipos.get(i));
        }
    }
    public static int devolverIndiceEquipo(ArrayList<Equipo> equipos, String e) {
        int rta = -1;
        for(int i = 0; i < equipos.size() && rta == -1; i++) {
            if(equipos.get(i).getNombre().equalsIgnoreCase(e)) {
                rta = i;
            }
        }
        return rta;
    }
    public static void fixed() {
        ArrayList<Equipo> tabla = new ArrayList<>();
        Equipo argentinos = new Equipo("Argentinos");
        tabla.add(argentinos);
        Equipo atleticoTucuman = new Equipo("Atletico Tucuman");
        tabla.add(atleticoTucuman);
        Equipo banfield = new Equipo("Banfield");
        tabla.add(banfield);
        Equipo barracasCentral = new Equipo("Barracas Central");
        tabla.add(barracasCentral);
        Equipo belgrano = new Equipo("Belgrano");
        tabla.add(belgrano);
        Equipo bocaJuniors = new Equipo("Boca Juniors");
        tabla.add(bocaJuniors);
        Equipo centralCordobaSdE = new Equipo("Central Cordoba (SdE)");
        tabla.add(centralCordobaSdE);
        Equipo defensaJusticia = new Equipo("Defensa y Justicia");
        tabla.add(defensaJusticia);
        Equipo estudiantesLP = new Equipo("Estudiantes (LP)");
        tabla.add(estudiantesLP);
        Equipo gimnasiaLP = new Equipo("Gimnasia (LP)");
        tabla.add(gimnasiaLP);
        Equipo godoyCruz = new Equipo("Godoy Cruz");
        tabla.add(godoyCruz);
        Equipo huracan = new Equipo("Huracan");
        tabla.add(huracan);
        Equipo independiente = new Equipo("Independiente");
        tabla.add(independiente);
        Equipo independienteRivadavia = new Equipo("Independiente Rivadavia");
        tabla.add(independienteRivadavia);
        Equipo instituto = new Equipo("Instituto");
        tabla.add(instituto);
        Equipo lanus = new Equipo("Lanus");
        tabla.add(lanus);
        Equipo newells = new Equipo("Newells");
        tabla.add(newells);
        Equipo platense = new Equipo("Platense");
        tabla.add(platense);
        Equipo racing = new Equipo("Racing");
        tabla.add(racing);
        Equipo riestra = new Equipo("Riestra");
        tabla.add(riestra);
        Equipo riverPlate = new Equipo("River Plate");
        tabla.add(riverPlate);
        Equipo rosarioCentral = new Equipo("Rosario Central");
        tabla.add(rosarioCentral);
        Equipo sanLorenzo = new Equipo("San Lorenzo");
        tabla.add(sanLorenzo);
        Equipo sarmientoJ = new Equipo("Sarmiento (J)");
        tabla.add(sarmientoJ);
        Equipo talleresC = new Equipo("Talleres (C)");
        tabla.add(talleresC);
        Equipo tigre = new Equipo("Tigre");
        tabla.add(tigre);
        Equipo union = new Equipo("Union");
        tabla.add(union);
        Equipo velez = new Equipo("Velez");
        tabla.add(velez);

        Fecha fecha1 = new Fecha(1);
        fecha1.agregarPartido(sarmientoJ, instituto);
        fecha1.agregarPartido(argentinos, rosarioCentral);
        fecha1.agregarPartido(newells, platense);
        fecha1.agregarPartido(huracan, defensaJusticia);
        fecha1.agregarPartido(godoyCruz, barracasCentral);
        fecha1.agregarPartido(independiente, talleresC);
        fecha1.agregarPartido(riverPlate, centralCordobaSdE);
        fecha1.agregarPartido(riestra, sanLorenzo);
        fecha1.agregarPartido(tigre, estudiantesLP);
        fecha1.agregarPartido(belgrano, racing);
        fecha1.agregarPartido(lanus, independienteRivadavia);
        fecha1.agregarPartido(atleticoTucuman, bocaJuniors);
        fecha1.agregarPartido(gimnasiaLP, velez);
        fecha1.agregarPartido(union, banfield);

        Fecha fecha2 = new Fecha(2);
        fecha2.agregarPartido(independienteRivadavia, godoyCruz);
        fecha2.agregarPartido(riverPlate, belgrano);
        fecha2.agregarPartido(defensaJusticia, gimnasiaLP);
        fecha2.agregarPartido(instituto, union);
        fecha2.agregarPartido(barracasCentral, sarmientoJ);
        fecha2.agregarPartido(platense, independiente);
        fecha2.agregarPartido(banfield, huracan);
        fecha2.agregarPartido(talleresC, atleticoTucuman);
        fecha2.agregarPartido(centralCordobaSdE, bocaJuniors);
        fecha2.agregarPartido(estudiantesLP, riestra);
        fecha2.agregarPartido(sanLorenzo, lanus);
        fecha2.agregarPartido(racing, argentinos);
        fecha2.agregarPartido(rosarioCentral, tigre);
        fecha2.agregarPartido(velez, newells);

        fecha1.getPartidos().get(0).actualizarResultado(1, 2);
        fecha1.getPartidos().get(1).actualizarResultado(3, 2);
        fecha1.getPartidos().get(2).actualizarResultado(2, 0);
        fecha1.getPartidos().get(3).actualizarResultado(3, 1);
        fecha1.getPartidos().get(4).actualizarResultado(0, 1);
        fecha1.getPartidos().get(5).actualizarResultado(1, 3);
        fecha1.getPartidos().get(6).actualizarResultado(3, 0);
        fecha1.getPartidos().get(7).actualizarResultado(1, 0);
        fecha1.getPartidos().get(8).actualizarResultado(0, 1);
        fecha1.getPartidos().get(9).actualizarResultado(4, 4);
        fecha1.getPartidos().get(10).actualizarResultado(0, 2);
        fecha1.getPartidos().get(11).actualizarResultado(1, 0);
        fecha1.getPartidos().get(12).actualizarResultado(3, 1);
        fecha1.getPartidos().get(13).actualizarResultado(1, 0);

        fecha2.getPartidos().get(0).actualizarResultado(0, 0);
        fecha2.getPartidos().get(1).actualizarResultado(3, 0);
        fecha2.getPartidos().get(2).actualizarResultado(1, 1);
        fecha2.getPartidos().get(3).actualizarResultado(1, 1);
        fecha2.getPartidos().get(4).actualizarResultado(1, 1);
        fecha2.getPartidos().get(5).actualizarResultado(0, 0);
        fecha2.getPartidos().get(6).actualizarResultado(1, 1);
        fecha2.getPartidos().get(7).actualizarResultado(2, 0);
        fecha2.getPartidos().get(8).actualizarResultado(2, 4);
        fecha2.getPartidos().get(9).actualizarResultado(2, 0);
        fecha2.getPartidos().get(10).actualizarResultado(1, 1);
        fecha2.getPartidos().get(11).actualizarResultado(3, 0);
        fecha2.getPartidos().get(12).actualizarResultado(1, 1);
        fecha2.getPartidos().get(13).actualizarResultado(1, 0);

        mostrarTabla(tabla);




    }
    public static void mostrarTabla(ArrayList<Equipo> tabla) {
        System.out.println("\u001B[30;100m" + " Pos | Equipo                    | Pts | PJ | PG | PE | PP | GF | GC | Dif " + "\u001B[0m");
        tabla.sort(Equipo::compareTo);//ordena la tabla (lista)
        System.out.println("\u001B[30;42m" + "   1 | " + tabla.getFirst() + "\u001B[0m");
        for(int i = 1; i < tabla.size(); i++) {
            if(i % 2 == 0) {
                System.out.print("\u001B[30;100m");
            }
            else {
                System.out.print("\u001B[30;47m");
            }
            System.out.println("  " + String.format("%2s", (i + 1)) + " | " + tabla.get(i) + "\u001B[0m");
        }
    }
}

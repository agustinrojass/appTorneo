package Clases;

public class Tecnico extends Persona {
    //Atributos
    private String formacionFavorita;
    //Constructores
    public Tecnico() {
        super();
        setFormacionFavorita("");
    }
    public Tecnico(String nombreAux, int edadAux, String formacionFavoritaAux) {
        super(nombreAux, edadAux);
        setFormacionFavorita(formacionFavoritaAux);
    }
    //Metodos
    private void setFormacionFavorita(String formacionFavoritaAux) {
        this.formacionFavorita = formacionFavoritaAux;
    }
    private String getFormacionFavorita() {
        return formacionFavorita;
    }
    @Override
    public String toString() {
        return super.toString() + " - " + getFormacionFavorita();
    }
}

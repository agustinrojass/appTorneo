package excepciones;
public class DescargaCanceladaException extends Exception {
    //Atributos
    private final String motivo;
    //Constructores
    public DescargaCanceladaException(String motivoAux) {
        super();
        this.motivo = motivoAux;
    }
    //Metodos
    public String getMessage() {
        return motivo;
    }
}
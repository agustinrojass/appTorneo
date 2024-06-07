package excepciones;
public class CargaCanceladaException extends Exception{
    //Atributos
    private final String motivo;
    //Constructores
    public CargaCanceladaException(String motivoAux) {
        super();
        this.motivo = motivoAux;
    }
    //Metodos
    @Override
    public String getMessage() {
        return motivo;
    }
}

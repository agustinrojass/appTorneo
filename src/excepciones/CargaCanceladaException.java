package excepciones;
public class CargaCanceladaException extends Exception{
    //Atributos
    private final String motivo;
    //Constructores
    public CargaCanceladaException(String motivoaux) {
        super();
        this.motivo = motivoaux;
    }
    //Metodos
    public String getMotivo() {
        return motivo;
    }
}

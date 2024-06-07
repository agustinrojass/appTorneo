package excepciones;
public class DescargaCanceladaException extends Exception {
    //Atributos
    private final String motivo;
    //Constructores
    public DescargaCanceladaException(String motivoaux) {
        super();
        this.motivo = motivoaux;
    }
    //Metodos
    public String getMotivo() {
        return motivo;
    }
}
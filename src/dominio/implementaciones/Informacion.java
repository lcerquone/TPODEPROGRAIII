package dominio.implementaciones;

public class Informacion {
    private double kms;
    private int minutos;
    private String origen;
    private String destino;

    public Informacion( String origen, String destino, double kms, int minutos) {
        this.kms = kms;
        this.minutos = minutos;
        this.origen = origen;
        this.destino = destino;
    }

    public double getKms() {
        return kms;
    }

    public int getMinutos() {
        return minutos;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }
}

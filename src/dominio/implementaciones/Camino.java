package dominio.implementaciones;

public class Camino {
    private double kms;
    private int minutos;

    public Camino(double kms, int minutos) {
        this.kms = kms;
        this.minutos = minutos;
    }

    public double getKms() {
        return kms;
    }

    public int getMinutos() {
        return minutos;
    }
}

package dominio.implementaciones;

import java.time.*;

public class Horario {
    private String lugar;
    private LocalTime fechaFin;
    private LocalTime fechaInicio;

    public Horario(String lugar, LocalTime fechaInicio, LocalTime fechaFin) {
        this.lugar = lugar;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
    }

    public String getLugar() {
        return lugar;
    }

    public LocalTime getFechaFin() {
        return fechaFin;
    }

    public LocalTime getFechaInicio() {
        return fechaInicio;
    }
}

package es.ieslavereda.ejerciciocomunicaciones;

public class Usuario {
    private String nombre;
    private int edad;
    private String actividad;

    public Usuario(String nombre, int edad, String actividad) {
        this.nombre = nombre;
        this.edad = edad;
        this.actividad = actividad;
    }

    @Override
    public String toString() {
        return nombre + " - " + edad + " - " + actividad;
    }
}

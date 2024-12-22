package main.java.com.example.model;

public class Gasto {
    private int id;
    private String descripcion;
    private long fecha;
    private double monto;

    // Constructor
    public Gasto(int id, String descripcion, long fecha, double monto) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.monto = monto;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
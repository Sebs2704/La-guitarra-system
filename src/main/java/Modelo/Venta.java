package Modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Venta {
    private int id;
    private int idCliente;
    private String nombreVendedor;
    private LocalDateTime fechaVenta;
    private double total;
    private List<DetalleVenta> detalles;

    public Venta(int idCliente, String nombreVendedor, LocalDateTime fechaVenta, double total) {
        this.idCliente = idCliente;
        this.nombreVendedor = nombreVendedor;
        this.fechaVenta = fechaVenta;
        this.total = total;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombreVendedor() { return nombreVendedor; }
    public void setNombreVendedor(String nombreVendedor) { this.nombreVendedor = nombreVendedor; }

    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles) { this.detalles = detalles; }
}

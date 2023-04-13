package tienda;

import java.rmi.*;
import java.rmi.server.*;

class ProductoImpl extends UnicastRemoteObject implements Producto {
    
    private int id;
    private String codigo_referencia;
    private String marca;
    private String talla;
    private String color;
    private String tipo;
    private String modelo;
    private int cantidad;

    ProductoImpl() throws RemoteException {
    }

    ProductoImpl(
        int id, String codigo_referencia, 
        String marca, String talla, String color, 
        String tipo, String modelo, int cantidad 
    ) throws RemoteException {
        
        this.id = id;
        this.codigo_referencia = codigo_referencia;
        this.marca = marca;
        this.talla = talla;
        this.color = color;
        this.tipo = tipo;
        this.modelo = modelo;
        this.cantidad = cantidad;

    }

    public String getCodigoReferencia() {
        return codigo_referencia;
    }

    public void setCodigoReferencia(String codigo_referencia) {
        this.codigo_referencia = codigo_referencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}


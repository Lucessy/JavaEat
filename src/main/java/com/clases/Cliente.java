package com.clases;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cada instancia de esta clase almacena la información de un cliente
 * 
 */
public abstract class Cliente implements Serializable {
    
    // Atributos
    
    private String nombre;
    private Direccion direccion;
    private Tarjeta tarjeta;
    private String telefono;
    private String correo;
    private String clave;
    private int id;
    private CarritoCompra carrito;
    
    

    // Métodos
    /**
     * Valida el correo
     * @param correo
     * @return booleano si es válido el correo
     */
    public boolean correoValido(String correo){
        if(!correo.contains("@")){
	    return false;
	}
	String[] split = correo.split("@");
	if(split.length<2){
	    return  false;
	}else{
	    return true;
	}
    }
    

    // Constructores
    /**
     * Constructor del cliente
     * @param nombre nombre del cliente
     * @param direccion direccion del cliente
     * @param telefono telefono del cliente
     * @param correo correo electronico del cliente
     * @param clave clave de acceso del cliente
     * @param id numero de identificación del cliente
     */
    public Cliente(String nombre, Direccion direccion, String telefono, String correo, String clave, int id) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tarjeta = null;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
        this.id = id;
        this.carrito = new CarritoCompra();
        
    }

    public CarritoCompra getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoCompra carrito) {
        this.carrito = carrito;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    //toString

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", direccion=" + direccion + ", tarjeta=" + tarjeta + ", telefono=" + telefono + ", correo=" + correo + ", clave=" + clave + '}';
    }
    
    
}

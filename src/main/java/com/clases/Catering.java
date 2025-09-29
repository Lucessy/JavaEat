package com.clases;

import java.io.Serializable;

/**
 * Cada instancia de esta clase almacena en booleanos los distintos tipos de catering de un restaurnate
 * Es serializable
 * 
 */
public class Catering implements Serializable{

    // Atributos
    private String comidaAProporcionar;
    private boolean camareros;
    private boolean cocineros;
    private boolean decoracion;
    private boolean espacioPrivado;
    private double precioTotal;
    private double precioComida;

    // Métodos
    /**
     * Retorna el precio del catering deseado
     * @param nombre del servicio de catering
     * @return el precio de los servicios de catering dado el nombre
     */
    public double getPrecioServicios(String nombre) {
        if ("Camareros".equals(nombre)) {
            return 800.0;
        } else if ("Cocineros".equals(nombre)) {
            return 1000.0;
        } else if ("Decoración".equals(nombre)) {
            return 500.0;
        } else if ("Espacio".equals(nombre)) {
            return 1000.0;
        } else {
            return precioComida;
        }

    }
    /**
     * Retorna el precio de los Camareros
     * @return 
     */
    public double getPrecioCamareros() {
        return 800.0;
    }
    /**
     * Retorna el precio de los Cocineros
     * @return 
     */
    public double getPrecioCocineros() {
        return 1000.0;
    }
    /**
     * Retorna el precio de la decoración
     * @return 
     */
    public double getPrecioDecoracion() {
        return 500.0;
    }
    /**
     * Retorna el precio del Espacio privado
     * @return 
     */
    public double getPrecioEspacio() {
        return 1000.0;
    }

    // Constructor
    /**
     * Constructor del catering
     * @param comidaAProporcionar comida a aportar por el restaurante
     * @param precioComida precio de la comida a aportar
     * @param camareros dispone de camareros
     * @param cocineros dispone de cocineros
     * @param decoracion dispone de decoración
     * @param espacioPrivado dispone de espacio privado
     */
    public Catering(String comidaAProporcionar, double precioComida, boolean camareros, boolean cocineros, boolean decoracion, boolean espacioPrivado) {
        this.comidaAProporcionar = comidaAProporcionar;
        this.precioComida = precioComida;
        this.camareros = camareros;
        this.cocineros = cocineros;
        this.decoracion = decoracion;
        this.espacioPrivado = espacioPrivado;
    }
    
    /**
     * Constructor del catering sin comida a proporcionar
     * @param camareros
     * @param cocineros
     * @param decoracion
     * @param espacioPrivado
     * 
     */
    public Catering(boolean camareros, boolean cocineros, boolean decoracion, boolean espacioPrivado) {
        this.camareros = camareros;
        this.cocineros = cocineros;
        this.decoracion = decoracion;
        this.espacioPrivado = espacioPrivado;
        this.comidaAProporcionar = " ";
        this.precioComida = 0;
    }

    public double getPrecioComida() {
        return precioComida;
    }

    public void setPrecioComida(double precioComida) {
        this.precioComida = precioComida;
    }

    public String getComidaAProporcionar() {
        return comidaAProporcionar;
    }

    public void setComidaAProporcionar(String comidaAProporcionar) {
        this.comidaAProporcionar = comidaAProporcionar;
    }

    public boolean isCamareros() {
        return camareros;
    }

    public void setCamareros(boolean camareros) {
        this.camareros = camareros;
    }

    public boolean isCocineros() {
        return cocineros;
    }

    public void setCocineros(boolean cocineros) {
        this.cocineros = cocineros;
    }

    public boolean isDecoracion() {
        return decoracion;
    }

    public void setDecoracion(boolean decoracion) {
        this.decoracion = decoracion;
    }

    public boolean isEspacioPrivado() {
        return espacioPrivado;
    }

    public void setEspacioPrivado(boolean espacioEmpresa) {
        this.espacioPrivado = espacioEmpresa;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

}

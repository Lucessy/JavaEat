package com.clases;

import java.io.Serializable;

/**
 * Clase que representa una tarjeta.
 */
public class Tarjeta implements Serializable {

    // Atributos
    private String titular;
    private String numTarjeta;
    private String fechaCaducidad;

    /**
     * Constructor de la clase Tarjeta.
     *
     * @param titular       El titular de la tarjeta
     * @param numTarjeta    El número de la tarjeta
     * @param fechaCaducidad La fecha de caducidad de la tarjeta
     */
    public Tarjeta(String titular, String numTarjeta, String fechaCaducidad) {
        this.titular = titular;
        this.numTarjeta = numTarjeta;
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * Devuelve el titular de la tarjeta.
     *
     * @return El titular de la tarjeta
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Establece el titular de la tarjeta.
     *
     * @param titular El titular de la tarjeta
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * Devuelve el número de la tarjeta.
     *
     * @return El número de la tarjeta
     */
    public String getNumTarjeta() {
        return numTarjeta;
    }

    /**
     * Establece el número de la tarjeta.
     *
     * @param numTarjeta El número de la tarjeta
     */
    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    /**
     * Devuelve la fecha de caducidad de la tarjeta.
     *
     * @return La fecha de caducidad de la tarjeta
     */
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * Establece la fecha de caducidad de la tarjeta.
     *
     * @param fechaCaducidad La fecha de caducidad de la tarjeta
     */
    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

}

package com.clases;

import java.io.Serializable;

/**
 * Cada instancia de esta clase almacena la información de la dirección de un cliente.
 * Es serializable.
 */
public class Direccion implements Serializable {
    
    // Atributos
    private String calle;
    private String numero;
    private String codigoPostal;
    private String ciudad;
    

    // Métodos
    /**
     * Valida el código postal dado.
     * 
     * @param codPostal El código postal a validar.
     * @return true si el código postal es válido, false de lo contrario.
     */
    public boolean validarCodPostal(String codPostal) {
        if (codPostal.matches("\\d+") && codPostal.length() == 5) {
            return true;
        }
        return false;
    }

    // Constructores
    /**
     * Constructor de la clase Direccion.
     * 
     * @param calle La calle de la dirección.
     * @param numero El número de la dirección.
     * @param codigoPostal El código postal de la dirección.
     * @param ciudad La ciudad de la dirección.
     */
    public Direccion(String calle, String numero, String codigoPostal, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
    }

    /**
     * Devuelve la calle de la dirección.
     * 
     * @return La calle de la dirección.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece la calle de la dirección.
     * 
     * @param calle La calle de la dirección.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Devuelve el código postal de la dirección.
     * 
     * @return El código postal de la dirección.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Establece el código postal de la dirección.
     * 
     * @param codigoPostal El código postal de la dirección.
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Devuelve la ciudad de la dirección.
     * 
     * @return La ciudad de la dirección.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad de la dirección.
     * 
     * @param ciudad La ciudad de la dirección.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Devuelve el número de la dirección.
     * 
     * @return El número de la dirección.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de la dirección.
     * 
     * @param numero El número de la dirección.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}

package com.clases;

import java.io.Serializable;

/**
 * Esta clase representa una empresa que es cliente.
 * Hereda de la clase Cliente.
 */
public class Empresa extends Cliente implements Serializable {
    
    // Atributos
    private String cif;
    private String web;

    // Constructores

    /**
     * Constructor de la clase Empresa.
     * 
     * @param cif El CIF de la empresa.
     * @param web La página web de la empresa.
     * @param nombre El nombre de la empresa.
     * @param direccion La dirección de la empresa.
     * @param telefono El número de teléfono de la empresa.
     * @param correo El correo electrónico de la empresa.
     * @param clave La clave de acceso de la empresa.
     * @param id El ID de la empresa.
     */
    public Empresa(String cif, String web, String nombre, Direccion direccion, String telefono, String correo, String clave, int id) {
        super(nombre, direccion, telefono, correo, clave, id);
        this.cif = cif;
        this.web = web;
    }

    // Métodos

    /**
     * Devuelve el CIF de la empresa.
     * 
     * @return El CIF de la empresa.
     */
    public String getCif() {
        return cif;
    }

    /**
     * Establece el CIF de la empresa.
     * 
     * @param cif El CIF de la empresa.
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Devuelve la página web de la empresa.
     * 
     * @return La página web de la empresa.
     */
    public String getWeb() {
        return web;
    }

    /**
     * Establece la página web de la empresa.
     * 
     * @param web La página web de la empresa.
     */
    public void setWeb(String web) {
        this.web = web;
    }
}

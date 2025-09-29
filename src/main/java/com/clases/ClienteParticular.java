package com.clases;

import java.io.Serializable;

/**
 * Heredada de la clase Cliente, cada instancia almacena la información de un cliente de tipo particular.
 * Es serializable
 * 
 */
public class ClienteParticular extends Cliente implements Serializable{
    
    // Atributos
    private String dni;
    private String apellido;

    // Métodos


    // Constructores
    /**
     * Constructor del cliente particular
     * @param dni dni del particular
     * @param apellido apellido del particular
     * @param nombre nombre del particular
     * @param direccion dirección del particular
     * @param telefono telefono del particular
     * @param correo correo eléctronico del particular
     * @param clave clave de acceso del particular
     * @param id número de identificación del particular
     */
    public ClienteParticular(String dni, String apellido, String nombre, Direccion direccion, String telefono, String correo, String clave, int id) {
        super(nombre, direccion, telefono, correo, clave, id);
        this.dni = dni;
        this.apellido = apellido;
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
    
}

package com.clases;

import java.io.Serializable;

/**
 * Cada instancia de esta clase almacena la información de una opinión de un cliente sobre un producto
 * Es serializable
 */
public class Opinion implements Serializable{

    private String titulo;
    private int calificacion;
    private String comentario;

    /**
     * Constructor de una opinión
     * @param titulo El título de la opinión 
     * @param calificacion La calificación del 1 al 5
     * @param comentario El comentario de la opinión
     */
    public Opinion(String titulo, int calificacion, String comentario) {
	try {
	    if (calificacion > 5 || calificacion < 1) {
		throw new Exception("Calificación no contenida entre 1 y 5.");
	    }
	} catch (Exception e) {
	    System.out.println("Error: " + e.getMessage());
	}
	this.titulo = titulo;
	this.calificacion = calificacion;
	this.comentario = comentario;
    }

    /**
     * Devuelve el título de la opinión 
     *
     * @return título de la opinión 
     */
    public String getTitulo() {
	return titulo;
    }

    /**
     * Establece el título de la opinión 
     *
     * @param titulo El nuevo título de la opinión 
     */
    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    /**
     * Devuelve el comentario de la opinión
     *
     * @return el comentario de la opinión
     */
    public String getComentario() {
	return comentario;
    }

    /**
     * Establece el comentario de la opinión
     *
     * @param comentario El nuevo comentario de la opinión
     */
    public void setComentario(String comentario) {
	this.comentario = comentario;
    }

    /**
     * Devuelve la calificación del 1 al 5
     *
     * @return the value of calificacion
     */
    public int getCalificacion() {
	return calificacion;
    }

    /**
     * Establece la calificación del 1 al 5
     *
     * @param calificacion La nueva calificación del 1 al 5
     */
    public void setCalificacion(int calificacion) {
	this.calificacion = calificacion;
    }

}

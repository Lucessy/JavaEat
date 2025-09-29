package com.clases;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Cada instancia de Comida almacena información de una comida de un restaurante
 * 
 */
public class Comida implements Serializable{

    // Atributos
    private String titulo;
    private Double precioVenta;
    private String nombreFotografia;
    private String ingredientes;
    private int cantidad;
    private int id;

    // Métodos
    /**
     * Hace una copia profunda de otro objeto tipo Comida
     * @param copyMe Comida que se quiere copiar
     */
        public Comida(Comida copyMe) {
            this.titulo = copyMe.titulo;
            this.precioVenta = copyMe.precioVenta;
            this.nombreFotografia = copyMe.nombreFotografia;
            this.ingredientes = copyMe.ingredientes;
            this.cantidad = copyMe.cantidad;
            this.id = copyMe.id;
        }

    /**
     * Devuelve la imagen encontrada en el path dado reescalada al tamaño dado,
     * o null si no la encuentra. Si el path es de la forma "/images/image.png"
     * entonces la buscará en los recursos del proyecto. En caso contrario la
     * buscará en el equipo
     *
     * @param width La nueva anchura de la imagen
     * @param height La nueva altura de la imagen
     * @param path El path de la imagen en los recursos del proyecto o en el
     * directorio del equipo
     * @return La nueva imagen reescalada como ImageIcon
     */
    public static ImageIcon resizedIcon(int width, int height, String path) {
        if (width == 0) {
            width = 330;
        }
        if (height == 0) {
            height = 330;
        }
        BufferedImage img;
        ImageIcon imageIcon = null;
        try {
            img = ImageIO.read(new File(path));
            Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(dimg);
        } catch (IOException e) {
            try {
                ImageIcon imagen = new ImageIcon(Restaurante.class.getResource(path));
                imageIcon = new ImageIcon(imagen.getImage().getScaledInstance(width, height, 1));
            } catch (Exception error) {
                System.out.println("Error: ninguna imagen encontrada con path:" + path);
            }
        }

        return imageIcon;
    }


    // Constructores
    /**
     * Constructor de Comida
     * @param titulo titulo de la comida
     * @param precioVenta pecio de la comida
     * @param nombreFotografia ruta de la imagen de la comida
     * @param ingredientes ingredientes que incluye la cómida
     * @param id número de identificación de la comida
     */
    public Comida(String titulo, Double precioVenta, String nombreFotografia, String ingredientes, int id) {
        this.titulo = titulo;
        this.precioVenta = precioVenta;
        this.nombreFotografia = nombreFotografia;
        this.ingredientes = ingredientes;
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getNombreFotografia() {
        return nombreFotografia;
    }

    public void setNombreFotografia(String nombreFotografia) {
        this.nombreFotografia = nombreFotografia;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

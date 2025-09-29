package com.clases;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Clase que representa un restaurante.
 */
public final class Restaurante {

    // Atributos
    private int id;
    private String cif;
    private String nombre;
    private Direccion direccion;
    private String especialidad;
    private double gastosEnvio;
    private int tiempoEnvio;
    private Catering catering;
    private boolean ofreceCatering;
    private ArrayList<Comida> comidas;
    private String nombreFotografia;
    private double calificacionMedia;
    private ArrayList<Integer> calificaciones;
    private ArrayList<Menu> menus;
    private ArrayList<Opinion> opiniones;

    // Métodos
    /**
     * Calcula la calificación media del restaurante.
     */
    private void calculaCalificacionMedia() {
        double calificacion = 0.0;
        for (Opinion opinion : opiniones) {
            calificacion += opinion.getCalificacion();
        }
        BigDecimal bd = new BigDecimal(calificacion / opiniones.size());
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.calificacionMedia = bd.doubleValue();
    }

    /**
     * Devuelve la comida con el ID dado o null si no existe.
     *
     * @param id El ID de la comida.
     * @return La comida con el ID dado, si existe.
     */
    public Comida obtenerComida(int id) {
        for (Comida comida : comidas) {
            if (comida.getId() == id) {
                return comida;
            }
        }
        return null;
    }

    /**
     * Devuelve una imagen reescalada al tamaño dado o null si no se encuentra.
     *
     * @param width  La nueva anchura de la imagen.
     * @param height La nueva altura de la imagen.
     * @param path   El path de la imagen en los recursos del proyecto o en el directorio del equipo.
     * @return La nueva imagen reescalada como ImageIcon.
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
                System.out.println("Error: ninguna imagen encontrada con path: " + path);
            }
        }
        return imageIcon;
    }

    /**
     * Obtiene un ID disponible para una nueva comida.
     *
     * @return Un ID disponible para una nueva comida.
     */
    public int getAvailableIdComida() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Comida comida : comidas) {
            ids.add(comida.getId());
        }
        int i = 1;
        while (true) {
            if (!ids.contains(i)) {
                return i;
            }
            i++;
        }
    }

    /**
     * Agrega una opinión al restaurante.
     *
     * @param op La opinión a agregar.
     */
    public void addOpinion(Opinion op) {
        opiniones.add(op);
    }
    
      // Constructores
      /**
     * Constructor de la clase Restaurante con parámetros completos.
     *
     * @param id               El ID del restaurante.
     * @param cif              El CIF del restaurante.
     * @param nombre           El nombre del restaurante.
     * @param direccion        La dirección del restaurante.
     * @param especialidad     La especialidad del restaurante.
     * @param gastosEnvio      Los gastos de envío del restaurante.
     * @param tiempoEnvio      El tiempo de envío del restaurante.
     * @param ofreceCatering   Indica si el restaurante ofrece servicio de catering.
     * @param catering         El catering asociado al restaurante.
     * @param comidas          La lista de comidas disponibles en el restaurante.
     * @param menus            La lista de menús disponibles en el restaurante.
     * @param nombreFotografia El nombre de la fotografía del restaurante.
     */
    public Restaurante(int id, String cif, String nombre, Direccion direccion, String especialidad, double gastosEnvio, int tiempoEnvio, boolean ofreceCatering, Catering catering, ArrayList<Comida> comidas, ArrayList<Menu> menus, String nombreFotografia) {
        this.id = id;
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.especialidad = especialidad;
        this.gastosEnvio = gastosEnvio;
        this.tiempoEnvio = tiempoEnvio;
        this.setCatering(catering);
        this.ofreceCatering = ofreceCatering;
        this.comidas = comidas;
        this.catering = catering;
        this.nombreFotografia = nombreFotografia;
        this.calificaciones = new ArrayList<>();
        this.calificacionMedia = 0.0;
        this.menus = menus;
        this.opiniones = new ArrayList<>();
    }

    /**
     * Constructor de la clase Restaurante sin la lista de menús.
     *
     * @param id               El ID del restaurante.
     * @param cif              El CIF del restaurante.
     * @param nombre           El nombre del restaurante.
     * @param direccion        La dirección del restaurante.
     * @param especialidad     La especialidad del restaurante.
     * @param gastosEnvio      Los gastos de envío del restaurante.
     * @param tiempoEnvio      El tiempo de envío del restaurante.
     * @param ofreceCatering   Indica si el restaurante ofrece servicio de catering.
     * @param catering         El catering asociado al restaurante.
     * @param comidas          La lista de comidas disponibles en el restaurante.
     * @param nombreFotografia El nombre de la fotografía del restaurante.
     */
    public Restaurante(int id, String cif, String nombre, Direccion direccion, String especialidad, double gastosEnvio, int tiempoEnvio, boolean ofreceCatering, Catering catering, ArrayList<Comida> comidas, String nombreFotografia) {
        this.id = id;
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.especialidad = especialidad;
        this.gastosEnvio = gastosEnvio;
        this.tiempoEnvio = tiempoEnvio;
        this.setCatering(catering);
        this.ofreceCatering = ofreceCatering;
        this.comidas = comidas;
        this.catering = catering;
        this.nombreFotografia = nombreFotografia;
        this.calificaciones = new ArrayList<>();
        this.calificacionMedia = 0.0;
        this.menus = new ArrayList<>();
        this.opiniones = new ArrayList<>();
    }


    /**
     * Devuelve la lista de opiniones del restaurante.
     *
     * @return La lista de opiniones del restaurante.
     */
    public ArrayList<Opinion> getOpiniones() {
        return opiniones;
    }

    /**
     * Establece la lista de opiniones del restaurante.
     *
     * @param opiniones La lista de opiniones a establecer.
     */
    public void setOpiniones(ArrayList<Opinion> opiniones) {
        this.opiniones = opiniones;
    }

    /**
     * Devuelve la calificación media del restaurante.
     *
     * @return La calificación media del restaurante.
     */
    public double getCalificacionMedia() {
        calculaCalificacionMedia();
        return calificacionMedia;
    }

    /**
     * Establece la calificación media del restaurante.
     *
     * @param calificacionMedia La calificación media a establecer.
     */
    public void setCalificacionMedia(double calificacionMedia) {
        this.calificacionMedia = calificacionMedia;
    }

    /**
     * Devuelve la lista de calificaciones del restaurante.
     *
     * @return La lista de calificaciones del restaurante.
     */
    public ArrayList<Integer> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Establece la lista de calificaciones del restaurante.
     *
     * @param calificaciones La lista de calificaciones a establecer.
     */
    public void setCalificaciones(ArrayList<Integer> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * Devuelve la calificación en el índice dado.
     *
     * @param index El índice de la calificación.
     * @return La calificación en el índice dado.
     */
    public int getCalificacionAt(int index) {
        return this.calificaciones.get(index);
    }

    /**
     * Agrega una calificación al restaurante.
     *
     * @param calificacion La calificación a agregar.
     */
    public void addCalificacion(int calificacion) {
        this.calificaciones.add(calificacion);
    }

    /**
     * Devuelve el nombre de la fotografía del restaurante.
     *
     * @return El nombre de la fotografía del restaurante.
     */
    public String getNombreFotografia() {
        return nombreFotografia;
    }

    /**
     * Establece el nombre de la fotografía del restaurante.
     *
     * @param nombreFotografia El nombre de la fotografía a establecer.
     */
    public void setNombreFotografia(String nombreFotografia) {
        this.nombreFotografia = nombreFotografia;
    }

    /**
     * Devuelve el ID del restaurante.
     *
     * @return El ID del restaurante.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del restaurante.
     *
     * @param id El ID a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la lista de comidas del restaurante.
     *
     * @return La lista de comidas del restaurante.
     */
    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    /**
     * Establece la lista de comidas del restaurante.
     *
     * @param comidas La lista de comidas a establecer.
     */
    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }

    /**
     * Devuelve el CIF del restaurante.
     *
     * @return El CIF del restaurante.
     */
    public String getCif() {
        return cif;
    }

    /**
     * Establece el CIF del restaurante.
     *
     * @param cif El CIF a establecer.
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Devuelve el nombre del restaurante.
     *
     * @return El nombre del restaurante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del restaurante.
     *
     * @param nombre El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la dirección del restaurante.
     *
     * @return La dirección del restaurante.
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del restaurante.
     *
     * @param direccion La dirección a establecer.
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve la especialidad del restaurante.
     *
     * @return La especialidad del restaurante.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad del restaurante.
     *
     * @param especialidad La especialidad a establecer.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Devuelve los gastos de envío del restaurante.
     *
     * @return Los gastos de envío del restaurante.
     */
    public double getGastosEnvio() {
        return gastosEnvio;
    }

    /**
     * Establece los gastos de envío del restaurante.
     *
     * @param gastosEnvio Los gastos de envío a establecer.
     */
    public void setGastosEnvio(Double gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    /**
     * Devuelve el tiempo de envío del restaurante.
     *
     * @return El tiempo de envío del restaurante.
     */
    public int getTiempoEnvio() {
        return tiempoEnvio;
    }

    /**
     * Establece el tiempo de envío del restaurante.
     *
     * @param tiempoEnvio El tiempo de envío a establecer.
     */
    public void setTiempoEnvio(int tiempoEnvio) {
        this.tiempoEnvio = tiempoEnvio;
    }

    /**
     * Devuelve el catering del restaurante.
     *
     * @return El catering del restaurante.
     */
    public Catering getCatering() {
        return catering;
    }

    /**
     * Establece el catering del restaurante.
     *
     * @param catering El catering a establecer.
     */
    public void setCatering(Catering catering) {
        this.catering = catering;
    }

    /**
     * Devuelve true si el restaurante ofrece catering, false en caso contrario.
     *
     * @return true si el restaurante ofrece catering, false en caso contrario.
     */
    public boolean isOfreceCatering() {
        return ofreceCatering;
    }

    /**
     * Establece si el restaurante ofrece catering.
     *
     * @param ofreceCatering true si el restaurante ofrece catering, false en caso contrario.
     */
    public void setOfreceCatering(boolean ofreceCatering) {
        this.ofreceCatering = ofreceCatering;
    }

    /**
     * Devuelve la lista de menús del restaurante.
     *
     * @return La lista de menús del restaurante.
     */
    public ArrayList<Menu> getMenus() {
        return menus;
    }

    /**
     * Establece la lista de menús del restaurante.
     *
     * @param menus La lista de menús a establecer.
     */
    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    /**
     * Agrega un menú al restaurante.
     *
     * @param menu El menú a agregar.
     */
    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    /**
     * Devuelve la cantidad de opiniones del restaurante.
     *
     * @return La cantidad de opiniones del restaurante.
     */
    public int getCantidadOpiniones() {
        return opiniones.size();
    }

    /**
     * Devuelve la cantidad de comidas del restaurante.
     *
     * @return La cantidad de comidas del restaurante.
     */
    public int getCantidadComidas() {
        return comidas.size();
    }

    /**
     * Devuelve la cantidad de menús del restaurante.
     *
     * @return La cantidad de menús del restaurante.
     */
    public int getCantidadMenus() {
        return menus.size();
    }
}

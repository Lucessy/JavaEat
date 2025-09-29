package com.clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase carrito, almacena las comidas, menús y los distintos caterings que se eligen en un carrito,
 * Es serializable
 * 
 */
public class CarritoCompra implements Serializable {

    // Atributos
    private ArrayList<Comida> comidas;
    private ArrayList<Menu> menus;
    private ArrayList<String> catering;

    // Métodos
    /**
     * Establece una nueva comida en la lista comidas
     * @param comida 
     */
    public void addComida(Comida comida) {
        comidas.add(comida);
    }

    /**
     * Establece un nuevo menú en la lista menús
     * @param menu 
     */
    public void addMenu(Menu menu) {
        menus.add(menu);
    }
    /**
     * Establece un nuevo tipo de catering en catering
     * @param servicio 
     */
    public void addCatering(String servicio) {
        catering.add(servicio);
    }

    /**
     * Calcula el precio de la compra total con todos los productos que están en el carrito
     * @param rest Toma el restaurante del que se compra
     * @return suma total compra
     */
    public double precioCompra(Restaurante rest) {
        Catering cat = rest.getCatering();
        double suma = 0.0;
        for (Comida comida : comidas) {
            suma += comida.getPrecioVenta() * comida.getCantidad();
        }
        for (Menu menu : menus) {
            suma += menu.getPrecio() * menu.getCantidad();
        }
        for (String nombre : catering) {
            switch (nombre) {
                case "Cocineros" ->
                    suma += cat.getPrecioCocineros();
                case "Camareros" ->
                    suma += cat.getPrecioCamareros();
                case "Espacio" ->
                    suma += cat.getPrecioEspacio();
                case "Decoración" ->
                    suma += cat.getPrecioDecoracion();
                case "Comida" ->
                    suma += cat.getPrecioComida(); //Debería ser el precio de todas las comidas en total

            }
        }
        return suma;
    }

    // Constructores
    /**
     * Constructor del Carrito compra
     */
    public CarritoCompra() {
        this.comidas = new ArrayList<>();
        this.menus = new ArrayList<>();
        this.catering = new ArrayList<>();
    }

    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public ArrayList<String> getCatering() {
        return catering;
    }

    public void setCatering(ArrayList<String> catering) {
        this.catering = catering;
    }

}

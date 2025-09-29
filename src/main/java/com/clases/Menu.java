package com.clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Representa un menú de comidas.
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributos
    private String nombre;
    private Double precio;
    private String nombreFotografia;
    private ArrayList<Comida> comidas = new ArrayList<>();
    private int id;
    private int cantidad;

    /**
     * Crea un nuevo objeto Menu a partir de otro objeto Menu.
     *
     * @param copyMe El objeto Menu a copiar.
     */
    public Menu(Menu copyMe) {
        this.nombre = copyMe.nombre;
        this.precio = copyMe.precio;
        this.nombreFotografia = copyMe.nombreFotografia;
        this.comidas = copyMe.comidas;
        this.cantidad = copyMe.cantidad;
        this.id = copyMe.id;
    }

    /**
     * Calcula el precio del menú.
     * Si el menú contiene comidas, se calcula sumando los precios de las comidas y aplicando un descuento del 5%.
     * Si el menú no contiene comidas, el precio se establece en 0.
     */
    public void calcularPrecio() {
        if (comidas != null) {
            double precio = 0.0;
            for (Comida comida : comidas) {
                precio += comida.getPrecioVenta();
            }
            BigDecimal bd = new BigDecimal(precio * 0.95);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            precio = bd.doubleValue();
            this.precio = precio; // Si compras un Menú sale 5% más barato que comprando todo por separado
        } else {
            this.precio = 0.0;
        }
    }

    /**
     * Aumenta la cantidad de menús en 1.
     *
     * @return La nueva cantidad de menús.
     */
    public int addCantidad() {
        this.cantidad += 1;
        return cantidad;
    }

    /**
     * Reduce la cantidad de menús en 1.
     *
     * @return La nueva cantidad de menús.
     */
    public int restCantidad() {
        this.cantidad -= 1;
        return cantidad;
    }

    /**
     * Crea un nuevo objeto Menu con los detalles especificados.
     *
     * @param nombre           El nombre del menú.
     * @param nombreFotografia El nombre de la fotografía del menú.
     * @param comidas          Las comidas que forman parte del menú.
     * @param id               El ID del menú.
     */
    public Menu(String nombre, String nombreFotografia, ArrayList<Comida> comidas, int id) {
        this.nombre = nombre;
        this.nombreFotografia = nombreFotografia;
        this.comidas = comidas;
        this.id = id;
        calcularPrecio();
    }

    /**
     * Obtiene la cantidad de menús.
     *
     * @return La cantidad de menús.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de menús.
     *
     * @param cantidad La cantidad de menús a establecer.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el ID del menú.
     *
     * @return El ID del menú.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del menú.
     *
     * @param id El ID del menú a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del menú.
     *
     * @return El nombre del menú.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del menú.
     *
     * @param nombre El nombre del menú a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del menú.
     *
     * @return El precio del menú.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del menú.
     *
     * @param precio El precio del menú a establecer.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el nombre de la fotografía del menú.
     *
     * @return El nombre de la fotografía del menú.
     */
    public String getNombreFotografia() {
        return nombreFotografia;
    }

    /**
     * Establece el nombre de la fotografía del menú.
     *
     * @param nombreFotografia El nombre de la fotografía del menú a establecer.
     */
    public void setNombreFotografia(String nombreFotografia) {
        this.nombreFotografia = nombreFotografia;
    }

    /**
     * Obtiene las comidas del menú.
     *
     * @return Las comidas del menú.
     */
    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    /**
     * Establece las comidas del menú.
     *
     * @param comidas Las comidas del menú a establecer.
     */
    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }
}

package com.clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class Venta implements Serializable {

    // Atributos
    private int id;
    private LocalDateTime fechaVenta;
    private ArrayList<Comida> comidasCompradas = new ArrayList<>();
    private ArrayList<Menu> menusComprados = new ArrayList<>();
    private ArrayList<String> cateringComprados = new ArrayList<>();
    private Cliente cliente;
    private Direccion direccionEnvio;
    private Double precioCompra;
    private Restaurante restaurante;

    // Métodos
    /**
     * Salva la venta dada como un fichero de texto con los datos
     * correspondientes. No debería ser llamado directamente, mejor llamar a
     * AppManager.registerVenta(Venta venta)
     *
     */
    public void imprimirVenta() {
        try {
            File newFile = new File("Ventas/Venta " + id + ".txt");
            try {
                newFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error IO: " + ioe.toString());
            }

            PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(newFile)));

            String separator = "-------------------------------------------";
            salida.println("VENTA " + id);
            salida.println(separator);
            salida.println("Fecha de venta: " + fechaVenta.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
            salida.println(separator);
            // Compra
            salida.println("Productos comprados:");
            
            if (!comidasCompradas.isEmpty()) {
                for (Comida comida : comidasCompradas) {
                    int cantidad = comida.getCantidad();
                    salida.println("-COMIDA " + comida.getId() + ":");
                    salida.println("    -Unidades compradas: " + cantidad);
                    salida.println("    >Nombre: " + comida.getTitulo());
                    salida.println("    >Ingredientes: " + comida.getIngredientes());
                    salida.println("    >Precio por unidad (€): " + comida.getPrecioVenta());
                    salida.println("    >Precio total (" + cantidad + " unidades) (€):" + comida.getPrecioVenta() * cantidad);
                    salida.println();
                }
            }
            if (!menusComprados.isEmpty()) {
                for (Menu menu : menusComprados) {
                    int cantidad = menu.getCantidad();
                    salida.println("-MENU " + menu.getId() + ":");
                    salida.println("    -Unidades compradas: " + cantidad);
                    salida.println("    >Nombre: " + menu.getNombre());
                    salida.println("    >Comidas Incluidas: ");
                    for (Comida comida : menu.getComidas()) {
                        salida.println(comida.getTitulo());
                    }
                    salida.println("    >Precio por unidad (€): " + menu.getPrecio());
                    salida.println("    >Precio total (" + cantidad + " unidades) (€):" + menu.getPrecio() * cantidad);
                    salida.println();
                }
            }
            //APARTADO PARA LA ZONA DEL CATERING XDEOS
            if (!cateringComprados.isEmpty()) {
                for (String servicio : cateringComprados) {
                    salida.println("-CATERING " + ":");
                    salida.println("    -Unidades compradas: " + "1");
                    salida.println("    >Nombre: " + servicio);
                    if (servicio.equals("Comida")) {
                        salida.println("    >Comidas Incluidas: ");
//                        for (Comida comida : menu.getComidas()) {
//                        salida.println(comida.getTitulo());
//                          }
                        salida.println("    " + restaurante.getCatering().getComidaAProporcionar());
                    }
                    salida.println("    >Precio total (" + "1" + " unidades) (€):" + restaurante.getCatering().getPrecioServicios(servicio));
                    salida.println();
                }
            }

            
            salida.println(separator);
            salida.println(separator);
            salida.println("Compra realizada por:");
            salida.println("-CLIENTE " + cliente.getId());
            salida.println("    >Nombre: " + cliente.getNombre());
            if (cliente instanceof ClienteParticular) {
                salida.println("    >DNI/NIE: " + ((ClienteParticular) cliente).getDni());
            } else {
                salida.println("    >CIF: " + ((Empresa) cliente).getCif());
            }
            salida.println("    >Correo: " + cliente.getCorreo());
            salida.println("    >Teléfono: " + cliente.getTelefono());
            salida.println("    >Dirección de envío:");
            salida.println("        -Calle: " + direccionEnvio.getCalle());
            salida.println("        -Número: " + direccionEnvio.getNumero());
            salida.println("        -Código postal: " + direccionEnvio.getCodigoPostal());
            salida.println("        -Ciudad: " + direccionEnvio.getCiudad());
            salida.println(separator);
            salida.println("Precio de la compra: " + precioCompra + "€");
            salida.println(separator);

            // Venta
            salida.println("Venta realizada por:");
            salida.println("-RESTAURANTE " + restaurante.getId());
            salida.println("    >Nombre: " + restaurante.getNombre());
            salida.println("    >CIF: " + restaurante.getCif());
            salida.println("    >Dirección de la compra:");
            salida.println("        -Calle: " + restaurante.getDireccion().getCalle());
            salida.println("        -Número: " + restaurante.getDireccion().getNumero());
            salida.println("        -Código postal: " + restaurante.getDireccion().getCodigoPostal());
            salida.println("        -Ciudad: " + restaurante.getDireccion().getCiudad());
            salida.println(separator);

            salida.close();
            System.out.println("Se ha almacenado la compra en un fichero de texto");
        } catch (IOException ioe) {
            System.out.println("Error IO: " + ioe.toString());
        }

    }
       /**
     * Constructor de la clase Venta.
     *
     * @param id              El ID de la venta
     * @param fechaVenta      La fecha de la venta
     * @param comidasCompradas La lista de comidas compradas
     * @param menusComprados  La lista de menús comprados
     * @param cateringComprados La lista de catering comprados
     * @param cliente         El cliente que realizó la compra
     * @param direccionEnvio  La dirección de envío
     * @param precioCompra    El precio total de la compra
     * @param restaurante     El restaurante que realizó la venta
     */
    public Venta(int id, LocalDateTime fechaVenta, ArrayList<Comida> comidasCompradas, ArrayList<Menu> menusComprados, ArrayList<String> cateringComprados, Cliente cliente, Direccion direccionEnvio, Double precioCompra, Restaurante restaurante) {
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.comidasCompradas = comidasCompradas;
        this.menusComprados = menusComprados;
        this.cliente = cliente;
        this.direccionEnvio = direccionEnvio;
        this.precioCompra = precioCompra;
        this.restaurante = restaurante;
        this.cateringComprados = cateringComprados;
    }

    // Constructores
        /**
     * Obtiene el restaurante asociado a la venta.
     *
     * @return El restaurante asociado a la venta.
     */
    public Restaurante getRestaurante() {
        return restaurante;
    }

    /**
     * Establece el restaurante asociado a la venta.
     *
     * @param restaurante El restaurante asociado a la venta.
     */
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    /**
     * Obtiene el ID de la venta.
     *
     * @return El ID de la venta.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la venta.
     *
     * @param id El ID de la venta.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha de la venta.
     *
     * @return La fecha de la venta.
     */
    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    /**
     * Establece la fecha de la venta.
     *
     * @param fechaVenta La fecha de la venta.
     */
    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    /**
     * Obtiene el cliente asociado a la venta.
     *
     * @return El cliente asociado a la venta.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente asociado a la venta.
     *
     * @param cliente El cliente asociado a la venta.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene la dirección de envío de la venta.
     *
     * @return La dirección de envío de la venta.
     */
    public Direccion getDireccionEnvio() {
        return direccionEnvio;
    }

    /**
     * Establece la dirección de envío de la venta.
     *
     * @param direccionEnvio La dirección de envío de la venta.
     */
    public void setDireccionEnvio(Direccion direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    /**
     * Obtiene el precio total de la compra.
     *
     * @return El precio total de la compra.
     */
    public Double getPrecioCompra() {
        return precioCompra;
    }

    /**
     * Establece el precio total de la compra.
     *
     * @param precioCompra El precio total de la compra.
     */
    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    /**
     * Obtiene la lista de comidas compradas.
     *
     * @return La lista de comidas compradas.
     */
    public ArrayList<Comida> getComidasCompradas() {
        return comidasCompradas;
    }

    /**
     * Establece la lista de comidas compradas.
     *
     * @param comidasCompradas La lista de comidas compradas.
     */
    public void setComidasCompradas(ArrayList<Comida> comidasCompradas) {
        this.comidasCompradas = comidasCompradas;
    }

    /**
     * Obtiene la lista de menús comprados.
     *
     * @return La lista de menús comprados.
     */
    public ArrayList<Menu> getMenusComprados() {
        return menusComprados;
    }

    /**
     * Establece la lista de menús comprados.
     *
     * @param menusComprados La lista de menús comprados.
     */
    public void setMenusComprados(ArrayList<Menu> menusComprados) {
        this.menusComprados = menusComprados;
    }

    /**
     * Obtiene la lista de catering comprados.
     *
     * @return La lista de catering comprados.
     */
    public ArrayList<String> getCateringComprados() {
        return cateringComprados;
    }

    /**
     * Establece la lista de catering comprados.
     *
     * @param cateringComprados La lista de catering comprados.
     */
    public void setCateringComprados(ArrayList<String> cateringComprados) {
        this.cateringComprados = cateringComprados;
    }
}

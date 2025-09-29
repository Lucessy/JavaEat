package com.clases;

import com.interfaz.*;
import com.clases.*;
import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class MainManager {

    // Atributos
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Venta> ventas;
    private static Cliente clienteIniciado;
    private static ArrayList<Restaurante> restaurantes;
    private static JFrame actualFrame;
    private static CarritoCompra carrito = new CarritoCompra();

    // Main
    /**
     * Método main llamado al ejecutar la aplicación
     * @param args 
     */
    public static void main(String[] args) {
        boolean success = loadData();
        if(!success){
            loadDefaultData();
            
        }
        showFrame("Login");
    }
    // Métodos

    /**
     * Intenta iniciar sesión con los datos dados.
     *
     * @param correo El correo del cliente
     * @param clave Clave de acceso del cliente
     * @return Devuelve "" si se inicia sesión con éxito y el mensaje de error a
     * mostrar si los datos no son correctos.
     */
    public static String verificaLogin(String correo, String clave) {

        if (correo.equals("admin@javaeat.com") || correo.equals("admin")) {
            if (clave.equals("admin")) {
                adminLogin();
                return "";
            } else {
                return "Contraseña incorrecta";
            }
        }
        Cliente usuario = null;
        for (Cliente cliente : clientes) {
            if (cliente.getCorreo().equals(correo)) {
                usuario = cliente;
                break;
            }
        }
        if (usuario == null) {
            return "Este correo no está registrado";
        }
        if (usuario.getClave().equals(clave)) {
            login(usuario);
            return "";
        } else {
            return "Contraseña incorrecta";
        }
    }

    /**
     * Entra al sistema como Administrador
     */
    public static void adminLogin() {
        showFrame("Administrador");
        System.out.println("Sesion de administrador iniciada con éxito");
    }

    /**
     * Entra como cliente/empresa al menú principal
     *
     * @param cliente Entra como clienteIniciado al sistema
     */
    public static void login(Cliente cliente) {
        clienteIniciado = cliente;
        showFrame("MenuPrincipal");
        System.out.println("Sesion iniciado con éxito");
    }

    /**
     * Intenta registrar al usuario con los datos dados
     *
     * @param datosCliente los datos del cliente a registrar
     * @return Devuelve "" si se registra con éxito y el mensaje de error a
     * mostrar si los datos no son correctos.
     */
    public static String verificaRegister(Cliente datosCliente) {
        System.out.println("Registrando...");
        boolean datosYaRegistrados = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCorreo().equals(datosCliente.getCorreo())) {
                datosYaRegistrados = true;
                break;
            }
            if (cliente instanceof ClienteParticular && datosCliente instanceof ClienteParticular) {
                if (((ClienteParticular) cliente).getDni().equals(((ClienteParticular) datosCliente).getDni())) {
                    datosYaRegistrados = true;
                    break;
                }
            }
            if (cliente instanceof Empresa && datosCliente instanceof Empresa) {
                if (((Empresa) cliente).getCif().equals(((Empresa) datosCliente).getCif())) {
                    datosYaRegistrados = true;
                    break;
                }
            }
        }
        if (datosYaRegistrados) {
            return "Este correo, DNI/NIE o CIF ya está registrado";
        }
        if (!datosCliente.correoValido(datosCliente.getCorreo())) {
            return "Introduzca un correo válido";
        }
        register(datosCliente);
        return "";
    }

    /**
     * Ordena la colección de comidas dada con el criterio de orden dado
     * mediante el método de la burbuja.
     *
     * @param orden el índice de tipo de orden. (0 = relevancia, 1 =
     * tiempoDeEnvio, 2=gastosEnvio)
     * @param restaurantes la colección de restaurantes a ordenar
     */
    public static void bubbleSort(int orden, ArrayList<Restaurante> restaurantes) {
        Restaurante temp; // temporal para intercambio
        boolean estaOrdenado; // indica si esta ordenada
        for (int i = 0; i < restaurantes.size() - 1; i++) {
            estaOrdenado = true;
            for (int j = 0; j < restaurantes.size() - i - 1; j++) {
                boolean intercambiar = false;
                switch (orden) {
                    case 0:
                        if (restaurantes.get(j).getCalificacionMedia() < restaurantes.get(j + 1).getCalificacionMedia()) { //Compara la calificación
                            intercambiar = true;
                        }
                        break;
                    case 1:
                        if (restaurantes.get(j).getTiempoEnvio() > restaurantes.get(j + 1).getTiempoEnvio()) { //Compara el tiempo de envío
                            intercambiar = true;
                        }
                        break;
                    case 2:
                        if (restaurantes.get(j).getGastosEnvio() > restaurantes.get(j + 1).getGastosEnvio()) { //Compara los gatos de envío
                            intercambiar = true;
                        }
                        break;
                    default:
                        break;
                }
                if (intercambiar) {
                    temp = restaurantes.get(j);
                    restaurantes.set(j, restaurantes.get(j + 1));
                    restaurantes.set(j + 1, temp);
                    estaOrdenado = false;
                }
                if (estaOrdenado) {
                    // no encontramos nada para intercambiar.
                    break; // hemos terminado
                }
            }

        }
    }

    /**
     * Registra al usuario dado. No verifica que la información sea válida, para
     * ello llamar a tryRegister(Cliente datosCliente)
     *
     * @param cliente El cliente a registrar
     */
    public static void register(Cliente cliente) {

        clienteIniciado = cliente;
        showFrame("RegistroTarjeta");
        System.out.println("Cliente registrado con éxito");
    }

    /**
     *
     * @param tarjeta
     * @return vacío si no hay datos incorrectos y retorna un mensaje si la
     * tarjeta está caducada
     */
    public static String verificaTarjeta(Tarjeta tarjeta) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
        try {
            Date inputDate = sdf.parse(tarjeta.getFechaCaducidad());
            Date currentDate = new Date();
            if (inputDate.before(currentDate)) {
                return "La tarjeta está caducada\nPor favor, introduzca una fecha válida.";
            }
        } catch (ParseException ex) {
            //Logger.getLogger(RegistroTarjeta.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ParseException ex");
        }
        if (!(tarjeta.getFechaCaducidad().length() == 5)) {
            return "Introduzca una fecha de caducidad válida";
        }
        addTarjeta(tarjeta);
        return "";
    }

    /**
     * Añade una tarjeta al clienteIniciado
     *
     * @param tarjeta del cliente que se acaba de registrar
     */
    public static void addTarjeta(Tarjeta tarjeta) {
        clienteIniciado.setTarjeta(tarjeta);
        clientes.add(clienteIniciado);
        showFrame("MenuPrincipal");
        System.out.println("Tarjeta añadida con éxito");
    }
    
    /**
     * Verifica los datos del cliente y actualiza la información en caso de ser válidos.
     *
     * @param clienteTemp El objeto Cliente con los datos a verificar y actualizar.
     * @param claveNueva La nueva contraseña a establecer (puede estar vacía).
     * @return Un mensaje de error si los datos no son válidos, o una cadena vacía si se actualizó correctamente.
     */
    public static String verificaDatos(Cliente clienteTemp, String claveNueva) {
        System.out.println("Actualizando datos...");
        if (!clienteTemp.correoValido(clienteTemp.getCorreo())) {
            return "Introduzca un correo válido";
        }
        if (!clienteTemp.getDireccion().getNumero().matches("\\d+")) {
            return "Introduzca un número de calle válido";
        }
        String codPostal = clienteTemp.getDireccion().getCodigoPostal();
        if (!clienteTemp.getDireccion().validarCodPostal(codPostal)) {
            return "Introduzca un código postal válido";
        }
        if (!clienteTemp.getClave().equals(clienteIniciado.getClave())) {
            return "La contraseña es incorrecta";
        }
        if (!claveNueva.equals("")) {
            clienteTemp.setClave(claveNueva);
        }
        actualizarDatos(clienteTemp);
        return "";
    }
    
    /**
     * Actualiza los datos del cliente iniciado con los datos proporcionados.
     *
     * @param clienteTemp El objeto Cliente con los datos actualizados.
     */
    public static void actualizarDatos(Cliente clienteTemp) {
        clienteIniciado = clienteTemp;

        showFrame("Cuenta");

        System.out.println("Datos actualizados con éxito");
    }
    
    /**
     * Verifica si el precio proporcionado es un valor numérico válido.
     *
     * @param precio El precio a verificar.
     * @return El precio convertido a Double si es válido, o -1.0 si no es válido.
     */
    public static Double verificaPrecio(String precio) {
        try {
            Double num = Double.valueOf(precio.replace(',', '.'));
            return num;
        } catch (NumberFormatException e) {
            return -1.0;
        }

    }
    
    /**
     * Verifica si el tiempo proporcionado es un valor numérico válido.
     *
     * @param tiempo El tiempo a verificar.
     * @return El tiempo convertido a int si es válido, o -1 si no es válido.
     */
    public static int verificaTiempo(String tiempo) {
        try {
            int num = Integer.parseInt(tiempo);
            return num;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Guarda los datos y finaliza la aplicación.
     */
    public static void quit() {
        saveData();
        System.exit(0);
    }
    
    /**
     * Verifica si el número de calle proporcionado es válido.
     *
     * @param num El número de calle a verificar.
     * @return Un mensaje de error si el número de calle no es válido, o una cadena vacía si es válido.
     */
    public static String verificaNumeroCalle(String num) {
        String numero = num;
        if (!numero.matches("\\d+")) {
            return "Por favor, introduzca un número de calle válido";
        } else {
            return "";
        }
    }
    
    /**
     * Agrega un restaurante a la lista de restaurantes y muestra el marco "Administrador".
     *
     * @param restaurante El objeto Restaurante a agregar.
     */
    public static void addRestaurante(Restaurante restaurante) {
        restaurantes.add(restaurante);
        showFrame("Administrador");
    }
    
    /**
     * Limpia el contenido del carrito de compras (comidas, menus y catering).
     */
    public static void limpiarCarrito() {
        carrito.getComidas().clear();
        carrito.getMenus().clear();
        carrito.getCatering().clear();
        //Catering o algo así y clear
    }

    /**
     * Inicializa los valores por defectos, establecidos en este método.
     */
    public static void loadDefaultData() {

        clientes = new ArrayList<>();
        restaurantes = new ArrayList<>();
        ventas = new ArrayList<>();

        //Dirección Clientes
        Direccion direccion1 = new Direccion("Nuñez de Guzman", "46", "28805", "Alcalá de Henares");
        Direccion direccion2 = new Direccion("Av de la Virgen del Val", "69", "28802", "Alcalá de Henares");
        Direccion direccion3 = new Direccion("Talamanca", "3", "28842", "Madrid");

        //Clientes
        Tarjeta tarjeta = new Tarjeta("Luciana Diaz", "1234-5678-9012-3456", "01/28");
        Tarjeta tarjeta2 = new Tarjeta("Norman", "5110-5240-7467-4552", "11/29");
        Tarjeta tarjeta3 = new Tarjeta("Juan", "2527-2420-7574-2445", "08/27");

        ClienteParticular usuario = new ClienteParticular("12345678Y", "Diaz", "Luciana", direccion1, "123456789", "luciana@gmail.com", "luciana", 1);
        usuario.setTarjeta(tarjeta);

        Empresa usuario2 = new Empresa("C6049376Q", "norman.com", "Normal & Sons", direccion2, "911256348", "norman@gmail.com", "norman", 2);
        usuario2.setTarjeta(tarjeta2);
        
        Empresa usuario3 = new Empresa("43434343T", "juan.com", "Juan S.A", direccion3, "111250933", "juan@gmail.com", "juan", 3);
        usuario3.setTarjeta(tarjeta3);
        
        clientes.add(usuario);
        clientes.add(usuario2);
        clientes.add(usuario3);
        
        //Dirección Restaurantes
        Direccion direccionR = new Direccion("José Martinez Ruiz Azorín", "6", "28805", "Alcalá de Henares");
        Direccion direccionR2 = new Direccion("Plaza Puerta del Vado", "9", "28802", "Alcalá de Henares");
        Direccion direccionR3 = new Direccion("Av de los Reyes Magos", "39", "28842", "Madrid");
        Direccion direccionR4 = new Direccion("Av de la Caballería Española", "13", "28849", "Madrid");

        //Catering Restaurantes
        Catering cat = new Catering("Arroz con pollo", 250.0, true, false, true, false);
        Catering cat1 = new Catering(false, true, false, true);
        Catering cat2 = new Catering(false, true, true, true);

        String path = "src/main/resources/otro/RESTAURANTE1/COMIDA";
        //Restaurante 1
        String ingre = "Tomate, carne, pan, lechuga";
        Comida com11 = new Comida("WHOPPER", 8.5, path + "1.jpg", ingre, 1);
        Comida com12 = new Comida("STEAKHOUSE", 15.4, path + "2.jpg", ingre + ",bacon", 2);
        Comida com13 = new Comida("OREO SHAKE", 6.5, path + "3.jpg", "Leche, oreo y nata", 3);
        ArrayList<Comida> comidas1 = new ArrayList<>();
        comidas1.add(com11);
        comidas1.add(com12);
        comidas1.add(com13);

        //Restaurante 2
        path = "src/main/resources/otro/RESTAURANTE2/COMIDA";
        Comida com21 = new Comida("Kentucky Fries Cheese Bacon", 10.4, path + "1.jpg", "Patatas Rústicas, Salsa de Queso y Tiras de Bacon", 1);
        Comida com22 = new Comida("La Maldita Burger", 9.4, path + "2.jpg", "Burger con 1 filete de pechuga receta Picante, lechuga, tomate, mayonesa y pan con semillas de sésamo", 2);
        Comida com23 = new Comida("La Mítica Burger", 7.9, path + "3.jpg", "Burger con 2 filetes de pechuga Original, bacon, salsa Coronel, lechuga, tomate, mayonesa y pan brioche", 3);
        ArrayList<Comida> comidas2 = new ArrayList<>();
        comidas2.add(com22);
        comidas2.add(com21);
        comidas2.add(com23);

        //Restaurante 3
        path = "src/main/resources/otro/RESTAURANTE3/COMIDA";
        Comida com31 = new Comida("Benedict", 13.4, path + "1.jpg", "Carne de vacuno con huevo frito, salsa holandesa, bacon crujiente, queso cheddar, tomate, lechuga y salsa mayo-sriracha en pan brioche tostado con dos sésamos. Acompañada de guarnición a elegir y salsa baconesa.", 1);
        Comida com32 = new Comida("Piri Piri Chicken", 11.6, path + "2.jpg", "Contramuslos de jugoso pollo marinado en piri piri, con pico de gallo con mango y toques de hierbabuena, salsa mayo-sriracha ligeramente picante sobre lechuga y brotes en pan de cristal. Acompañado de guarnición a elegir y salsa mayo- sriracha.", 2);
        Comida com33 = new Comida("Bun & Roll Choco", 15.4, path + "3.jpg", "Croissant roll relleno de helado con avellana y chocolate y helado de chocolate blanco, sirope de cacao, nueces y un toque de caramelo.", 3);
        ArrayList<Comida> comidas3 = new ArrayList<>();
        comidas3.add(com31);
        comidas3.add(com32);
        comidas3.add(com33);
        
        //Restaurante 4
        path = "src/main/resources/otro/RESTAURANTE4/COMIDA";
        Comida com41 = new Comida("McExtreme Bacon Doble", 6.4, path + "1.jpg", "Disfruta el doble con su deliciosa carne 100% vacuno español acompañada de queso, bacon, cebolla blanca y nuestra inconfundible salsa de bacon. Nuestra McExtreme de McDonald's® más emblemática, ahora con mucho más de lo que te gusta.", 1);
        Comida com42 = new Comida("McCrispy BBQ&Bacon", 12.3, path + "2.jpg", "Disfruta de nuestra nueva hamburguesa de pollo con deliciosas salsas BBQ y Bacon. Tan crujiente, Tan McCrispy, Tan McDonald's®", 2);
        Comida com43 = new Comida("Ensalada césar con pollo crujiente", 15.2, path + "3.jpg", "¡Disfruta de esta deliciosa ensalada, creada con nuestra base más fresca! Base de ensalada acompañada de pollo crispy, queso en láminas y croutons. Nuestras ensaladas se elaboran diariamente con productos frescos de Florette®", 3);
        ArrayList<Comida> comidas4 = new ArrayList<>();
        comidas4.add(com41);
        comidas4.add(com42);
        comidas4.add(com43);

        // Menu
        path = "src/main/resources/otro/RESTAURANTE1/MENU";
        Menu menu11 = new Menu("Menu Explosivo", path + "1.jpg", comidas1, 1);
        ArrayList<Menu> menu1 = new ArrayList<>();
        menu1.add(menu11);
        
        //Restaurantes
        path = "src/main/resources/otro/RESTAURANTE";
        Restaurante rest = new Restaurante(1, "A80192727", "BURGER KING", direccionR2, "Internacional", 3.0, 4, true, cat1, comidas1, menu1, path + "1/RESTAURANTE.jpg");
        Restaurante rest2 = new Restaurante(2, "B19573628", "KFC", direccionR, "Pollo frito" , 2.5, 5, true, cat2, comidas2, path + "2/RESTAURANTE.jpg");
        Restaurante rest3 = new Restaurante(3, "Q16725493", "VIPS", direccionR3, "Internacional", 1.5, 3, true, cat1, comidas3, path + "3/RESTAURANTE.jpg");
        Restaurante rest4 = new Restaurante(4, "L97246531", "MCDONALD'S", direccionR4, "Comida Callejera", 3.5, 2, true, cat, comidas4, path + "4/RESTAURANTE.jpg");

        Opinion op1 = new Opinion("Me encanta todo los dependientes son…",5,"Me encanta todo los dependientes son los mejores y he comido genial todo estupendo!!!!");
        Opinion op2 = new Opinion("Buena disposición",4,"Buena disposición, buena comida, sin embargo mala organización para la entrega y tiempos de entrega");
        Opinion op3 = new Opinion("Los huevos benedict los hicieron mucho",3,"Los huevos benedict los hicieron mucho, el servicio muy lento");
        Opinion op4 = new Opinion("Mc Caro.",1,"Mc. Donals del aeropuerto de Lisboa, precios escandalosos!! Una caja de 20 nugget de pollo once euros, una coca cola sin hielo templada, tres euros. Para un pais como Portugal con un nivel de vida y un poder adquisitivo menor que el de España, me parecen exagerados estos precios. No volvere a consumir en este establecimiento jamás!!");
        
        rest.addOpinion(op1);
        rest2.addOpinion(op2);
        rest3.addOpinion(op3);
        rest4.addOpinion(op4);
        
        restaurantes.add(rest);
        restaurantes.add(rest2);
        restaurantes.add(rest3);
        restaurantes.add(rest4);

    }
    
    /**
     * Obtiene un ID de venta disponible.
     *
     * @return Un ID de venta que no está siendo utilizado actualmente.
     */
    public static int getAvailableIdVentas() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Venta venta : ventas) {
            ids.add(venta.getId());
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
     * Recupera todos los datos.
     *
     * @return Devuelve verdadero si se cargan los datos con éxito y devuelve
     * falso si no encuentra el archivo o ante cualquier otro error.
     */
    public static boolean loadData() {
        try {
            FileInputStream fis = new FileInputStream("saves/javaeat.lu");
            ObjectInputStream ois = new ObjectInputStream(fis);
            clientes = (ArrayList<Cliente>) ois.readObject();
            restaurantes = (ArrayList<Restaurante>) ois.readObject();
            ventas = (ArrayList<Venta>) ois.readObject();
            return true;

        } catch (IOException e) {
            System.out.println("No hay datos previos");
            System.out.println("Error IO: " + e.getMessage());

            return false;
        } catch (ClassNotFoundException e) {
            System.out.println("Error clase no encontrada: " + e.getMessage());
            return false;
        }

    }

    /**
     * Salva todos los datos.
     */
    public static void saveData() {
        FileOutputStream fos;
        try {
            File newFile = new File("saves/javaeat.lu");
            newFile.createNewFile();
            fos = new FileOutputStream(newFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clientes);
            oos.writeObject(restaurantes);
            oos.writeObject(ventas);

        } catch (IOException e) {
            System.out.println("Error IO: " + e.getMessage());
        }
        System.out.println("Data saved correctly!");
    }

    /**
     * Cambia de Frame cerrando el anterior
     *
     * @param frameName
     */
    public static void showFrame(String frameName) {
        if (actualFrame != null) {
            actualFrame.setVisible(false);
            actualFrame.dispose();
            actualFrame = null;
        }
        try {
            Class<?> tempClass = Class.forName("com.interfaz." + frameName);
            Constructor<?> ctor = tempClass.getConstructor();
            actualFrame = (JFrame) ctor.newInstance();
            actualFrame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error accediendo al nuevo Frame: " + frameName + "\nError: " + e.getCause());
        }
    }
    
    /**
     * Muestra un panel en un contenedor principal.
     *
     * @param p         El panel que se va a mostrar.
     * @param width     El ancho del panel.
     * @param height    La altura del panel.
     * @param contenido El contenedor principal donde se mostrará el panel.
     */
    public static void showPanel(JPanel p, int width, int height, JPanel contenido) {
        p.setSize(width, height);
        p.setLocation(0, 0);

        contenido.removeAll();
        contenido.add(p, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
    }
    
    /**
     * Muestra la ventana de alta de restaurante.
     *
     * @param restaurante      El restaurante que se va a dar de alta.
     * @param nuevoRestaurante Indica si se trata de un nuevo restaurante o una actualización.
     */
    public static void showAltaRestaurante(Restaurante restaurante, boolean nuevoRestaurante) {
        if (actualFrame != null) {
            actualFrame.setVisible(false);
            actualFrame.dispose();
            actualFrame = null;
        }
        actualFrame = new AltaRestaurante(restaurante, nuevoRestaurante);
        actualFrame.setVisible(true);
    }
    
    /**
     * Muestra la ventana de visualización de restaurante.
     *
     * @param restaurante El restaurante que se va a visualizar.
     */
    public static void showVerRestaurante(Restaurante restaurante) {
        if (actualFrame != null) {
            actualFrame.setVisible(false);
            actualFrame.dispose();
            actualFrame = null;
        }
        actualFrame = new VerRestaurante(restaurante);
        actualFrame.setVisible(true);
    }

    /**
     * Devuelve el restaurante con el id dado o null si no existe.
     *
     * @param id El id del restaurante
     * @return El restaurante con el id dado, si existe.
     */
    public static Restaurante obtenerRestaurante(int id) {
        for (Restaurante restaurante : restaurantes) {
            if (restaurante.getId() == id) {
                return restaurante;
            }
        }
        return null;
    }
    
    /**
     * Registra una venta y la añade a la lista de ventas.
     *
     * @param venta2 La venta que se va a registrar.
     */
    public static void registrarVenta(Venta venta2) {
        ventas.add(venta2);
        venta2.imprimirVenta();
    }

    /**
     * Devuelve el id (número de identificación) más pequeño no usado por otro
     * producto. Usando este método para los nuevos productos se evita que se
     * alcancen ids cada vez mayores.
     *
     * @return El id disponible más pequeño
     */
    public static int getAvailableId() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Restaurante restaurante : restaurantes) {
            ids.add(restaurante.getId());
        }
        int i = 1;
        while (true) {
            if (!ids.contains(i)) {
                return i;
            }
            i++;
        }
    }
    
    //Constructores
        /**
     * Elimina un restaurante de la lista de restaurantes.
     *
     * @param restaurante El restaurante a eliminar.
     */
    public static void removeRestaurante(Restaurante restaurante) {
        restaurantes.remove(restaurante);
    }


       /**
     * Devuelve el carrito de compra actual.
     *
     * @return El carrito de compra actual.
     */
    public static CarritoCompra getCarritoCompra() {
        return carrito;
    }


    
        /**
     * Devuelve la lista de restaurantes.
     *
     * @return La lista de restaurantes.
     */
    public static ArrayList<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    /**
     * Establece la lista de restaurantes.
     *
     * @param restaurantes La lista de restaurantes a establecer.
     */
    public static void setRestaurantes(ArrayList<Restaurante> restaurantes) {
        MainManager.restaurantes = restaurantes;
    }

    /**
     * Devuelve el marco de ventana actual.
     *
     * @return El marco de ventana actual.
     */
    public static JFrame getActualFrame() {
        return actualFrame;
    }

    /**
     * Establece el marco de ventana actual.
     *
     * @param actualFrame El marco de ventana actual a establecer.
     */
    public static void setActualFrame(JFrame actualFrame) {
        MainManager.actualFrame = actualFrame;
    }

    /**
     * Devuelve la lista de clientes.
     *
     * @return La lista de clientes.
     */
    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Establece la lista de clientes.
     *
     * @param clientes La lista de clientes a establecer.
     */
    public static void setClientes(ArrayList<Cliente> clientes) {
        MainManager.clientes = clientes;
    }

    /**
     * Devuelve la lista de ventas.
     *
     * @return La lista de ventas.
     */
    public static ArrayList<Venta> getVentas() {
        return ventas;
    }

    /**
     * Establece la lista de ventas.
     *
     * @param ventas La lista de ventas a establecer.
     */
    public static void setVentas(ArrayList<Venta> ventas) {
        MainManager.ventas = ventas;
    }

    /**
     * Devuelve el cliente iniciado.
     *
     * @return El cliente iniciado.
     */
    public static Cliente getClienteIniciado() {
        return clienteIniciado;
    }

    /**
     * Establece el cliente iniciado.
     *
     * @param clienteIniciado El cliente iniciado a establecer.
     */
    public static void setClienteIniciado(Cliente clienteIniciado) {
        MainManager.clienteIniciado = clienteIniciado;
    }
}

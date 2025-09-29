# JavaEat – Sistema de Gestión de Restaurantes

## 📌 Descripción

**JavaEat** es una aplicación desarrollada en **Java** que simula una plataforma de gestión de restaurantes y pedidos en línea.  
El sistema permite registrar clientes, gestionar restaurantes y menús, procesar compras, y almacenar ventas, integrando tanto la lógica de negocio como una interfaz gráfica.

---

## ⚙️ Funcionalidades principales

- **Gestión de clientes**  
  - Registro de clientes particulares y empresas.  
  - Gestión de direcciones y tarjetas asociadas.  
  - Opiniones y valoraciones de restaurantes.  

- **Gestión de restaurantes**  
  - Alta y modificación de restaurantes.  
  - Creación de menús y platos (comidas).  
  - Administración de catering.  

- **Procesos de compra y ventas**  
  - Carrito de compra.  
  - Procesamiento de ventas con facturación.  
  - Registro y consulta de ventas.  

- **Interfaz gráfica (Swing/JavaFX)**  
  - Módulo de login y registro.  
  - Panel de administración para restaurantes y clientes.  
  - Ventanas para compra, consulta y gestión de información.  

---

## 📂 Estructura del Proyecto

```
JavaEat/
├── src/main/java/com/clases/        # Clases de negocio (Cliente, Restaurante, Comida, Venta, etc.)
├── src/main/java/com/interfaz/      # Interfaz gráfica (Login, MenuPrincipal, Comprar, etc.)
├── src/main/java/com/utilidad/      # Utilidades (manejo de productos)
├── Ventas/                          # Registros de ventas
├── saves/                           # Archivos de persistencia
├── pom.xml                          # Configuración Maven
└── README.md                        # Este archivo
```

---

## 🚀 Requisitos

- **Java 17+**  
- **Maven** para la gestión de dependencias  
- IDE recomendado: **NetBeans / IntelliJ / Eclipse**  

---

## ▶️ Ejecución

1. Clonar el repositorio o descomprimir el proyecto.  
2. Compilar con Maven:
```bash
mvn clean install
```
3. Ejecutar la aplicación desde el IDE o con:
```bash
mvn exec:java -Dexec.mainClass="com.clases.MainManager"
```

---

## ✨ Aspectos Técnicos

- Programación **orientada a objetos** con paquetes bien estructurados.  
- Uso de **Maven** para dependencias y compilación.  
- Persistencia básica en carpetas `Ventas/` y `saves/`.  
- Interfaz gráfica modularizada.  

---

## 👩‍💻 Autora

- **Luciana Paola Díaz**  
  Proyecto académico – Paradigmas Avanzados de Programación  

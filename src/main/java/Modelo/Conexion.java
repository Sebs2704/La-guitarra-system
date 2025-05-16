package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Conexion {

    private final String url = "jdbc:postgresql://localhost:5432/Sistemaventas";
    private final String user = "postgres";
    private final String password = "123456";

    // Método para establecer la conexión
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a PostgreSQL");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
        }
        return conn;

    }

    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("razon_social")
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los clientes: " + e.getMessage());
        }

        return clientes;
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT producto.codigo, producto.descripcion, producto.precio, producto.cantidad, " +
             "producto.material, producto.marca, producto.nombre AS nombre_producto, producto.color, " +
             "marca.nombre AS marca_nombre, marca.id_marca, " +
             "material.nombre AS material_nombre, material.id_material, " +
             "color.nombre AS color_nombre, color.id_color " +
             "FROM public.producto " +
             "LEFT JOIN public.marca ON producto.marca = marca.id_marca " +
             "LEFT JOIN public.material ON producto.material = material.id_material " +
             "LEFT JOIN public.color ON producto.color = color.id_color " +
             "ORDER BY producto.codigo ASC";



        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                String materialNombre = rs.getString("material_nombre");  // ahora es int
                int materialId = rs.getInt("id_material");
                String marcaNombre = rs.getString("marca_nombre"); 
                int marcaId = rs.getInt("id_marca");
                String nombre = rs.getString("nombre_producto");
                int colorId = rs.getInt("id_color");
                String colorNombre = rs.getString("color_nombre");
                
                MarcaItem marcaitem = new MarcaItem(marcaId, marcaNombre);
                MaterialItem materialitem = new MaterialItem(materialId, materialNombre);
                ColorItem coloritem = new ColorItem(colorId, colorNombre);

                Producto producto = new Producto(codigo, descripcion, precio, cantidad, materialitem, marcaitem, nombre, coloritem);
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());
        }

        return productos;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.nombre_usuario, u.contraseña, p.nombre, p.apellido " +
                     "FROM usuario u JOIN persona p ON u.id_persona = p.id_persona";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getString("nombre_usuario"),
                        rs.getString("contraseña"),
                        rs.getString("nombre"),
                        rs.getString("apellido")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los usuarios: " + e.getMessage());
        }

        return usuarios;
    }


    public Producto consultarProducto(int codigo) {
        String sql = "SELECT p.codigo, p.descripcion, p.precio, p.cantidad, " +
             "p.material, p.marca, p.nombre AS nombre_producto, p.color, " +
             "m.id_marca, m.nombre AS marca_nombre, " +
             "mat.id_material, mat.nombre AS material_nombre, " +
             "c.id_color, c.nombre AS color_nombre " +
             "FROM producto p " +
             "LEFT JOIN marca m ON p.marca = m.id_marca " +
             "LEFT JOIN material mat ON p.material = mat.id_material " +
             "LEFT JOIN color c ON p.color = c.id_color " +
             "WHERE p.codigo = ?";


        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String descripcion = rs.getString("descripcion");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                String materialNombre = rs.getString("material_nombre");  // ahora es int
                int materialId = rs.getInt("id_material");
                String marcaNombre = rs.getString("marca_nombre"); 
                int marcaId = rs.getInt("id_marca");
                String nombre = rs.getString("nombre_producto");
                int colorId = rs.getInt("id_color");
                String colorNombre = rs.getString("color_nombre");
                
                MarcaItem marcaitem = new MarcaItem(marcaId, marcaNombre);
                MaterialItem materialitem = new MaterialItem(materialId, materialNombre);
                ColorItem coloritem = new ColorItem(colorId, colorNombre);

                return new Producto(codigo, descripcion, precio, cantidad, materialitem, marcaitem, nombre, coloritem);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar el producto: " + e.getMessage());
        }

        return null; // No encontrado
    }

    public boolean productoExiste(int codigo) {
        String sql = "SELECT COUNT(*) FROM producto WHERE codigo = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar existencia del producto: " + e.getMessage());
        }

        return false;
    }

    public boolean eliminarUsuario(String nombreUsuario) {
        String sql = "DELETE FROM usuario WHERE nombre_usuario = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarUsuario(Usuario usuario) {
         String sqlUsuario = "UPDATE usuario SET contraseña = ? WHERE nombre_usuario = ?";
    String sqlPersona = "UPDATE persona SET nombre = ?, apellido = ? WHERE id_persona = (" +
                        "SELECT id_persona FROM usuario WHERE nombre_usuario = ?)";

    try (Connection conn = connect()) {
        conn.setAutoCommit(false); // Inicia transacción

        // Actualiza la contraseña del usuario
        try (PreparedStatement pstmt1 = conn.prepareStatement(sqlUsuario)) {
            pstmt1.setString(1, usuario.getContraseña());
            pstmt1.setString(2, usuario.getUsuario());
            pstmt1.executeUpdate();
        }

        // Actualiza el nombre y apellido en la tabla persona
        try (PreparedStatement pstmt2 = conn.prepareStatement(sqlPersona)) {
            pstmt2.setString(1, usuario.getNombre());
            pstmt2.setString(2, usuario.getApellido());
            pstmt2.setString(3, usuario.getUsuario());
            pstmt2.executeUpdate();
        }

        conn.commit(); // Confirma ambos cambios
        return true;

    } catch (SQLException e) {
        System.out.println("Error al actualizar el usuario: " + e.getMessage());
        return false;
    }
}

    // Método para verificar si un usuario ya existe en la base de datos
    public boolean usuarioExiste(String nombreUsuario) {
        String sql = "SELECT 1 FROM usuario WHERE nombre_usuario = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // Retorna true si hay algún resultado, indicando que el usuario ya existe

        } catch (SQLException e) {
            System.out.println("Error al verificar el usuario: " + e.getMessage());
            return false; // Retorna false en caso de error
        }
    }
    // Método para registrar un usuario en la base de datos

    public boolean registrarUsuario(Usuario usuario) {
        if (usuarioExiste(usuario.getUsuario())) {
            System.out.println("El usuario ya está registrado.");
            return false;
        }

        String sqlPersona = "INSERT INTO persona (nombre, apellido) VALUES (?, ?) RETURNING id_persona";
        String sqlUsuario = "INSERT INTO usuario (nombre_usuario, contraseña, id_persona) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona);
             PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {

            conn.setAutoCommit(false);

            // Insertar en persona
            stmtPersona.setString(1, usuario.getNombre());
            stmtPersona.setString(2, usuario.getApellido());
            ResultSet rs = stmtPersona.executeQuery();

            int idPersona = -1;
            if (rs.next()) {
                idPersona = rs.getInt("id_persona");
            } else {
                conn.rollback();
                return false;
            }

            // Insertar en usuario
            stmtUsuario.setString(1, usuario.getUsuario());
            stmtUsuario.setString(2, usuario.getContraseña());
            stmtUsuario.setInt(3, idPersona);

            int filas = stmtUsuario.executeUpdate();
            conn.commit();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
            return false;
        }
    }

    // Método para iniciar sesión
    public boolean iniciarSesion(String nombreUsuario, String contraseña) {
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? AND contraseña = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, contraseña);

            ResultSet rs = pstmt.executeQuery();

            // Si hay resultados, las credenciales son correctas
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            return false;
        }
    }

    public boolean registrarProducto(Producto producto) {
        if (productoExiste(producto.getCodigo())) {
            System.out.println("El producto ya está registrado.");
            return false;
        }

        String sql = "INSERT INTO producto (codigo, descripcion, precio, cantidad, material, marca, nombre, color) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, producto.getCodigo());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setInt(3, producto.getPrecio());
            pstmt.setInt(4, producto.getCantidad());
            pstmt.setInt(5, producto.getMaterial().getIdMaterial()); // corregido
            pstmt.setInt(6, producto.getMarca().getIdMarca());    // corregido
            pstmt.setString(7, producto.getNombre());
            pstmt.setInt(8, producto.getColor().getIdColor());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar el producto: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarProducto(Producto producto) {
        String sql = "UPDATE producto SET descripcion = ?, precio = ?, cantidad = ?, material = ?, marca = ?, nombre = ?, color = ? WHERE codigo = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getDescripcion());
            pstmt.setInt(2, producto.getPrecio());
            pstmt.setInt(3, producto.getCantidad());
            pstmt.setInt(4, producto.getMaterial().getIdMaterial()); // corregido
            pstmt.setInt(5, producto.getMarca().getIdMarca());    // corregido
            pstmt.setString(6, producto.getNombre());
            pstmt.setInt(7, producto.getColor().getIdColor());
            pstmt.setInt(8, producto.getCodigo());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar el producto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProducto(int codigo) {
        String sql = "DELETE FROM producto WHERE codigo = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, codigo);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
            return false;
        }
    }

    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
        String sql = "SELECT u.nombre_usuario, u.contraseña, p.nombre, p.apellido " +
                     "FROM usuario u JOIN persona p ON u.id_persona = p.id_persona " +
                     "WHERE u.nombre_usuario = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getString("nombre_usuario"),
                        rs.getString("contraseña"),
                        rs.getString("nombre"),
                        rs.getString("apellido")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el usuario: " + e.getMessage());
        }

        return null;
    }

    // --------------------------CLIENTES------------------------------------
    
    public boolean registrarCliente(Cliente cliente) {
    if (clienteExiste(cliente.getDocumento())) {
        System.out.println("El cliente ya está registrado.");
        return false;
    }

    String sql = "INSERT INTO cliente (documento, nombre, telefono, razon_social) VALUES (?, ?, ?, ?)";

    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, cliente.getDocumento());
        pstmt.setString(2, cliente.getNombre());
        pstmt.setString(3, cliente.getTelefono());
        pstmt.setString(4, cliente.getRazonSocial());

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        System.out.println("Error al registrar el cliente: " + e.getMessage());
        return false;
    }
}
    
    public boolean clienteExiste(String documento) {
    String sql = "SELECT 1 FROM cliente WHERE documento = ?";

    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, documento);
        ResultSet rs = pstmt.executeQuery();
        return rs.next(); // true si existe
    } catch (SQLException e) {
        System.out.println("Error al verificar existencia del cliente: " + e.getMessage());
        return false;
    }
}

    public Cliente consultarCliente(String documento) {
    String sql = "SELECT * FROM cliente WHERE documento = ?";

    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, documento);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Cliente(
                rs.getString("documento"),
                rs.getString("nombre"),
                rs.getString("telefono"),
                rs.getString("razon_social")
            );
        } else {
            System.out.println("El cliente no existe.");
            return null;
        }

    } catch (SQLException e) {
        System.out.println("Error al consultar el cliente: " + e.getMessage());
        return null;
    }
}

    
    
    public boolean actualizarCliente(Cliente cliente) {
    if (!clienteExiste(cliente.getDocumento())) {
        System.out.println("El cliente no existe, no se puede actualizar.");
        return false;
    }

    String sql = "UPDATE cliente SET nombre = ?, telefono = ?, razon_social = ? WHERE documento = ?";

    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, cliente.getNombre());
        pstmt.setString(2, cliente.getTelefono());
        pstmt.setString(3, cliente.getRazonSocial());
        pstmt.setString(4, cliente.getDocumento());

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar el cliente: " + e.getMessage());
        return false;
    }
}


   public boolean eliminarCliente(String documento) {
    if (!clienteExiste(documento)) {
        System.out.println("El cliente no existe, no se puede eliminar.");
        return false;
    }

    String sql = "DELETE FROM cliente WHERE documento = ?";

    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, documento);
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Error al eliminar el cliente: " + e.getMessage());
        return false;
    }
}


    public List<MarcaItem> obtenerTodasLasMarcas() {
        List<MarcaItem> marcas = new ArrayList<>();
        // Consulta SQL para obtener id y nombre de la tabla marca
        String sql = "SELECT id_marca, nombre FROM public.marca ORDER BY nombre ASC";

        // Usar try-with-resources para asegurar el cierre automático
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                marcas.add(new MarcaItem(
                        rs.getInt("id_marca"),     
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las marcas: " + e.getMessage());
            // Podrías devolver una lista vacía o null, o lanzar una excepción
        }
        return marcas;
    }

     public List<ColorItem> obtenerTodosLosColores() {
        List<ColorItem> colores = new ArrayList<>();
        // Consulta SQL para obtener id y nombre de la tabla color
        String sql = "SELECT id_color, nombre FROM public.color ORDER BY nombre ASC";

        // Usar try-with-resources para asegurar el cierre automático
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                colores.add(new ColorItem(
                        rs.getInt("id_color"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los colores: " + e.getMessage());
            // Podrías devolver una lista vacía o null, o lanzar una excepción
        }
        return colores;
                
    }
     
     public List<MaterialItem> obtenerTodosLosMateriales() {
        List<MaterialItem> materiales = new ArrayList<>();
        // Consulta SQL para obtener id y nombre de la tabla marca
        String sql = "SELECT id_material, nombre FROM public.material ORDER BY nombre ASC";

        // Usar try-with-resources para asegurar el cierre automático
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                materiales.add(new MaterialItem(
                        rs.getInt("id_material"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los materiales: " + e.getMessage());
            // Podrías devolver una lista vacía o null, o lanzar una excepción
        }
        return materiales;
    }
     
     public boolean insertarNuevaMarca(String nombre) {
    String sql = "INSERT INTO marca (nombre) VALUES (?)";
    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nombre);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Error al insertar nueva marca: " + e.getMessage());
        return false;
    }
}

public boolean insertarNuevoColor(String nombre) {
    String sql = "INSERT INTO color (nombre) VALUES (?)";
    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nombre);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Error al insertar nuevo color: " + e.getMessage());
        return false;
    }
}

public boolean insertarNuevoMaterial(String nombre) {
    String sql = "INSERT INTO material (nombre) VALUES (?)";
    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nombre);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Error al insertar nuevo material: " + e.getMessage());
        return false;
    }
}
public boolean eliminarMarca(int idMarca) {
    String sql = "DELETE FROM marca WHERE id_marca = ?";
    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idMarca);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Error al eliminar marca: " + e.getMessage());
        return false;
    }
}

public boolean eliminarColor(int idColor) {
    String sql = "DELETE FROM color WHERE id_color = ?";
    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idColor);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Error al eliminar color: " + e.getMessage());
        return false;
    }
}

public boolean eliminarMaterial(int idMaterial) {
    String sql = "DELETE FROM material WHERE id_material = ?";
    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idMaterial);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Error al eliminar material: " + e.getMessage());
        return false;
    }
}



// Datos adiccionados


public boolean marcaEstaEnUso(int idMarca) {
    String sql = "SELECT COUNT(*) FROM producto WHERE marca = ?";
    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idMarca);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        System.out.println("Error al verificar uso de marca: " + e.getMessage());
    }
    return false;
}

public boolean colorEstaEnUso(int idColor) {
    String sql = "SELECT COUNT(*) FROM producto WHERE color = ?";
    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idColor);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        System.out.println("Error al verificar uso del color: " + e.getMessage());
    }
    return false;
}

public boolean materialEstaEnUso(int idMaterial) {
    String sql = "SELECT COUNT(*) FROM producto WHERE material = ?";
    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idMaterial);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        System.out.println("Error al verificar uso del material: " + e.getMessage());
    }
    return false;
    }  
}


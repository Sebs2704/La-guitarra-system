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
                 "producto.material, producto.marca, producto.nombre AS nombre_producto, producto.color, " + // p.nombre AS nombre_producto para evitar ambigüedad
                 "marca.nombre AS nombre_marca, marca.id_marca " + // Seleccionamos el nombre de la marca con un alias
                 "FROM public.producto " +
                 "LEFT JOIN public.marca ON producto.marca = marca.id_marca " + // Unimos las tablas por el ID de marca
                 "ORDER BY producto.codigo ASC";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                int material = rs.getInt("material");  // ahora es int
                String marcaNombre = rs.getString("nombre_marca"); 
                int marcaId = rs.getInt("id_marca");
                String nombre = rs.getString("nombre_producto");
                int color = rs.getInt("color");
                
                MarcaItem marcaitem = new MarcaItem(marcaId, marcaNombre);

                Producto producto = new Producto(codigo, descripcion, precio, cantidad, material, marcaitem, nombre, color);
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los productos: " + e.getMessage());
        }

        return productos;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT nombre_usuario, contraseña, nombre, apellido FROM usuario";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("nombre_usuario"),
                        rs.getString("contraseña"),
                        rs.getString("nombre"),
                        rs.getString("apellido")
                );
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    public Producto consultarProducto(int codigo) {
        String sql = "SELECT * FROM producto WHERE codigo = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String descripcion = rs.getString("descripcion");
                int precio = rs.getInt("precio");
                int cantidad = rs.getInt("cantidad");
                int material = rs.getInt("material");  // ahora es int
                String marcaNombre = rs.getString("nombre"); 
                int marcaId = rs.getInt("id_marca");
                String nombre = rs.getString("nombre");
                int color = rs.getInt("color");
                
                MarcaItem marcaitem = new MarcaItem(marcaId, marcaNombre);

                return new Producto(codigo, descripcion, precio, cantidad, material, marcaitem, nombre, color);
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
        String sql = "UPDATE usuario SET contraseña = ?, nombre = ?, apellido = ? WHERE nombre_usuario = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getContraseña()); // Asegúrate de encriptar la contraseña antes
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellido());
            pstmt.setString(4, usuario.getUsuario());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

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
        // Verificar si el usuario ya existe
        if (usuarioExiste(usuario.getUsuario())) {
            System.out.println("El usuario ya está registrado.");
            return false; // Retorna false si el usuario ya existe
        }
        String sql = "INSERT INTO usuario (nombre_usuario, contraseña, nombre, apellido) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getUsuario());
            pstmt.setString(2, usuario.getContraseña());
            pstmt.setString(3, usuario.getNombre());
            pstmt.setString(4, usuario.getApellido());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

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
            pstmt.setInt(5, producto.getMaterial()); // corregido
            pstmt.setInt(6, producto.getMarca().getIdMarca());    // corregido
            pstmt.setString(7, producto.getNombre());
            pstmt.setInt(8, producto.getColor());

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
            pstmt.setInt(4, producto.getMaterial()); // corregido
            pstmt.setInt(5, producto.getMarca().getIdMarca());    // corregido
            pstmt.setString(6, producto.getNombre());
            pstmt.setInt(7, producto.getColor());
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
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ?";
        Usuario usuario = null;

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getString("nombre_usuario"),
                        rs.getString("contraseña"),
                        rs.getString("nombre"),
                        rs.getString("apellido")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el usuario: " + e.getMessage());
        }

        return usuario;
    }

    public boolean registrarCliente(Cliente cliente) {
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

    public boolean actualizarCliente(Cliente cliente) {
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

}

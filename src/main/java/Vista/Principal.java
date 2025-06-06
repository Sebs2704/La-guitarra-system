
package Vista;

import Modelo.Producto;
import Modelo.Conexion;
import Modelo.MarcaItem;
import Modelo.MaterialItem;
import Modelo.ColorItem;
import Modelo.Usuario;
import Modelo.Cliente;
import Modelo.Venta;
import Modelo.DetalleVenta;
import java.awt.Color;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;


public class Principal extends javax.swing.JFrame {
    
    Conexion conexion = new Conexion();
    
    
    private void limpiarCampos() {
    CodigoTxT.setText("");
    DescripcionTxT.setText("");
    PrecioTxT.setText("");
    CantidadTxT.setText("");
    //MaterialTxT.setText("");
    //MarcaComboBOx.;
    NombTxT.setText("");
    //ColorTxT.setText("");
}
    private void limpiarCamposCliente() {
    DocTxT.setText("");
    NombreTxT.setText("");
    Apellido2TxT.setText("");
    TelefonoTxT.setText("");
    Razon_SocialTxT.setText("");
}

    
    
        private void cargarDatosComboBoxes()
        {
        cargarMarcasYTabla();
        cargarMaterialesYTabla(); // Llama si implementas el combo de material
        cargarColoresYTabla();    // Llama si implementas el combo de color
    }
    
    
     private void cargarMarcasYTabla() {
    // Cargar ComboBox (como ya lo haces)
    List<MarcaItem> marcas = conexion.obtenerTodasLasMarcas();
    DefaultComboBoxModel<MarcaItem> model = new DefaultComboBoxModel<>();

    if (marcas == null || marcas.isEmpty()) {
        System.out.println("No se encontraron marcas.");
    } else {
        for (MarcaItem item : marcas) {
            model.addElement(item);
        }
        MarcaComboBox.setEnabled(true);
    }

    MarcaComboBox.setModel(model);

    // Cargar JTable con las marcas
    DefaultTableModel tablaModelo = new DefaultTableModel();
    tablaModelo.addColumn("Marcas");

    for (MarcaItem item : marcas) {
        if (!item.getNombre().trim().isEmpty()) {
            tablaModelo.addRow(new Object[]{item.getNombre()});
        }
    }

    Marcastbld.setModel(tablaModelo);
}

     
     private void cargarMaterialesYTabla() {
    List<MaterialItem> materiales = conexion.obtenerTodosLosMateriales();
    DefaultComboBoxModel<MaterialItem> model = new DefaultComboBoxModel<>();

    for (MaterialItem m : materiales) {
        if (!m.getNombre().trim().isEmpty()) {
            model.addElement(m);
        }
    }

    MaterialComboBox.setModel(model);
    MaterialComboBox.setEnabled(!materiales.isEmpty());

    DefaultTableModel tablaModelo = new DefaultTableModel();
    tablaModelo.addColumn("Materiales");

    for (MaterialItem m : materiales) {
        if (!m.getNombre().trim().isEmpty()) {
            tablaModelo.addRow(new Object[]{m.getNombre()});
        }
    }

    Materialestbld.setModel(tablaModelo);
}

    
     private void cargarColoresYTabla() {
    List<ColorItem> colores = conexion.obtenerTodosLosColores();
    DefaultComboBoxModel<ColorItem> model = new DefaultComboBoxModel<>();

    for (ColorItem c : colores) {
        if (!c.getNombre().trim().isEmpty()) {
            model.addElement(c);
        }
    }

    ColorComboBox.setModel(model);
    ColorComboBox.setEnabled(!colores.isEmpty());

    DefaultTableModel tablaModelo = new DefaultTableModel();
    tablaModelo.addColumn("Colores");

    for (ColorItem c : colores) {
        if (!c.getNombre().trim().isEmpty()) {
            tablaModelo.addRow(new Object[]{c.getNombre()});
        }
    }

    Colorestbld.setModel(tablaModelo);
}

private void cargarProductosEnTabla() {
    DefaultTableModel modeloTabla = (DefaultTableModel) ProductosTbld.getModel();
    modeloTabla.setRowCount(0); // Limpiar la tabla

    List<Producto> productos = app.obtenerTodosLosProductos();
    
    for (Producto producto : productos) {
        modeloTabla.addRow(new Object[]{
            producto.getCodigo(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getCantidad(),
            producto.getMaterial(),
            producto.getMarca(),
            producto.getNombre(),
            producto.getColor()
        });
    }
}



private void cargarTablaClientes() {
    DefaultTableModel modelo = (DefaultTableModel) ClientesTbld.getModel();
    modelo.setRowCount(0);

    List<Cliente> clientes = conexion.obtenerTodosLosClientes();
    for (Cliente c : clientes) {
        modelo.addRow(new Object[]{
            c.getDocumento(),
            c.getNombre(),
            c.getApellido(),
            c.getTelefono(),
            c.getCorreo()
        });
    }
}


private void llenarTablaUsuarios() {
    // Crear un modelo de tabla con las columnas necesarias
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Nombre de Usuario");
    modelo.addColumn("Contraseña");  // Considera si deseas mostrarla directamente por seguridad
    modelo.addColumn("Nombre");
    modelo.addColumn("Apellido");

    // Obtener todos los datos de los usuarios desde la base de datos
    
    List<Usuario> usuarios = conexion.obtenerTodosLosUsuarios();

    // Limpiar las filas previas del modelo de la tabla
    modelo.setRowCount(0);

    // Agregar cada usuario al modelo de tabla
    for (Usuario usuario : usuarios) {
        modelo.addRow(new Object[]{
            usuario.getUsuario(),
            usuario.getContraseña(), 
            usuario.getNombre(),
            usuario.getApellido()
        });
    }

    // Asignar el modelo a la tabla UserTbld
    UserTbld.setModel(modelo);
    }

/*private void LlenarTabladatos() {
    DefaultTableModel modelo = new DefaultTableModel();
modelo.addColumn("Marcas");
modelo.addColumn("Colores");
modelo.addColumn("Materiales");

List<ColorItem> colores = conexion.obtenerTodosLosColores();
List<MaterialItem> materiales = conexion.obtenerTodosLosMateriales();
List<MarcaItem> marcas = conexion.obtenerTodasLasMarcas();


if (marcas.isEmpty() && colores.isEmpty() && materiales.isEmpty()) {
    Marcastbld.setModel(modelo); // Asignamos modelo vacío sin filas
    return;
}

int max = Math.max(marcas.size(), Math.max(colores.size(), materiales.size()));
for (int i = 0; i < max; i++) {
    String marca = (i < marcas.size()) ? marcas.get(i).getNombre() : "";
    String color = (i < colores.size()) ? colores.get(i).getNombre() : "";
    String material = (i < materiales.size()) ? materiales.get(i).getNombre() : "";
    // Evita agregar filas completamente vacías
    if (marca.isEmpty() && color.isEmpty() && material.isEmpty()) continue;
    modelo.addRow(new Object[]{marca, color, material});
}

// Asignar el modelo a la tabla
Marcastbld.setModel(modelo);
}*/

private void llenarTablaMarcas() {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Marcas");

    List<MarcaItem> marcas = conexion.obtenerTodasLasMarcas();
    for (MarcaItem m : marcas) {
        if (!m.getNombre().trim().isEmpty()) {  // Evita filas vacías
            modelo.addRow(new Object[]{m.getNombre()});
        }
    }

    Marcastbld.setModel(modelo);
}


private void llenarTablaColores() {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Colores");

    List<ColorItem> colores = conexion.obtenerTodosLosColores();
    for (ColorItem c : colores) {
        if (!c.getNombre().trim().isEmpty()) {
            modelo.addRow(new Object[]{c.getNombre()});
        }
    }

    Colorestbld.setModel(modelo);
}



private void llenarTablaMateriales() {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Materiales");

    List<MaterialItem> materiales = conexion.obtenerTodosLosMateriales();
    for (MaterialItem m : materiales) {
        if (!m.getNombre().trim().isEmpty()) {
            modelo.addRow(new Object[]{m.getNombre()});
        }
    }

    Materialestbld.setModel(modelo);
}


    Conexion app = new Conexion();
    int xMouse, yMouse;
    public Principal() {
        initComponents();//ingresar cualquier carga de metodos
        
        DefaultTableModel modelo = new DefaultTableModel(
    new Object[][]{}, // filas vacías al inicio
    new Object[]{"Producto", "Precio Unitario", "Cantidad", "Subtotal", "Total + IVA"} // ← ¡con llaves!
);
VentaTbld.setModel(modelo);
        
        
        
        cargarDatosComboBoxes();
        cargarProductosEnTabla();
        llenarTablaUsuarios();
        //LlenarTabladatos();
        llenarTablaMarcas();     
        llenarTablaColores();    
        llenarTablaMateriales(); 
        cargarTablaClientes();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        ClientesBtn = new javax.swing.JButton();
        ReparacionesBtn = new javax.swing.JButton();
        ExitBtn = new javax.swing.JPanel();
        ExitTxT = new javax.swing.JLabel();
        CerrarSesionBtn = new javax.swing.JButton();
        VentasBtn = new javax.swing.JButton();
        NuevaVentaBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        StockBtn = new javax.swing.JButton();
        UsuariosBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Tabla = new javax.swing.JTabbedPane();
        NuevaVnt = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        VentaTbld = new javax.swing.JTable();
        PagarBtn = new javax.swing.JButton();
        CodigoProductoTxT = new javax.swing.JTextField();
        CantidadProdTxT = new javax.swing.JTextField();
        Vendedor1TxT = new javax.swing.JTextField();
        NombreClienTxT = new javax.swing.JTextField();
        DocumentoClienTxT = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Clientes = new javax.swing.JPanel();
        Documento = new javax.swing.JLabel();
        DocTxT = new javax.swing.JTextField();
        Nombre = new javax.swing.JLabel();
        NombreTxT = new javax.swing.JTextField();
        Telefono = new javax.swing.JLabel();
        TelefonoTxT = new javax.swing.JTextField();
        Razon_Social = new javax.swing.JLabel();
        Razon_SocialTxT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        ClientesTbld = new javax.swing.JTable();
        Clientebtn = new javax.swing.JButton();
        ConClntbtn = new javax.swing.JButton();
        ElimClntbtn = new javax.swing.JButton();
        ModClntbtn = new javax.swing.JButton();
        Nombre1 = new javax.swing.JLabel();
        Apellido2TxT = new javax.swing.JTextField();
        Productos = new javax.swing.JPanel();
        Codigo = new javax.swing.JLabel();
        Descripcion = new javax.swing.JLabel();
        Precio = new javax.swing.JLabel();
        Cantidad = new javax.swing.JLabel();
        CodigoTxT = new javax.swing.JTextField();
        DescripcionTxT = new javax.swing.JTextField();
        PrecioTxT = new javax.swing.JTextField();
        CantidadTxT = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        ProductosTbld = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NombTxT = new javax.swing.JTextField();
        RegistrarBtn = new javax.swing.JButton();
        ConsultarBtn = new javax.swing.JButton();
        EliminarBtn = new javax.swing.JButton();
        ModificarBtn = new javax.swing.JButton();
        MarcaComboBox = new javax.swing.JComboBox<MarcaItem>();
        MaterialComboBox = new javax.swing.JComboBox<>();
        ColorComboBox = new javax.swing.JComboBox<>();
        Editardatosbutton = new javax.swing.JButton();
        Reparaciones = new javax.swing.JPanel();
        Venntas = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        VentasTbld = new javax.swing.JTable();
        VentaTxT = new javax.swing.JTextField();
        Venta = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ClienteTxT = new javax.swing.JTextField();
        VendedorTxT = new javax.swing.JTextField();
        Vendedor = new javax.swing.JLabel();
        TotalTxT = new javax.swing.JTextField();
        Total = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        Config = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        UserTbld = new javax.swing.JTable();
        TituloUsr = new javax.swing.JLabel();
        NombreUsr = new javax.swing.JLabel();
        NombreUsrTxT = new javax.swing.JTextField();
        UsuarioTxT = new javax.swing.JTextField();
        ContraseñaTxT = new javax.swing.JTextField();
        ApellidoTxT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Contraseña = new javax.swing.JLabel();
        Nombre_Usuario = new javax.swing.JLabel();
        RegisBtn = new javax.swing.JButton();
        ConsulBtn = new javax.swing.JButton();
        ElimBtn = new javax.swing.JButton();
        ActualizarBtn = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Marcastbld = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        MarcaTxT = new javax.swing.JTextField();
        AñadirMarBtn = new javax.swing.JButton();
        EliminarMarBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        Colorestbld = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        ColorTxT = new javax.swing.JTextField();
        AñadirColBtn = new javax.swing.JButton();
        EliminarColBtn = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Materialestbld = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        MaterialTxT = new javax.swing.JTextField();
        AñadirMatBtn = new javax.swing.JButton();
        EliminarMatBtn = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(181, 116, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(153, 82, 0));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        ClientesBtn.setBackground(new java.awt.Color(117, 80, 72));
        ClientesBtn.setForeground(new java.awt.Color(255, 255, 255));
        ClientesBtn.setText("Clientes");
        ClientesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ClientesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientesBtnActionPerformed(evt);
            }
        });

        ReparacionesBtn.setBackground(new java.awt.Color(117, 80, 72));
        ReparacionesBtn.setForeground(new java.awt.Color(255, 255, 255));
        ReparacionesBtn.setText("Reparaciones");
        ReparacionesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ReparacionesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReparacionesBtnActionPerformed(evt);
            }
        });

        ExitBtn.setBackground(new java.awt.Color(153, 102, 0));

        ExitTxT.setBackground(new java.awt.Color(255, 255, 255));
        ExitTxT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ExitTxT.setForeground(new java.awt.Color(255, 255, 255));
        ExitTxT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExitTxT.setText("X");
        ExitTxT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ExitTxT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitTxTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ExitTxTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ExitTxTMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ExitBtnLayout = new javax.swing.GroupLayout(ExitBtn);
        ExitBtn.setLayout(ExitBtnLayout);
        ExitBtnLayout.setHorizontalGroup(
            ExitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExitBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ExitTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ExitBtnLayout.setVerticalGroup(
            ExitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ExitTxT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        CerrarSesionBtn.setBackground(new java.awt.Color(117, 80, 72));
        CerrarSesionBtn.setForeground(new java.awt.Color(255, 255, 255));
        CerrarSesionBtn.setText("Cerrar sesion");
        CerrarSesionBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CerrarSesionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarSesionBtnActionPerformed(evt);
            }
        });

        VentasBtn.setBackground(new java.awt.Color(117, 80, 72));
        VentasBtn.setForeground(new java.awt.Color(255, 255, 255));
        VentasBtn.setText("Ventas");
        VentasBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VentasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentasBtnActionPerformed(evt);
            }
        });

        NuevaVentaBtn.setBackground(new java.awt.Color(117, 80, 72));
        NuevaVentaBtn.setForeground(new java.awt.Color(255, 255, 255));
        NuevaVentaBtn.setText("Nueva Venta");
        NuevaVentaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NuevaVentaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevaVentaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(NuevaVentaBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ClientesBtn)
                .addGap(63, 63, 63)
                .addComponent(ReparacionesBtn)
                .addGap(18, 18, 18)
                .addComponent(VentasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(CerrarSesionBtn)
                .addGap(52, 52, 52)
                .addComponent(ExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ExitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ReparacionesBtn)
                        .addComponent(ClientesBtn)
                        .addComponent(VentasBtn)
                        .addComponent(NuevaVentaBtn))
                    .addComponent(CerrarSesionBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 60));

        jPanel2.setBackground(new java.awt.Color(153, 95, 0));

        StockBtn.setBackground(new java.awt.Color(117, 80, 72));
        StockBtn.setForeground(new java.awt.Color(255, 255, 255));
        StockBtn.setText("Stock");
        StockBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        StockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockBtnActionPerformed(evt);
            }
        });

        UsuariosBtn.setBackground(new java.awt.Color(117, 80, 72));
        UsuariosBtn.setForeground(new java.awt.Color(255, 255, 255));
        UsuariosBtn.setText("Usuarios ");
        UsuariosBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UsuariosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariosBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(StockBtn)
                .addGap(154, 154, 154)
                .addComponent(UsuariosBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StockBtn)
                    .addComponent(UsuariosBtn))
                .addGap(467, 467, 467))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 800, 30));

        jPanel4.setBackground(new java.awt.Color(153, 101, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 800, 30));

        Tabla.setBackground(new java.awt.Color(187, 119, 0));

        NuevaVnt.setBackground(new java.awt.Color(187, 119, 0));

        jLabel12.setText("Fecha");

        VentaTbld.setBackground(new java.awt.Color(155, 96, 28));
        VentaTbld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(VentaTbld);

        PagarBtn.setBackground(new java.awt.Color(117, 80, 72));
        PagarBtn.setForeground(new java.awt.Color(255, 255, 255));
        PagarBtn.setText("PAGAR");
        PagarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PagarBtnActionPerformed(evt);
            }
        });

        CodigoProductoTxT.setBackground(new java.awt.Color(207, 128, 38));
        CodigoProductoTxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoProductoTxTActionPerformed(evt);
            }
        });
        CodigoProductoTxT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CodigoProductoTxTKeyPressed(evt);
            }
        });

        CantidadProdTxT.setBackground(new java.awt.Color(207, 128, 38));
        CantidadProdTxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadProdTxTActionPerformed(evt);
            }
        });
        CantidadProdTxT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CantidadProdTxTKeyPressed(evt);
            }
        });

        Vendedor1TxT.setBackground(new java.awt.Color(207, 128, 38));
        Vendedor1TxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Vendedor1TxTActionPerformed(evt);
            }
        });

        NombreClienTxT.setBackground(new java.awt.Color(207, 128, 38));

        DocumentoClienTxT.setBackground(new java.awt.Color(207, 128, 38));

        jTextField6.setBackground(new java.awt.Color(207, 128, 38));

        jLabel4.setText("CODIGO PRODUCTO");

        jLabel11.setText("Cantidad producto");

        jLabel14.setText("Nombre vendedor");

        jLabel15.setText("Nombre cliente");

        jLabel18.setText("Documento cliente");

        javax.swing.GroupLayout NuevaVntLayout = new javax.swing.GroupLayout(NuevaVnt);
        NuevaVnt.setLayout(NuevaVntLayout);
        NuevaVntLayout.setHorizontalGroup(
            NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevaVntLayout.createSequentialGroup()
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevaVntLayout.createSequentialGroup()
                        .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NuevaVntLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(NuevaVntLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NuevaVntLayout.createSequentialGroup()
                        .addContainerGap(95, Short.MAX_VALUE)
                        .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(CantidadProdTxT, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CodigoProductoTxT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81)))
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NuevaVntLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(NuevaVntLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Vendedor1TxT, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addGap(65, 65, 65)
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(NuevaVntLayout.createSequentialGroup()
                        .addComponent(NombreClienTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DocumentoClienTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NuevaVntLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PagarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        NuevaVntLayout.setVerticalGroup(
            NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevaVntLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(NuevaVntLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CodigoProductoTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CantidadProdTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Vendedor1TxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombreClienTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DocumentoClienTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PagarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        Tabla.addTab("Venta", NuevaVnt);

        Clientes.setBackground(new java.awt.Color(187, 119, 0));

        Documento.setText("CC / Documento de identidad ");

        DocTxT.setBackground(new java.awt.Color(207, 128, 38));
        DocTxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocTxTActionPerformed(evt);
            }
        });

        Nombre.setText("Nombre");

        NombreTxT.setBackground(new java.awt.Color(207, 128, 38));
        NombreTxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreTxTActionPerformed(evt);
            }
        });

        Telefono.setText("Telefono");

        TelefonoTxT.setBackground(new java.awt.Color(207, 128, 38));

        Razon_Social.setText("Correo electronico");

        Razon_SocialTxT.setBackground(new java.awt.Color(207, 128, 38));

        ClientesTbld.setBackground(new java.awt.Color(155, 96, 28));
        ClientesTbld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CC / Documento de identidad", "Nombre", "Telefono", "Correo"
            }
        ));
        jScrollPane2.setViewportView(ClientesTbld);

        Clientebtn.setBackground(new java.awt.Color(117, 80, 72));
        Clientebtn.setForeground(new java.awt.Color(255, 255, 255));
        Clientebtn.setText("Registrar");
        Clientebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientebtnActionPerformed(evt);
            }
        });

        ConClntbtn.setBackground(new java.awt.Color(117, 80, 72));
        ConClntbtn.setForeground(new java.awt.Color(255, 255, 255));
        ConClntbtn.setText("Consultar");
        ConClntbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConClntbtnActionPerformed(evt);
            }
        });

        ElimClntbtn.setBackground(new java.awt.Color(117, 80, 72));
        ElimClntbtn.setForeground(new java.awt.Color(255, 255, 255));
        ElimClntbtn.setText("Eliminar");
        ElimClntbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElimClntbtnActionPerformed(evt);
            }
        });

        ModClntbtn.setBackground(new java.awt.Color(117, 80, 72));
        ModClntbtn.setForeground(new java.awt.Color(255, 255, 255));
        ModClntbtn.setText("Modificar");
        ModClntbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModClntbtnActionPerformed(evt);
            }
        });

        Nombre1.setText("Apellido");

        Apellido2TxT.setBackground(new java.awt.Color(207, 128, 38));
        Apellido2TxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Apellido2TxTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ClientesLayout = new javax.swing.GroupLayout(Clientes);
        Clientes.setLayout(ClientesLayout);
        ClientesLayout.setHorizontalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClientesLayout.createSequentialGroup()
                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ClientesLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TelefonoTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(Razon_Social, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Razon_SocialTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Documento)
                                    .addGroup(ClientesLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(NombreTxT, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(DocTxT, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(Apellido2TxT)))))
                    .addGroup(ClientesLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(ElimClntbtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ModClntbtn))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(Clientebtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ConClntbtn)))
                        .addGap(68, 68, 68)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ClientesLayout.setVerticalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClientesLayout.createSequentialGroup()
                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ClientesLayout.createSequentialGroup()
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DocTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Documento))
                        .addGap(30, 30, 30)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Nombre)
                            .addComponent(NombreTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(Nombre1))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(Apellido2TxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TelefonoTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Telefono))
                        .addGap(40, 40, 40)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Razon_Social)
                            .addComponent(Razon_SocialTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Clientebtn)
                            .addComponent(ConClntbtn)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ElimClntbtn)
                    .addComponent(ModClntbtn))
                .addGap(24, 24, 24))
        );

        Tabla.addTab("Clientes", Clientes);

        Productos.setBackground(new java.awt.Color(187, 119, 0));

        Codigo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Codigo.setForeground(new java.awt.Color(255, 255, 255));
        Codigo.setText("Codigo");

        Descripcion.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Descripcion.setForeground(new java.awt.Color(255, 255, 255));
        Descripcion.setText("Descripción ");

        Precio.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Precio.setForeground(new java.awt.Color(255, 255, 255));
        Precio.setText("Precio");

        Cantidad.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Cantidad.setForeground(new java.awt.Color(255, 255, 255));
        Cantidad.setText("Cantidad");

        CodigoTxT.setBackground(new java.awt.Color(207, 128, 38));

        DescripcionTxT.setBackground(new java.awt.Color(207, 128, 38));

        PrecioTxT.setBackground(new java.awt.Color(207, 128, 38));

        CantidadTxT.setBackground(new java.awt.Color(207, 128, 38));

        ProductosTbld.setBackground(new java.awt.Color(155, 96, 28));
        ProductosTbld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Descripcion", "Precio", "Cantidad", "Material", "Marca", "Nombre", "Color"
            }
        ));
        jScrollPane3.setViewportView(ProductosTbld);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Material");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Marca");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Color");

        NombTxT.setBackground(new java.awt.Color(207, 128, 38));
        NombTxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombTxTActionPerformed(evt);
            }
        });

        RegistrarBtn.setBackground(new java.awt.Color(117, 80, 72));
        RegistrarBtn.setForeground(new java.awt.Color(255, 255, 255));
        RegistrarBtn.setText("Registrar");
        RegistrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarBtnActionPerformed(evt);
            }
        });

        ConsultarBtn.setBackground(new java.awt.Color(117, 80, 72));
        ConsultarBtn.setForeground(new java.awt.Color(255, 255, 255));
        ConsultarBtn.setText("Consultar");
        ConsultarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarBtnActionPerformed(evt);
            }
        });

        EliminarBtn.setBackground(new java.awt.Color(117, 80, 72));
        EliminarBtn.setForeground(new java.awt.Color(255, 255, 255));
        EliminarBtn.setText("Eliminar");
        EliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarBtnActionPerformed(evt);
            }
        });

        ModificarBtn.setBackground(new java.awt.Color(117, 80, 72));
        ModificarBtn.setForeground(new java.awt.Color(255, 255, 255));
        ModificarBtn.setText("Modificar");
        ModificarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarBtnActionPerformed(evt);
            }
        });

        MarcaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarcaComboBoxActionPerformed(evt);
            }
        });

        MaterialComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaterialComboBoxActionPerformed(evt);
            }
        });

        Editardatosbutton.setBackground(new java.awt.Color(117, 80, 72));
        Editardatosbutton.setForeground(new java.awt.Color(255, 255, 255));
        Editardatosbutton.setText("Editar Datos");
        Editardatosbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditardatosbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ProductosLayout = new javax.swing.GroupLayout(Productos);
        Productos.setLayout(ProductosLayout);
        ProductosLayout.setHorizontalGroup(
            ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProductosLayout.createSequentialGroup()
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(ProductosLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ProductosLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(CodigoTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(NombTxT)))
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(PrecioTxT))
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addComponent(Descripcion)
                                .addGap(12, 12, 12)
                                .addComponent(DescripcionTxT))
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addComponent(Cantidad)
                                .addGap(12, 12, 12)
                                .addComponent(CantidadTxT)))
                        .addGap(18, 18, 18))
                    .addGroup(ProductosLayout.createSequentialGroup()
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(MaterialComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProductosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ColorComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(MarcaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(ProductosLayout.createSequentialGroup()
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addComponent(RegistrarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ConsultarBtn))
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addComponent(EliminarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ModificarBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Editardatosbutton)
                        .addGap(12, 12, 12)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ProductosLayout.setVerticalGroup(
            ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductosLayout.createSequentialGroup()
                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ProductosLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Codigo)
                            .addComponent(CodigoTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(NombTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Precio)
                            .addComponent(PrecioTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CantidadTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cantidad))
                        .addGap(18, 18, 18)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(MaterialComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(MarcaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Descripcion)
                            .addComponent(DescripcionTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(ColorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(RegistrarBtn)
                                    .addComponent(ConsultarBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EliminarBtn)
                                    .addComponent(ModificarBtn)))
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(Editardatosbutton)))
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addGroup(ProductosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        Tabla.addTab("Stock", Productos);

        Reparaciones.setBackground(new java.awt.Color(187, 119, 0));

        javax.swing.GroupLayout ReparacionesLayout = new javax.swing.GroupLayout(Reparaciones);
        Reparaciones.setLayout(ReparacionesLayout);
        ReparacionesLayout.setHorizontalGroup(
            ReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        ReparacionesLayout.setVerticalGroup(
            ReparacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        Tabla.addTab("Reparaciones", Reparaciones);

        Venntas.setBackground(new java.awt.Color(187, 119, 0));

        VentasTbld.setBackground(new java.awt.Color(155, 96, 28));
        VentasTbld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Vendedor", "Total"
            }
        ));
        jScrollPane4.setViewportView(VentasTbld);

        VentaTxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VentaTxTActionPerformed(evt);
            }
        });

        Venta.setForeground(new java.awt.Color(255, 255, 255));
        Venta.setText("Numero de venta (ID)");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cliente");

        ClienteTxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClienteTxTActionPerformed(evt);
            }
        });

        Vendedor.setForeground(new java.awt.Color(255, 255, 255));
        Vendedor.setText("Vendedor");

        Total.setForeground(new java.awt.Color(255, 255, 255));
        Total.setText("Total");

        jButton6.setBackground(new java.awt.Color(117, 80, 72));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Consultar");

        jButton7.setBackground(new java.awt.Color(117, 80, 72));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Modificar");

        jButton8.setBackground(new java.awt.Color(117, 80, 72));
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Eliminar");

        jButton9.setBackground(new java.awt.Color(117, 80, 72));
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Analisis");

        javax.swing.GroupLayout VenntasLayout = new javax.swing.GroupLayout(Venntas);
        Venntas.setLayout(VenntasLayout);
        VenntasLayout.setHorizontalGroup(
            VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VenntasLayout.createSequentialGroup()
                .addGroup(VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                    .addGroup(VenntasLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(VentaTxT)
                                .addComponent(Venta, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                            .addComponent(jButton6))
                        .addGap(18, 18, 18)
                        .addGroup(VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(VenntasLayout.createSequentialGroup()
                                .addComponent(ClienteTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(VendedorTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(VenntasLayout.createSequentialGroup()
                                .addGroup(VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7))
                                .addGap(18, 18, 18)
                                .addGroup(VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(VenntasLayout.createSequentialGroup()
                                        .addComponent(jButton8)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(Vendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TotalTxT)
                            .addGroup(VenntasLayout.createSequentialGroup()
                                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VenntasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        VenntasLayout.setVerticalGroup(
            VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VenntasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(2, 2, 2)
                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addGroup(VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Venta)
                    .addComponent(jLabel8)
                    .addComponent(Vendedor)
                    .addComponent(Total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VenntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VentaTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClienteTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendedorTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Tabla.addTab("Ventas", Venntas);

        Config.setBackground(new java.awt.Color(187, 119, 0));

        UserTbld.setBackground(new java.awt.Color(155, 96, 28));
        UserTbld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Contraseña", "Nombre", "Apellido"
            }
        ));
        jScrollPane6.setViewportView(UserTbld);

        TituloUsr.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TituloUsr.setForeground(new java.awt.Color(255, 255, 255));
        TituloUsr.setText("Sistema de Usuarios");

        NombreUsr.setForeground(new java.awt.Color(255, 255, 255));
        NombreUsr.setText("Nombre");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Apellido");

        Contraseña.setForeground(new java.awt.Color(255, 255, 255));
        Contraseña.setText("Contraseña");

        Nombre_Usuario.setForeground(new java.awt.Color(255, 255, 255));
        Nombre_Usuario.setText("Nombre de usuario");

        RegisBtn.setBackground(new java.awt.Color(117, 80, 72));
        RegisBtn.setForeground(new java.awt.Color(255, 255, 255));
        RegisBtn.setText("Registrar");
        RegisBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisBtnActionPerformed(evt);
            }
        });

        ConsulBtn.setBackground(new java.awt.Color(117, 80, 72));
        ConsulBtn.setForeground(new java.awt.Color(255, 255, 255));
        ConsulBtn.setText("Consultar");
        ConsulBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsulBtnActionPerformed(evt);
            }
        });

        ElimBtn.setBackground(new java.awt.Color(117, 80, 72));
        ElimBtn.setForeground(new java.awt.Color(255, 255, 255));
        ElimBtn.setText("Eliminar");
        ElimBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElimBtnActionPerformed(evt);
            }
        });

        ActualizarBtn.setBackground(new java.awt.Color(117, 80, 72));
        ActualizarBtn.setForeground(new java.awt.Color(255, 255, 255));
        ActualizarBtn.setText("Actualizar");
        ActualizarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConfigLayout = new javax.swing.GroupLayout(Config);
        Config.setLayout(ConfigLayout);
        ConfigLayout.setHorizontalGroup(
            ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfigLayout.createSequentialGroup()
                .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConfigLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TituloUsr, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(ConfigLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Nombre_Usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Contraseña, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(NombreUsr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(RegisBtn)
                            .addComponent(ElimBtn))
                        .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ConfigLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ContraseñaTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(UsuarioTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NombreUsrTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ApellidoTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConfigLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ActualizarBtn)
                                    .addComponent(ConsulBtn))
                                .addGap(43, 43, 43)))))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ConfigLayout.setVerticalGroup(
            ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
            .addGroup(ConfigLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(TituloUsr)
                .addGap(38, 38, 38)
                .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsuarioTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nombre_Usuario))
                .addGap(18, 18, 18)
                .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ContraseñaTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Contraseña))
                .addGap(21, 21, 21)
                .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NombreUsr)
                    .addComponent(NombreUsrTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ApellidoTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(58, 58, 58)
                .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RegisBtn)
                    .addComponent(ConsulBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(ConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ElimBtn)
                    .addComponent(ActualizarBtn))
                .addGap(22, 22, 22))
        );

        Tabla.addTab("Usuarios", Config);

        jPanel5.setBackground(new java.awt.Color(187, 119, 0));

        Marcastbld.setBackground(new java.awt.Color(155, 96, 28));
        Marcastbld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(Marcastbld);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Adiccionar datos");

        MarcaTxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarcaTxTActionPerformed(evt);
            }
        });

        AñadirMarBtn.setText("Añadir");
        AñadirMarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirMarBtnActionPerformed(evt);
            }
        });

        EliminarMarBtn.setText("Eliminar");
        EliminarMarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarMarBtnActionPerformed(evt);
            }
        });

        jLabel10.setText("Marca");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 82, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(MarcaTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(AñadirMarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EliminarMarBtn)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(MarcaTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AñadirMarBtn)
                    .addComponent(EliminarMarBtn))
                .addGap(96, 96, 96))
        );

        jTabbedPane1.addTab("Marca", jPanel5);

        jPanel9.setBackground(new java.awt.Color(187, 119, 0));

        Colorestbld.setBackground(new java.awt.Color(155, 96, 28));
        Colorestbld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(Colorestbld);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Adiccionar datos");

        AñadirColBtn.setText("Añadir");
        AñadirColBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirColBtnActionPerformed(evt);
            }
        });

        EliminarColBtn.setText("Eliminar");
        EliminarColBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarColBtnActionPerformed(evt);
            }
        });

        jLabel19.setText("Color");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 80, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ColorTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(AñadirColBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EliminarColBtn)
                        .addGap(24, 24, 24)))
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(28, 28, 28)
                .addComponent(ColorTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EliminarColBtn)
                    .addComponent(AñadirColBtn))
                .addGap(84, 84, 84))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Color", jPanel6);

        jPanel8.setBackground(new java.awt.Color(187, 119, 0));

        Materialestbld.setBackground(new java.awt.Color(155, 96, 28));
        Materialestbld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(Materialestbld);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Adiccionar datos");

        AñadirMatBtn.setText("Añadir");
        AñadirMatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñadirMatBtnActionPerformed(evt);
            }
        });

        EliminarMatBtn.setText("Eliminar");
        EliminarMatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarMatBtnActionPerformed(evt);
            }
        });

        jLabel16.setText("Material");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(AñadirMatBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(EliminarMatBtn))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap(67, Short.MAX_VALUE)
                        .addComponent(MaterialTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel13)
                .addGap(75, 75, 75)
                .addComponent(jLabel16)
                .addGap(26, 26, 26)
                .addComponent(MaterialTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AñadirMatBtn)
                    .addComponent(EliminarMatBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Material", jPanel8);

        Tabla.addTab("Datos", jTabbedPane1);

        jPanel1.add(Tabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 800, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReparacionesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReparacionesBtnActionPerformed
        Tabla.setSelectedIndex(3); // Abre Tab 5 (Ventas)
    }//GEN-LAST:event_ReparacionesBtnActionPerformed

    private void ExitTxTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitTxTMouseClicked
        System.exit(0);
    }//GEN-LAST:event_ExitTxTMouseClicked

    private void ExitTxTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitTxTMouseEntered
        ExitBtn.setBackground(Color.red);
    }//GEN-LAST:event_ExitTxTMouseEntered

    private void ExitTxTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitTxTMouseExited
        ExitBtn.setBackground(new Color(153,102,0));
    }//GEN-LAST:event_ExitTxTMouseExited

    private void VentasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentasBtnActionPerformed
       Tabla.setSelectedIndex(4); // Abre Tab 5 (Ventas)
    }//GEN-LAST:event_VentasBtnActionPerformed

    private void NuevaVentaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevaVentaBtnActionPerformed
        Tabla.setSelectedIndex(0);
    }//GEN-LAST:event_NuevaVentaBtnActionPerformed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        int x= evt.getXOnScreen();
        int y= evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanel3MousePressed

    private void CerrarSesionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarSesionBtnActionPerformed
int confirm = JOptionPane.showConfirmDialog(
    this,
    "¿Estás seguro que deseas cerrar sesión?",
    "Confirmar cierre de sesión",
    JOptionPane.YES_NO_OPTION,
    JOptionPane.QUESTION_MESSAGE
);

if (confirm == JOptionPane.YES_OPTION) {
    this.dispose(); // Cierra la ventana actual
    Login login = new Login(); 
    login.setVisible(true); // Abre la ventana de login
}
    }//GEN-LAST:event_CerrarSesionBtnActionPerformed

    private void ClientesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientesBtnActionPerformed
        Tabla.setSelectedIndex(1);
    }//GEN-LAST:event_ClientesBtnActionPerformed

    private void StockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockBtnActionPerformed
        Tabla.setSelectedIndex(2);
    }//GEN-LAST:event_StockBtnActionPerformed

    private void UsuariosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosBtnActionPerformed
        Tabla.setSelectedIndex(5);
    }//GEN-LAST:event_UsuariosBtnActionPerformed

    private void ActualizarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarBtnActionPerformed
        // Obtener los datos ingresados en los campos de texto
        String nombreUsuario = UsuarioTxT.getText();
        String nuevaContraseña = new String(ContraseñaTxT.getText());
        String nombre = NombreUsrTxT.getText();
        String apellido = ApellidoTxT.getText();

        // Verificar que el nombre de usuario no esté vacío (es nuestro identificador único)
        if (nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre de usuario a actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verificar que los demás campos no estén vacíos
        if (nuevaContraseña.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Crear una instancia del usuario con los datos actualizados
        Usuario usuario = new Usuario(nombreUsuario, nuevaContraseña, nombre, apellido);

        // Intentar actualizar el usuario en la base de datos
        boolean actualizado = conexion.actualizarUsuario(usuario);
        llenarTablaUsuarios();
        // Mostrar mensaje basado en el resultado de la actualización
        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el usuario. Verifique si el usuario existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ActualizarBtnActionPerformed

    private void ElimBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ElimBtnActionPerformed
        // Obtener el nombre de usuario ingresado
        String nombreUsuario = UsuarioTxT.getText();

        // Verificar que el campo no esté vacío
        if (nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre de usuario a eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmación antes de eliminar
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Intentar eliminar el usuario en la base de datos
            boolean eliminado = conexion.eliminarUsuario(nombreUsuario);

            // Mostrar mensaje basado en el resultado de la eliminación
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                llenarTablaUsuarios();
                limpiarCampos();
                // Limpiar el campo de texto después de la eliminación
                UsuarioTxT.setText("");
                ContraseñaTxT.setText("");
                NombreUsrTxT.setText("");
                ApellidoTxT.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el usuario. Verifique si el usuario existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_ElimBtnActionPerformed

    private void ConsulBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsulBtnActionPerformed
        // Obtener el nombre de usuario ingresado
        String nombreUsuario = UsuarioTxT.getText();

        // Verificar que el campo no esté vacío
        if (nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre de usuario a consultar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = conexion.obtenerUsuarioPorNombre(nombreUsuario);

        // Verificar si el usuario existe
        if (usuario != null) {
            // Mostrar los datos del usuario en los campos de texto
            ContraseñaTxT.setText(usuario.getContraseña());  // Puede que quieras encriptar o no mostrar directamente la contraseña
            NombreUsrTxT.setText(usuario.getNombre());
            ApellidoTxT.setText(usuario.getApellido());
            JOptionPane.showMessageDialog(this, "Usuario encontrado", "Consulta exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Mostrar mensaje si el usuario no existe
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);

            // Limpiar los campos en caso de que el usuario no exista
            ContraseñaTxT.setText("");
            NombreUsrTxT.setText("");
            ApellidoTxT.setText("");
        }
    }//GEN-LAST:event_ConsulBtnActionPerformed

    private void RegisBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisBtnActionPerformed
        // Crear una instancia de la interfaz Registro
        Registro registroInterfaz = new Registro();

        // Hacer visible la interfaz de registro
        registroInterfaz.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_RegisBtnActionPerformed

    private void ClienteTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClienteTxTActionPerformed

    private void VentaTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentaTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VentaTxTActionPerformed

    private void MaterialComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaterialComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaterialComboBoxActionPerformed

    private void MarcaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarcaComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MarcaComboBoxActionPerformed

    private void ModificarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarBtnActionPerformed
        try {
            int codigo = Integer.parseInt(CodigoTxT.getText());
            String descripcion = DescripcionTxT.getText();
            int precio = Integer.parseInt(PrecioTxT.getText());
            int cantidad = Integer.parseInt(CantidadTxT.getText());
            String nombre = NombTxT.getText();

            // Obtener los objetos seleccionados en los ComboBox
            MaterialItem materialItem = (MaterialItem) MaterialComboBox.getSelectedItem();
            MarcaItem marcaItem = (MarcaItem) MarcaComboBox.getSelectedItem();
            ColorItem colorItem = (ColorItem) ColorComboBox.getSelectedItem();

            if (nombre.isEmpty() || materialItem == null || marcaItem == null || colorItem == null) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Crear el objeto Producto con objetos completos
            Producto producto = new Producto(
                codigo,
                descripcion,
                precio,
                cantidad,
                materialItem,
                marcaItem,
                nombre,
                colorItem
            );

            if (app.modificarProducto(producto)) {
                JOptionPane.showMessageDialog(this, "Producto modificado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarProductosEnTabla(); // Actualizar la tabla
                limpiarCampos(); // Limpia los campos luego de actualizar
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar el producto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos para Código, Precio y Cantidad", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ModificarBtnActionPerformed

    private void EliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarBtnActionPerformed
        // Verificar si el campo de código está vacío
        if (CodigoTxT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el código del producto a eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int codigo;
        try {
            // Obtener el código del producto a eliminar
            codigo = Integer.parseInt(CodigoTxT.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un código válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirmar la eliminación
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Llamar al método de eliminar producto
            if (app.eliminarProducto(codigo)) {
                JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarProductosEnTabla(); // Actualizar la tabla después de eliminar el producto
                limpiarCampos(); // Limpiar los campos de texto después de la eliminación
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto o el producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_EliminarBtnActionPerformed

    private void ConsultarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarBtnActionPerformed
        int codigo;
        try {
            codigo = Integer.parseInt(CodigoTxT.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un código válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Producto producto = app.consultarProducto(codigo);

        if (producto != null) {
            // Llenar los campos con la información del producto
            DescripcionTxT.setText(producto.getDescripcion());
            PrecioTxT.setText(String.valueOf(producto.getPrecio()));
            CantidadTxT.setText(String.valueOf(producto.getCantidad()));
            NombTxT.setText(producto.getNombre());

            // Establecer selección en los ComboBox
            MaterialComboBox.setSelectedItem(producto.getMaterial());
            MarcaComboBox.setSelectedItem(producto.getMarca());
            ColorComboBox.setSelectedItem(producto.getColor());

        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_ConsultarBtnActionPerformed

    private void RegistrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarBtnActionPerformed
        // Obtener los datos de los campos de texto
        String codigo = CodigoTxT.getText();
        String descripcion = DescripcionTxT.getText();
        String precio = PrecioTxT.getText();
        String cantidad = CantidadTxT.getText();
        String nombre = NombTxT.getText();

        Object selectedMarcaObj = MarcaComboBox.getSelectedItem();
        Object selectedMaterialObj = MaterialComboBox.getSelectedItem();
        Object selectedColorObj = ColorComboBox.getSelectedItem();

        // Verificar que todos los campos estén llenos
        if (codigo.isEmpty() || descripcion.isEmpty() || precio.isEmpty() || cantidad.isEmpty() || selectedMaterialObj.toString().isEmpty() || selectedMarcaObj.toString().isEmpty() || nombre.isEmpty() || selectedColorObj.toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellenar todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {

            MarcaItem idItem = (MarcaItem) selectedMarcaObj;
            MaterialItem materialItem = (MaterialItem) selectedMaterialObj;
            ColorItem colorItem = (ColorItem) selectedColorObj;
            // Convertir campos numéricos
            int codigoInt = Integer.parseInt(codigo);
            int precioInt = Integer.parseInt(precio);
            int cantidadInt = Integer.parseInt(cantidad);
            int materialInt = materialItem.getIdMaterial(); // corregido
            int marcaInt = idItem.getIdMarca();       // corregido
            int colorInt = colorItem.getIdColor();       // corregido

            // Crear el objeto Producto
            Producto nuevoProducto = new Producto(codigoInt, descripcion, precioInt, cantidadInt, materialInt, marcaInt, nombre, colorInt);

            // Intentar registrar el producto
            boolean registrado = app.registrarProducto(nuevoProducto);

            // Mostrar mensaje basado en el resultado del registro
            if (registrado) {
                JOptionPane.showMessageDialog(this, "Producto registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                DefaultTableModel modeloTabla = (DefaultTableModel) ProductosTbld.getModel();
                modeloTabla.addRow(new Object[]{
                    nuevoProducto.getCodigo(),
                    nuevoProducto.getDescripcion(),
                    nuevoProducto.getPrecio(),
                    nuevoProducto.getCantidad(),
                    nuevoProducto.getMaterial(),
                    nuevoProducto.getMarca(),
                    nuevoProducto.getNombre(),
                    nuevoProducto.getColor()
                });
                limpiarCampos(); // Limpiar los campos después de registrar
            } else {
                if (app.productoExiste(codigoInt)) {
                    JOptionPane.showMessageDialog(this, "El producto ya está registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el producto", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para Código, Precio, Cantidad, Material, Marca y Color", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_RegistrarBtnActionPerformed

    private void ClientebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientebtnActionPerformed
        String documento = DocTxT.getText();
    String nombre = NombreTxT.getText();
    String apellido = Apellido2TxT.getText(); // Campo separado
    String telefono = TelefonoTxT.getText();
    String correo = Razon_SocialTxT.getText(); // Este campo es el correo

    // Validación de campos vacíos
    if (documento.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Validar que el documento contenga solo dígitos (sin convertirlo a int)
    if (!documento.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "El campo CC debe contener solo números.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 1. Registrar persona
    int idPersona = conexion.registrarPersona(nombre, apellido);
    if (idPersona == -1) {
        JOptionPane.showMessageDialog(this, "Error al registrar la persona.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 2. Crear cliente
    Cliente cliente = new Cliente(documento, nombre, apellido, telefono, correo, idPersona);

    // 3. Registrar cliente
    if (conexion.registrarCliente(cliente)) {
        JOptionPane.showMessageDialog(this, "Cliente registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCamposCliente();
        cargarTablaClientes(); // Recarga visual en tiempo real
    } else {
        JOptionPane.showMessageDialog(this, "El cliente ya está registrado o hubo un error.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_ClientebtnActionPerformed

    private void DocTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DocTxTActionPerformed

    private void EditardatosbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditardatosbuttonActionPerformed
Tabla.setSelectedIndex(6);    }//GEN-LAST:event_EditardatosbuttonActionPerformed

    private void NombTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombTxTActionPerformed

    private void EliminarMatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarMatBtnActionPerformed
String nombreMaterial = MaterialTxT.getText().trim();

    if (nombreMaterial.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese el nombre del material que desea eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    List<MaterialItem> materiales = conexion.obtenerTodosLosMateriales();
    MaterialItem materialAEliminar = null;

    for (MaterialItem m : materiales) {
        if (m.getNombre().equalsIgnoreCase(nombreMaterial)) {
            materialAEliminar = m;
            break;
        }
    }

    if (materialAEliminar == null) {
        JOptionPane.showMessageDialog(this, "El material '" + nombreMaterial + "' no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    if (conexion.materialEstaEnUso(materialAEliminar.getIdMaterial())) {
        JOptionPane.showMessageDialog(this, "No se puede eliminar el material porque está siendo usado en productos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el material '" + nombreMaterial + "'?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        boolean eliminado = conexion.eliminarMaterial(materialAEliminar.getIdMaterial());
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Material eliminado correctamente.");
            MaterialTxT.setText("");
            cargarMaterialesYTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar el material.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    }//GEN-LAST:event_EliminarMatBtnActionPerformed

    private void AñadirMatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirMatBtnActionPerformed
   String nuevoMaterial = MaterialTxT.getText().trim();

    if (nuevoMaterial.isEmpty() || 
        nuevoMaterial.equalsIgnoreCase("nombre") || 
        !nuevoMaterial.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {

        JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre válido (solo letras) para el material.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    boolean existe = conexion.obtenerTodosLosMateriales().stream()
                     .anyMatch(m -> m.getNombre().equalsIgnoreCase(nuevoMaterial));

    if (existe) {
        JOptionPane.showMessageDialog(this, "El material '" + nuevoMaterial + "' ya existe.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    } else {
        boolean insertado = conexion.insertarNuevoMaterial(nuevoMaterial);
        if (insertado) {
            cargarMaterialesYTabla();
            JOptionPane.showMessageDialog(this, "Material añadido correctamente.");
            MaterialTxT.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al insertar el material.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_AñadirMatBtnActionPerformed

    private void EliminarMarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarMarBtnActionPerformed
       String nombreColor = ColorTxT.getText().trim();

    if (nombreColor.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese el nombre del color que desea eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    List<ColorItem> colores = conexion.obtenerTodosLosColores();
    ColorItem colorAEliminar = null;

    for (ColorItem c : colores) {
        if (c.getNombre().equalsIgnoreCase(nombreColor)) {
            colorAEliminar = c;
            break;
        }
    }

    if (colorAEliminar == null) {
        JOptionPane.showMessageDialog(this, "El color '" + nombreColor + "' no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    if (conexion.colorEstaEnUso(colorAEliminar.getIdColor())) {
        JOptionPane.showMessageDialog(this, "No se puede eliminar el color porque está siendo usado en productos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el color '" + nombreColor + "'?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        boolean eliminado = conexion.eliminarColor(colorAEliminar.getIdColor());
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Color eliminado correctamente.");
            ColorTxT.setText("");
            cargarColoresYTabla();  // Refresca combo y tabla
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar el color.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_EliminarMarBtnActionPerformed

    private void AñadirMarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirMarBtnActionPerformed
       String nuevaMarca = MarcaTxT.getText().trim();

    if (nuevaMarca.isEmpty() || 
        nuevaMarca.equalsIgnoreCase("nombre") || 
        !nuevaMarca.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
        
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre válido (solo letras) para la marca.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    boolean existe = conexion.obtenerTodasLasMarcas().stream()
                     .anyMatch(m -> m.getNombre().equalsIgnoreCase(nuevaMarca));

    if (existe) {
        JOptionPane.showMessageDialog(this, "La marca '" + nuevaMarca + "' ya existe.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    } else {
        boolean insertado = conexion.insertarNuevaMarca(nuevaMarca);
        if (insertado) {
            cargarMarcasYTabla();
            JOptionPane.showMessageDialog(this, "Marca añadida correctamente.");
            MarcaTxT.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al insertar la marca.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_AñadirMarBtnActionPerformed

    private void MarcaTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarcaTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MarcaTxTActionPerformed

    private void AñadirColBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñadirColBtnActionPerformed
 String nuevoColor = ColorTxT.getText().trim();

    if (nuevoColor.isEmpty() || 
        nuevoColor.equalsIgnoreCase("nombre") || 
        !nuevoColor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {

        JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre válido (solo letras) para el color.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    boolean existe = conexion.obtenerTodosLosColores().stream()
                     .anyMatch(c -> c.getNombre().equalsIgnoreCase(nuevoColor));

    if (existe) {
        JOptionPane.showMessageDialog(this, "El color '" + nuevoColor + "' ya existe.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    } else {
        boolean insertado = conexion.insertarNuevoColor(nuevoColor);
        if (insertado) {
            cargarColoresYTabla();
            JOptionPane.showMessageDialog(this, "Color añadido correctamente.");
            ColorTxT.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al insertar el color.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }      
    }//GEN-LAST:event_AñadirColBtnActionPerformed

    private void EliminarColBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarColBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EliminarColBtnActionPerformed

    private void ConClntbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConClntbtnActionPerformed
    String documento = DocTxT.getText();

    if (documento.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese el número de CC para consultar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Línea corregida: ahora se usa el String directamente
    Cliente cliente = conexion.consultarCliente(documento); 

    if (cliente != null) {
        NombreTxT.setText(cliente.getNombre());
        Apellido2TxT.setText(cliente.getApellido());
        TelefonoTxT.setText(cliente.getTelefono());
        Razon_SocialTxT.setText(cliente.getCorreo());
    } else {
        JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
    
    }    }//GEN-LAST:event_ConClntbtnActionPerformed

    private void ElimClntbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ElimClntbtnActionPerformed
 String documento = DocTxT.getText();

    if (documento.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese el documento del cliente a eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        String cc = documento; // 👈 conversión a int

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (conexion.eliminarCliente(cc)) { // 👈 ahora pasa el int correcto
                JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposCliente();
                cargarTablaClientes();
            } else {
                JOptionPane.showMessageDialog(this, "El cliente no existe o hubo un error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El documento debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_ElimClntbtnActionPerformed

    private void ModClntbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModClntbtnActionPerformed
    String documento = DocTxT.getText();
    String nombre = NombreTxT.getText();
    String apellido = Apellido2TxT.getText();
    String telefono = TelefonoTxT.getText();
    String correo = Razon_SocialTxT.getText();

    if (documento.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Complete todos los campos para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Consultamos primero para obtener el idPersona
    Cliente clienteExistente = conexion.consultarCliente(documento);
    if (clienteExistente == null) {
        JOptionPane.showMessageDialog(this, "El cliente no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Crear cliente actualizado con todos los campos
    Cliente cliente = new Cliente(
        documento,
        nombre,
        apellido,
        telefono,
        correo,
        clienteExistente.getIdPersona() // ← esta es la clave para actualizar la tabla persona
    );

    if (conexion.actualizarCliente(cliente)) {
        JOptionPane.showMessageDialog(this, "Cliente modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarCamposCliente();
        cargarTablaClientes();
    } else {
        JOptionPane.showMessageDialog(this, "Hubo un error al modificar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);

    }    }//GEN-LAST:event_ModClntbtnActionPerformed

    private void Apellido2TxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Apellido2TxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Apellido2TxTActionPerformed

    private void NombreTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreTxTActionPerformed

    private void Vendedor1TxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Vendedor1TxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Vendedor1TxTActionPerformed

    private void CodigoProductoTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoProductoTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodigoProductoTxTActionPerformed

    private Producto productoActual = null; // Variable global temporal para guardar el producto consultado
    private void CodigoProductoTxTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoProductoTxTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        try {
            int codigo = Integer.parseInt(CodigoProductoTxT.getText());
            productoActual = conexion.consultarProducto(codigo);

            if (productoActual == null) {
                JOptionPane.showMessageDialog(this, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Agrega el nombre y precio del producto a la tabla (cantidad aún no)
            DefaultTableModel modelo = (DefaultTableModel) VentaTbld.getModel();
            modelo.addRow(new Object[]{
                productoActual.getNombre(),    // Nombre
                productoActual.getPrecio(),    // Precio unitario
                "",                            // Cantidad vacía
                "",                            // Subtotal vacío
                ""                             // Total con IVA vacío
            });

            // Enfocar en cantidad
            CantidadProdTxT.requestFocus();

            // Limpiar el campo de código
            CodigoProductoTxT.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Código inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_CodigoProductoTxTKeyPressed

    private void CantidadProdTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadProdTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadProdTxTActionPerformed

    private void CantidadProdTxTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadProdTxTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        try {
            int cantidad = Integer.parseInt(CantidadProdTxT.getText());

            if (productoActual == null) {
                JOptionPane.showMessageDialog(this, "Primero ingrese el código del producto", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double precio = productoActual.getPrecio();
            double subtotal = cantidad * precio;
            double totalIVA = subtotal * 1.19;

            DefaultTableModel modelo = (DefaultTableModel) VentaTbld.getModel();
            int lastRow = modelo.getRowCount() - 1;

            modelo.setValueAt(cantidad, lastRow, 2);   // Cantidad
            modelo.setValueAt(subtotal, lastRow, 3);   // Subtotal
            modelo.setValueAt(totalIVA, lastRow, 4);   // Total + IVA

            // Limpiar cantidad y reiniciar productoActual
            CantidadProdTxT.setText("");
            productoActual = null;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_CantidadProdTxTKeyPressed

    private void PagarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PagarBtnActionPerformed
        String vendedor = Vendedor1TxT.getText();
    String clienteNombre = NombreClienTxT.getText();
    String clienteCC = DocumentoClienTxT.getText();

    if (vendedor.isEmpty() || clienteNombre.isEmpty() || clienteCC.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Complete los datos del vendedor y cliente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel modelo = (DefaultTableModel) VentaTbld.getModel();
    if (modelo.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "No hay productos en la venta.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    double totalVenta = 0;
    for (int i = 0; i < modelo.getRowCount(); i++) {
        Number valor = (Number) modelo.getValueAt(i, 4); // Total + IVA
        totalVenta += valor.doubleValue();
    }

    int idCliente = conexion.obtenerIdClientePorDocumento(clienteCC);
    if (idCliente == -1) {
        JOptionPane.showMessageDialog(this, "Cliente no encontrado en la tabla clientes.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar stock antes de registrar la venta
    for (int i = 0; i < modelo.getRowCount(); i++) {
        String nombreProducto = (String) modelo.getValueAt(i, 0);
        Number cantidadNum = (Number) modelo.getValueAt(i, 2);
        int cantidad = cantidadNum.intValue();

        Producto producto = conexion.consultarProductoPorNombre(nombreProducto);
        if (producto == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el producto: " + nombreProducto, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (producto.getCantidad() < cantidad) {
            JOptionPane.showMessageDialog(this, "No hay suficiente stock para el producto: " + nombreProducto +
                "\nDisponibles: " + producto.getCantidad() + " | Solicitados: " + cantidad, "Stock insuficiente", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // Registrar venta
    Venta venta = new Venta(idCliente, vendedor, LocalDateTime.now(), totalVenta);
    int idVenta = conexion.registrarVenta(venta);
    if (idVenta == -1) {
        JOptionPane.showMessageDialog(this, "Error al registrar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Registrar detalles y descontar stock
    for (int i = 0; i < modelo.getRowCount(); i++) {
        String nombreProducto = (String) modelo.getValueAt(i, 0);
        double precioUnitario = ((Number) modelo.getValueAt(i, 1)).doubleValue();
        int cantidad = ((Number) modelo.getValueAt(i, 2)).intValue();
        double subtotal = ((Number) modelo.getValueAt(i, 3)).doubleValue();

        Producto producto = conexion.consultarProductoPorNombre(nombreProducto);
        if (producto == null) continue;

        DetalleVenta detalle = new DetalleVenta(idVenta, producto.getCodigo(), cantidad, precioUnitario, subtotal);
        conexion.registrarDetalleVenta(idVenta, detalle);
        conexion.descontarStock(producto.getCodigo(), cantidad);
    }

    cargarProductosEnTabla(); // Refresca el stock
    ((DefaultTableModel) VentaTbld.getModel()).setRowCount(0); // Limpia la tabla de venta

    JOptionPane.showMessageDialog(this, "Venta realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_PagarBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
        }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActualizarBtn;
    private javax.swing.JTextField Apellido2TxT;
    private javax.swing.JTextField ApellidoTxT;
    private javax.swing.JButton AñadirColBtn;
    private javax.swing.JButton AñadirMarBtn;
    private javax.swing.JButton AñadirMatBtn;
    private javax.swing.JLabel Cantidad;
    private javax.swing.JTextField CantidadProdTxT;
    private javax.swing.JTextField CantidadTxT;
    private javax.swing.JButton CerrarSesionBtn;
    private javax.swing.JTextField ClienteTxT;
    private javax.swing.JButton Clientebtn;
    private javax.swing.JPanel Clientes;
    private javax.swing.JButton ClientesBtn;
    private javax.swing.JTable ClientesTbld;
    private javax.swing.JLabel Codigo;
    private javax.swing.JTextField CodigoProductoTxT;
    private javax.swing.JTextField CodigoTxT;
    private javax.swing.JComboBox<ColorItem> ColorComboBox;
    private javax.swing.JTextField ColorTxT;
    private javax.swing.JTable Colorestbld;
    private javax.swing.JButton ConClntbtn;
    private javax.swing.JPanel Config;
    private javax.swing.JButton ConsulBtn;
    private javax.swing.JButton ConsultarBtn;
    private javax.swing.JLabel Contraseña;
    private javax.swing.JTextField ContraseñaTxT;
    private javax.swing.JLabel Descripcion;
    private javax.swing.JTextField DescripcionTxT;
    private javax.swing.JTextField DocTxT;
    private javax.swing.JLabel Documento;
    private javax.swing.JTextField DocumentoClienTxT;
    private javax.swing.JButton Editardatosbutton;
    private javax.swing.JButton ElimBtn;
    private javax.swing.JButton ElimClntbtn;
    private javax.swing.JButton EliminarBtn;
    private javax.swing.JButton EliminarColBtn;
    private javax.swing.JButton EliminarMarBtn;
    private javax.swing.JButton EliminarMatBtn;
    private javax.swing.JPanel ExitBtn;
    private javax.swing.JLabel ExitTxT;
    private javax.swing.JComboBox<MarcaItem> MarcaComboBox;
    private javax.swing.JTextField MarcaTxT;
    private javax.swing.JTable Marcastbld;
    private javax.swing.JComboBox<MaterialItem> MaterialComboBox;
    private javax.swing.JTextField MaterialTxT;
    private javax.swing.JTable Materialestbld;
    private javax.swing.JButton ModClntbtn;
    private javax.swing.JButton ModificarBtn;
    private javax.swing.JTextField NombTxT;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Nombre1;
    private javax.swing.JTextField NombreClienTxT;
    private javax.swing.JTextField NombreTxT;
    private javax.swing.JLabel NombreUsr;
    private javax.swing.JTextField NombreUsrTxT;
    private javax.swing.JLabel Nombre_Usuario;
    private javax.swing.JButton NuevaVentaBtn;
    private javax.swing.JPanel NuevaVnt;
    private javax.swing.JButton PagarBtn;
    private javax.swing.JLabel Precio;
    private javax.swing.JTextField PrecioTxT;
    private javax.swing.JPanel Productos;
    private javax.swing.JTable ProductosTbld;
    private javax.swing.JLabel Razon_Social;
    private javax.swing.JTextField Razon_SocialTxT;
    private javax.swing.JButton RegisBtn;
    private javax.swing.JButton RegistrarBtn;
    private javax.swing.JPanel Reparaciones;
    private javax.swing.JButton ReparacionesBtn;
    private javax.swing.JButton StockBtn;
    private javax.swing.JTabbedPane Tabla;
    private javax.swing.JLabel Telefono;
    private javax.swing.JTextField TelefonoTxT;
    private javax.swing.JLabel TituloUsr;
    private javax.swing.JLabel Total;
    private javax.swing.JTextField TotalTxT;
    private javax.swing.JTable UserTbld;
    private javax.swing.JTextField UsuarioTxT;
    private javax.swing.JButton UsuariosBtn;
    private javax.swing.JLabel Vendedor;
    private javax.swing.JTextField Vendedor1TxT;
    private javax.swing.JTextField VendedorTxT;
    private javax.swing.JPanel Venntas;
    private javax.swing.JLabel Venta;
    private javax.swing.JTable VentaTbld;
    private javax.swing.JTextField VentaTxT;
    private javax.swing.JButton VentasBtn;
    private javax.swing.JTable VentasTbld;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}


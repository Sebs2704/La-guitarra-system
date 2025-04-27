
package Vista;

import Modelo.Producto;
import Modelo.Conexion;
import Modelo.MarcaItem;
import Modelo.Usuario;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Principal extends javax.swing.JFrame {
    
    Conexion conexion = new Conexion();
    
    
    private void limpiarCampos() {
    CodigoTxT.setText("");
    DescripcionTxT.setText("");
    PrecioTxT.setText("");
    CantidadTxT.setText("");
    MaterialTxT.setText("");
    //MarcaComboBOx.;
    NombTxT.setText("");
    ColorTxT.setText("");
}
    
        private void cargarDatosComboBoxes() {
        cargarMarcas();
        //cargarMateriales(); // Llama si implementas el combo de material
        //cargarColores();    // Llama si implementas el combo de color
    }
    
    
     private void cargarMarcas() {
        // Obtener la lista de marcas desde la clase Conexion
        List<MarcaItem> marcas = conexion.obtenerTodasLasMarcas();

        // Crear el modelo para el ComboBox
        DefaultComboBoxModel<MarcaItem> model = new DefaultComboBoxModel<>();

        if (marcas == null || marcas.isEmpty()) {
            System.out.println("No se encontraron marcas.");
            // Opcional: añadir un item placeholder o deshabilitar
            // model.addElement(new MarcaItem(0, "-- Sin Marcas --"));
            // marcaComboBox.setEnabled(false);
        } else {
            // Llenar el modelo con los objetos MarcaItem
            for (MarcaItem item : marcas) {
                model.addElement(item);
            }
             MarcaComboBox.setEnabled(true);
        }
        // Asignar el modelo al JComboBox
        MarcaComboBox.setModel(model);
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
            usuario.getContraseña(), // Considera mostrarla encriptada o enmascarada
            usuario.getNombre(),
            usuario.getApellido()
        });
    }

    // Asignar el modelo a la tabla UserTbld
    UserTbld.setModel(modelo);
    }

    Conexion app = new Conexion();
    int xMouse, yMouse;
    public Principal() {
        initComponents();
        cargarDatosComboBoxes();
        cargarProductosEnTabla();
        llenarTablaUsuarios();
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        CantidadTxt = new javax.swing.JTextField();
        DescripPro = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        VentaTbld = new javax.swing.JTable();
        CodigoPro = new javax.swing.JLabel();
        CodigoTxt = new javax.swing.JTextField();
        PrecioTxt = new javax.swing.JTextField();
        CantidadPro = new javax.swing.JLabel();
        PrecioPro = new javax.swing.JLabel();
        DescripTxt = new javax.swing.JTextField();
        DOC = new javax.swing.JLabel();
        DOCtxt = new javax.swing.JTextField();
        Nom = new javax.swing.JLabel();
        NomTxT = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
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
        MaterialTxT = new javax.swing.JTextField();
        NombTxT = new javax.swing.JTextField();
        ColorTxT = new javax.swing.JTextField();
        RegistrarBtn = new javax.swing.JButton();
        ConsultarBtn = new javax.swing.JButton();
        EliminarBtn = new javax.swing.JButton();
        ModificarBtn = new javax.swing.JButton();
        MarcaComboBox = new javax.swing.JComboBox<MarcaItem>();
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTable1);

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
                .addContainerGap(421, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StockBtn)
                    .addComponent(UsuariosBtn)))
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

        CantidadTxt.setBackground(new java.awt.Color(207, 128, 38));
        CantidadTxt.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        CantidadTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadTxtActionPerformed(evt);
            }
        });

        DescripPro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        DescripPro.setText("Descripción");

        VentaTbld.setBackground(new java.awt.Color(155, 96, 28));
        VentaTbld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane1.setViewportView(VentaTbld);

        CodigoPro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        CodigoPro.setText("Codigo");

        CodigoTxt.setEditable(false);
        CodigoTxt.setBackground(new java.awt.Color(207, 128, 38));
        CodigoTxt.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        PrecioTxt.setEditable(false);
        PrecioTxt.setBackground(new java.awt.Color(207, 128, 38));
        PrecioTxt.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        PrecioTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecioTxtActionPerformed(evt);
            }
        });

        CantidadPro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        CantidadPro.setText("Cantidad");

        PrecioPro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        PrecioPro.setText("Precio");

        DescripTxt.setBackground(new java.awt.Color(207, 128, 38));
        DescripTxt.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        DOC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        DOC.setText("CC / Documento");

        DOCtxt.setBackground(new java.awt.Color(207, 128, 38));
        DOCtxt.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        DOCtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOCtxtActionPerformed(evt);
            }
        });

        Nom.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Nom.setText("Nombre");

        NomTxT.setBackground(new java.awt.Color(207, 128, 38));
        NomTxT.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jButton1.setBackground(new java.awt.Color(207, 128, 38));
        jButton1.setText("Imprimir");

        jLabel3.setText("Total");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel4.setText("-----------");

        javax.swing.GroupLayout NuevaVntLayout = new javax.swing.GroupLayout(NuevaVnt);
        NuevaVnt.setLayout(NuevaVntLayout);
        NuevaVntLayout.setHorizontalGroup(
            NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevaVntLayout.createSequentialGroup()
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevaVntLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(CodigoPro))
                    .addGroup(NuevaVntLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CodigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DescripPro)
                    .addComponent(DescripTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(NuevaVntLayout.createSequentialGroup()
                        .addComponent(CantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NuevaVntLayout.createSequentialGroup()
                        .addComponent(CantidadPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PrecioPro)))
                .addContainerGap(315, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NuevaVntLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NuevaVntLayout.createSequentialGroup()
                        .addComponent(DOC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Nom)
                        .addGap(154, 154, 154))
                    .addGroup(NuevaVntLayout.createSequentialGroup()
                        .addComponent(DOCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NomTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)))
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addGap(36, 36, 36)
                .addComponent(jLabel4)
                .addGap(69, 69, 69))
            .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NuevaVntLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        NuevaVntLayout.setVerticalGroup(
            NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NuevaVntLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CodigoPro)
                    .addComponent(DescripPro)
                    .addComponent(PrecioPro)
                    .addComponent(CantidadPro))
                .addGap(14, 14, 14)
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CodigoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescripTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NuevaVntLayout.createSequentialGroup()
                        .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(NuevaVntLayout.createSequentialGroup()
                                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Nom)
                                    .addComponent(DOC))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(NomTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DOCtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NuevaVntLayout.createSequentialGroup()
                        .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(20, 20, 20))))
            .addGroup(NuevaVntLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NuevaVntLayout.createSequentialGroup()
                    .addContainerGap(139, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 126, Short.MAX_VALUE)))
        );

        Tabla.addTab("tab1", NuevaVnt);

        Clientes.setBackground(new java.awt.Color(187, 119, 0));

        Documento.setText("CC / Documento de identidad ");

        DocTxT.setBackground(new java.awt.Color(207, 128, 38));
        DocTxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocTxTActionPerformed(evt);
            }
        });

        Nombre.setText("Nombre");

        NombreTxT.setEditable(false);
        NombreTxT.setBackground(new java.awt.Color(207, 128, 38));

        Telefono.setText("Telefono");

        TelefonoTxT.setBackground(new java.awt.Color(207, 128, 38));

        Razon_Social.setText("Razon social");

        Razon_SocialTxT.setBackground(new java.awt.Color(207, 128, 38));

        ClientesTbld.setBackground(new java.awt.Color(155, 96, 28));
        ClientesTbld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CC / Documento de identidad", "Nombre", "Telefono", "Razon social"
            }
        ));
        jScrollPane2.setViewportView(ClientesTbld);

        jButton2.setBackground(new java.awt.Color(117, 80, 72));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Registrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(117, 80, 72));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Consultar");

        jButton4.setBackground(new java.awt.Color(117, 80, 72));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Eliminar");

        jButton5.setBackground(new java.awt.Color(117, 80, 72));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Modificar");

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
                                .addComponent(Documento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DocTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TelefonoTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NombreTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(Razon_Social, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Razon_SocialTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(ClientesLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)))
                        .addGap(68, 68, 68)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
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
                        .addGap(29, 29, 29)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NombreTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nombre))
                        .addGap(40, 40, 40)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TelefonoTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Telefono))
                        .addGap(40, 40, 40)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Razon_Social)
                            .addComponent(Razon_SocialTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(24, 24, 24))
        );

        Tabla.addTab("tab2", Clientes);

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

        MaterialTxT.setBackground(new java.awt.Color(207, 128, 38));

        NombTxT.setBackground(new java.awt.Color(207, 128, 38));

        ColorTxT.setBackground(new java.awt.Color(207, 128, 38));
        ColorTxT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorTxTActionPerformed(evt);
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

        javax.swing.GroupLayout ProductosLayout = new javax.swing.GroupLayout(Productos);
        Productos.setLayout(ProductosLayout);
        ProductosLayout.setHorizontalGroup(
            ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductosLayout.createSequentialGroup()
                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProductosLayout.createSequentialGroup()
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(ProductosLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MaterialTxT)
                            .addComponent(ColorTxT)
                            .addComponent(MarcaComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ProductosLayout.createSequentialGroup()
                        .addContainerGap()
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
                                        .addGap(0, 52, Short.MAX_VALUE)
                                        .addComponent(CodigoTxT, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(NombTxT)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProductosLayout.createSequentialGroup()
                                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(RegistrarBtn)
                                    .addComponent(EliminarBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ModificarBtn)
                                    .addComponent(ConsultarBtn))
                                .addGap(20, 20, 20))
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
                                .addComponent(CantidadTxT)))))
                .addGap(18, 18, 18)
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
                            .addComponent(MaterialTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
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
                            .addComponent(ColorTxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RegistrarBtn)
                            .addComponent(ConsultarBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ModificarBtn)
                            .addComponent(EliminarBtn)))
                    .addGroup(ProductosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        Tabla.addTab("tab3", Productos);

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

        Tabla.addTab("tab6", Reparaciones);

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

        Tabla.addTab("tab5", Venntas);

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

        Tabla.addTab("tab6", Config);

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

    private void CantidadTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadTxtActionPerformed

    private void PrecioTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecioTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrecioTxtActionPerformed

    private void DOCtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOCtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOCtxtActionPerformed

    private void DocTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DocTxTActionPerformed

    private void CerrarSesionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarSesionBtnActionPerformed
        // Cerrar la ventana principal
    this.dispose();

    // Abrir la ventana de inicio de sesión
    Login login = new Login(); // Suponiendo que tienes una clase llamada "Login"
    login.setVisible(true);
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
        MaterialTxT.setText(String.valueOf(producto.getMaterial())); // corregido
        //MarcaComboBox.se(String.valueOf(producto.getMarca()));       // corregido
        NombTxT.setText(producto.getNombre());
        ColorTxT.setText(String.valueOf(producto.getColor()));
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
    String material = MaterialTxT.getText();
    String nombre = NombTxT.getText();
    String color = ColorTxT.getText();
    
    Object selectedMarcaObj = MarcaComboBox.getSelectedItem();

    // Verificar que todos los campos estén llenos
    if (codigo.isEmpty() || descripcion.isEmpty() || precio.isEmpty() || cantidad.isEmpty() || material.isEmpty() || selectedMarcaObj.toString().isEmpty() || nombre.isEmpty() || color.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Rellenar todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        
        MarcaItem idItem = (MarcaItem) selectedMarcaObj;
        // Convertir campos numéricos
        int codigoInt = Integer.parseInt(codigo);
        int precioInt = Integer.parseInt(precio);
        int cantidadInt = Integer.parseInt(cantidad);
        int materialInt = Integer.parseInt(material); // corregido
        int marcaInt = idItem.getIdMarca();       // corregido
        int colorInt = Integer.parseInt(color);       // corregido

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

    private void ModificarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarBtnActionPerformed
        try {
        int codigo = Integer.parseInt(CodigoTxT.getText());
        String descripcion = DescripcionTxT.getText();
        int precio = Integer.parseInt(PrecioTxT.getText());
        int cantidad = Integer.parseInt(CantidadTxT.getText());
        int material = Integer.parseInt(MaterialTxT.getText()); // corregido
        //int marca = Integer.parseInt(MarcaComboBox.getText());       // corregido
        String nombre = NombTxT.getText();
        int color = Integer.parseInt(ColorTxT.getText());

        // Verificar que el campo de nombre no esté vacío
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del producto no puede estar vacío", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Producto producto = new Producto(codigo, descripcion, precio, cantidad, material, 0 , nombre, color);

        if (app.modificarProducto(producto)) {
            JOptionPane.showMessageDialog(this, "Producto modificado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarProductosEnTabla(); // Actualizar la tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos para Código, Precio, Cantidad, Material, Marca y Color", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void RegisBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisBtnActionPerformed
        // Crear una instancia de la interfaz Registro
    Registro registroInterfaz = new Registro();
    
    // Hacer visible la interfaz de registro
    registroInterfaz.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_RegisBtnActionPerformed

    private void VentaTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VentaTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VentaTxTActionPerformed

    private void ClienteTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClienteTxTActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ColorTxTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorTxTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ColorTxTActionPerformed

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
    private javax.swing.JTextField ApellidoTxT;
    private javax.swing.JLabel Cantidad;
    private javax.swing.JLabel CantidadPro;
    private javax.swing.JTextField CantidadTxT;
    private javax.swing.JTextField CantidadTxt;
    private javax.swing.JButton CerrarSesionBtn;
    private javax.swing.JTextField ClienteTxT;
    private javax.swing.JPanel Clientes;
    private javax.swing.JButton ClientesBtn;
    private javax.swing.JTable ClientesTbld;
    private javax.swing.JLabel Codigo;
    private javax.swing.JLabel CodigoPro;
    private javax.swing.JTextField CodigoTxT;
    private javax.swing.JTextField CodigoTxt;
    private javax.swing.JTextField ColorTxT;
    private javax.swing.JPanel Config;
    private javax.swing.JButton ConsulBtn;
    private javax.swing.JButton ConsultarBtn;
    private javax.swing.JLabel Contraseña;
    private javax.swing.JTextField ContraseñaTxT;
    private javax.swing.JLabel DOC;
    private javax.swing.JTextField DOCtxt;
    private javax.swing.JLabel DescripPro;
    private javax.swing.JTextField DescripTxt;
    private javax.swing.JLabel Descripcion;
    private javax.swing.JTextField DescripcionTxT;
    private javax.swing.JTextField DocTxT;
    private javax.swing.JLabel Documento;
    private javax.swing.JButton ElimBtn;
    private javax.swing.JButton EliminarBtn;
    private javax.swing.JPanel ExitBtn;
    private javax.swing.JLabel ExitTxT;
    private javax.swing.JComboBox<MarcaItem> MarcaComboBox;
    private javax.swing.JTextField MaterialTxT;
    private javax.swing.JButton ModificarBtn;
    private javax.swing.JLabel Nom;
    private javax.swing.JTextField NomTxT;
    private javax.swing.JTextField NombTxT;
    private javax.swing.JLabel Nombre;
    private javax.swing.JTextField NombreTxT;
    private javax.swing.JLabel NombreUsr;
    private javax.swing.JTextField NombreUsrTxT;
    private javax.swing.JLabel Nombre_Usuario;
    private javax.swing.JButton NuevaVentaBtn;
    private javax.swing.JPanel NuevaVnt;
    private javax.swing.JLabel Precio;
    private javax.swing.JLabel PrecioPro;
    private javax.swing.JTextField PrecioTxT;
    private javax.swing.JTextField PrecioTxt;
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
    private javax.swing.JTextField VendedorTxT;
    private javax.swing.JPanel Venntas;
    private javax.swing.JLabel Venta;
    private javax.swing.JTable VentaTbld;
    private javax.swing.JTextField VentaTxT;
    private javax.swing.JButton VentasBtn;
    private javax.swing.JTable VentasTbld;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}

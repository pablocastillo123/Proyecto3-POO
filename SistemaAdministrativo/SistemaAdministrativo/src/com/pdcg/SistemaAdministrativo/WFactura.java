package com.pdcg.SistemaAdministrativo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WFactura extends JFrame {
	private JFrame frame;
	private int idFactura = 0;
	private int idCliente=0;
	private int idVendedor=0;
	private String encabezado[] = { "Codigo", "Producto", "Precio" };
	WFactura wClose;
	private JTextField tfCedula;
	private JTextField tfDireccion;
	private JTextField tfTelefono;
	private JTextField tfFecha;
	private JTextField tfFactura;
	private JTextField tfSubtotal;
	private JTextField tfIVA;
	private JTextField tfTotal;
	private JPanel contentPane;
	private JTable table;

	public void wClose(WFactura wClose) {
		this.wClose = wClose;
	}

	public WFactura() {
		setTitle("Factura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		getContentPane().setLayout(null);
		getContentPane().setLayout(null);

		JLabel lblDatosDelCliente = new JLabel("Datos del Cliente ");

		JLabel lblCedula = new JLabel("Cedula");

		tfCedula = new JTextField();
		tfCedula.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");

		JLabel lblDireccion = new JLabel("Direccion");

		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);

		JLabel lblTelefono = new JLabel("Telefono");

		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha");

		tfFecha = new JTextField();
		tfFecha.setColumns(10);

		JLabel lblFactura = new JLabel("Factura");

		tfFactura = new JTextField();
		tfFactura.setColumns(10);

		JLabel lblProducto = new JLabel("Producto");

		JLabel lblVendedor = new JLabel("Vendedor");

		JLabel lblSubtotal = new JLabel("SubTotal:");

		tfSubtotal = new JTextField();
		tfSubtotal.setColumns(10);

		JLabel lblIva = new JLabel("I.V.A.:");

		tfIVA = new JTextField();
		tfIVA.setColumns(10);

		JLabel lblTotal = new JLabel("Total");

		tfTotal = new JTextField();
		tfTotal.setColumns(10);

	

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wClose.setVisible(false);

			}
		});

		JComboBox cmbProducto = new JComboBox();
		loadDataProducto(cmbProducto);
		cmbProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadListaCompra(cmbProducto);
			}
		});

		JComboBox cmbVendedor = new JComboBox();
		loadDataVendedor(cmbVendedor);
		
		JScrollPane scrollPane = new JScrollPane();

		JComboBox cmbCliente = new JComboBox();
		loadDataCliente(cmbCliente);
		cmbCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadCliente(cmbCliente);
			}
		});
		
		JButton btnNewButton = new JButton("Facturar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				crearFactura(cmbCliente,cmbVendedor,cmbProducto);
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(27)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout
										.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 690,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addGap(9)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(lblVendedor).addGap(40)
																.addComponent(cmbVendedor, GroupLayout.PREFERRED_SIZE,
																		131, GroupLayout.PREFERRED_SIZE)
																.addGap(244).addComponent(lblSubtotal))
														.addGroup(groupLayout.createSequentialGroup().addGap(99)
																.addComponent(btnNewButton).addGap(18)
																.addComponent(btnSalir)
																.addPreferredGap(ComponentPlacement.RELATED, 240,
																		Short.MAX_VALUE)
																.addComponent(lblTotal))
														.addGroup(groupLayout.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.RELATED, 474,
																		Short.MAX_VALUE)
																.addComponent(lblIva)))
												.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(tfSubtotal, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(
																tfIVA, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(tfTotal, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 76,
														GroupLayout.PREFERRED_SIZE)
												.addGap(63)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup().addGap(294)
																.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE,
																		63, GroupLayout.PREFERRED_SIZE)
																.addGap(18).addComponent(tfTelefono,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout.createSequentialGroup().addGap(21)
																.addComponent(lblProducto, GroupLayout.PREFERRED_SIZE,
																		60, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(cmbProducto, GroupLayout.PREFERRED_SIZE,
																		404, GroupLayout.PREFERRED_SIZE)))
												.addPreferredGap(
														ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING)
												.addComponent(lblDatosDelCliente, GroupLayout.PREFERRED_SIZE, 106,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblCedula, GroupLayout.PREFERRED_SIZE, 46,
																GroupLayout.PREFERRED_SIZE)
														.addGap(10)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(
																		tfDireccion, GroupLayout.PREFERRED_SIZE, 263,
																		GroupLayout.PREFERRED_SIZE)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(tfCedula,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED, 27,
																				Short.MAX_VALUE)
																		.addComponent(lblNombre,
																				GroupLayout.PREFERRED_SIZE, 46,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(cmbCliente,
																				GroupLayout.PREFERRED_SIZE, 100,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)))))
												.addGap(18)
												.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 46,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(tfFecha, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblFactura, GroupLayout.PREFERRED_SIZE, 46,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(tfFactura, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(146)))
										.addGap(70)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(11).addComponent(lblDatosDelCliente).addGap(
						11)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout.createSequentialGroup().addGap(6).addComponent(lblCedula))
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(tfCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNombre).addComponent(lblFecha)
										.addComponent(tfFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFactura)
										.addComponent(tfFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(cmbCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
				.addGap(11)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblTelefono)
								.addComponent(tfTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(tfDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDireccion)))
				.addGap(24)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblProducto).addComponent(
						cmbProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(119)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(tfSubtotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSubtotal).addComponent(lblVendedor).addComponent(cmbVendedor,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(tfIVA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIva))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(tfTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTotal))
								.addContainerGap(58, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
										.addComponent(btnSalir))
								.addGap(28))))
				.addGroup(groupLayout.createSequentialGroup().addGap(142)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(151, Short.MAX_VALUE)));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},	encabezado));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
	

}

	public void loadDataProducto(JComboBox cp) {
		try {
			Conexion cnn = new Conexion();
			Statement st = cnn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id_producto,nu_codigo,de_nombre,nu_precio_venta FROM negocio.producto");

			Producto pp = new Producto();
			cp.addItem(pp);
			while (rs.next()) {
				pp = new Producto();
				pp.setId(rs.getInt("id_producto"));
				pp.setCodigo(rs.getInt("nu_codigo"));
				pp.setNombre(rs.getString("de_nombre"));
				pp.setPrecio(rs.getLong("nu_precio_venta"));
				cp.addItem(pp);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadDataVendedor(JComboBox cp) {
		try {
			Conexion cnn = new Conexion();
			Statement st = cnn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id_vendedor,de_nombre FROM negocio.vendedor");

			Vendedor vv = new Vendedor();
			cp.addItem(vv);

			while (rs.next()) {
				vv = new Vendedor();
				vv.setId(rs.getInt("id_vendedor"));
				vv.setNombre(rs.getString("de_nombre"));
				cp.addItem(vv);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadListaCompra(JComboBox cmbProducto) {
		Producto pp = (Producto) cmbProducto.getSelectedItem();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Vector row = new Vector();

		if (tfSubtotal.getText() == null || tfSubtotal.getText().equals("")) {
			tfSubtotal.setText("0");
			tfIVA.setText("0");
			tfTotal.setText("0");
		}

		if (pp.getNombre() != null) {
			row.add(pp.getCodigo());
			row.add(pp.getNombre());
			row.add(pp.getPrecio());
			model.addRow(row);
			tfSubtotal.setText((Float.parseFloat(tfSubtotal.getText()) + pp.getPrecio()) + "");
			tfIVA.setText((Float.parseFloat(tfSubtotal.getText()) * 0.16) + "");
			tfTotal.setText((Float.parseFloat(tfSubtotal.getText()) * 1.16) + "");
		}
	}

	public void loadDataCliente(JComboBox cp) {
		try {
			Conexion cnn = new Conexion();
			Statement st = cnn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id_cliente,nu_cedula,de_nombre,de_direccion,nu_telefono FROM negocio.cliente");

			Cliente cc = new Cliente();
			cp.addItem(cc);
			while (rs.next()) {
				cc = new Cliente();
				cc.setId(rs.getInt("id_cliente"));
				cc.setCedula(rs.getInt("nu_cedula"));
				cc.setNombre(rs.getString("de_nombre"));
				cc.setDireccion(rs.getString("de_direccion"));
				cc.setTelefono(rs.getString("nu_telefono"));

				cp.addItem(cc);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void loadCliente(JComboBox cmbCliente) {
		Cliente cc = (Cliente) cmbCliente.getSelectedItem();
		tfCedula.setText(String.valueOf(cc.getCedula()));
		tfDireccion.setText(cc.getDireccion());
		tfTelefono.setText(cc.getTelefono());

	} 

	private void crearFactura(JComboBox cmbCliente, JComboBox cmbVendedor,JComboBox cmbProducto) {
		Conexion cnn = new Conexion() ;
		Conexion cnn1 = new Conexion();
		Vendedor vv = (Vendedor) cmbVendedor.getSelectedItem();
		Cliente cc = (Cliente) cmbCliente.getSelectedItem();
		Producto pp = (Producto) cmbProducto.getSelectedItem();
		
		int tfF = Integer.parseInt(tfFactura.getText());
		float tfT = Float.parseFloat(tfTotal.getText());

		for (int i = 0; i < table.getRowCount(); i++) {
			System.out.println(table.getValueAt(i, 0));
			System.out.println(table.getValueAt(i, 1));
			System.out.println(table.getValueAt(i, 2));
			System.out.println("----------------------------------------------------------------");
			
			cnn.ejecutarCambio(cnn,"INSERT INTO negocio.factura(id_factura, id_impuesto, nu_factura, nu_total, id_cliente, id_vendedor) "+
			"VALUES ("+ tfF + "," + 1 + "," + tfF + "," + tfT + "," +cc.getId()+ "," + vv.getId() + ")");
			cnn1.ejecutarCambio(cnn1,"INSERT INTO negocio.detalle_factura(id_producto, nu_cantidad, nu_precio_venta, id_factura) "+
			"VALUES ("+pp.getId()+","+ 1 +","+pp.getPrecio() +","+ tfF+")");

			// cnn.ejecutarCambio(cnn, "INSERT INTO negocio.vendedor(de_nombre, nu_cedula)"
			// + "VALUES ('" + tfNombre.getText() + "', " +
			// Integer.parseInt(tfCedula.getText()) + ")");

		}
	}
}
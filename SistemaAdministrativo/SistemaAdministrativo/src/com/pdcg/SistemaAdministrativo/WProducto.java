package com.pdcg.SistemaAdministrativo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class WProducto extends JFrame {
	
	private JFrame frame;
	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfCantidad;
	private JTextField tfNombre;
	private JTextField tfPrecio;
	private JTable table;
	private int idProducto = 0;
	WProducto wClose;
	private String encabezado[]={"Id","codigo","Nombre","Cantidad","Precio"};

	
	public void wClose(WProducto wClose) {
		this.wClose = wClose;
	}
	
	public WProducto() {
		setTitle("Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCodigo = new JLabel("Codigo");
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblCantidad = new JLabel("Cantidad");
		
		JLabel lblPrecio = new JLabel("Precio");
		
		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		
		tfCantidad = new JTextField();
		tfCantidad.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (validarDato()) {
						Conexion cnn = new Conexion();
	
						String nombre = tfNombre.getText();
						int codigo = Integer.parseInt(tfCodigo.getText());
						int cantidad = Integer.parseInt(tfCantidad.getText());
						int precio = Integer.parseInt(tfPrecio.getText());
	
						if (idProducto == 0)
							cnn.ejecutarCambio(cnn,"INSERT INTO negocio.producto(nu_codigo,de_nombre,nu_cantidad,nu_precio_venta)" + 
						"VALUES ("+""+codigo+",'"+nombre+"',"+cantidad+","+precio+")");
						else 
							cnn.ejecutarCambio(cnn,"UPDATE negocio.producto SET nu_codigo = " + codigo + ", de_nombre = '" + nombre+"'"+",nu_cantidad = +"+cantidad+"nu_precio_venta = "+precio+"WHERE id_producto = " + idProducto);
						
						loadDataTable();	
					}
					else
						JOptionPane.showMessageDialog(null, "Faltan datos, verfique!", "Sistema", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Conexion cnn = new Conexion();
					cnn.ejecutarCambio(cnn,"DELETE FROM negocio.producto WHERE id_producto = " + Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					loadDataTable();
				}
			});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wClose.setVisible(false);
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(25, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCodigo, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(17)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(tfCantidad, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(tfCodigo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrecio))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tfNombre)
						.addComponent(tfPrecio))
					.addContainerGap(26, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(78)
					.addComponent(btnGuardar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEliminar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalir)
					.addContainerGap(106, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCodigo)
								.addComponent(tfCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNombre)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCantidad)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(tfPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPrecio))
						.addComponent(tfCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar)
						.addComponent(btnEliminar)
						.addComponent(btnSalir))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},encabezado));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
					idProducto = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					tfCodigo.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					tfNombre.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					tfCantidad.setText(table.getValueAt(table.getSelectedRow(),3).toString());
					tfPrecio.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				}
			});
		loadDataTable();
	}
	
	private void loadDataTable() {
		table.setModel(new DefaultTableModel(findDataTable(), encabezado));
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		idProducto = 0;
		tfCodigo.setText("");
		tfNombre.setText("");
		tfCantidad.setText("");
		tfPrecio.setText("");
	}
	
	private String[][] findDataTable(){
		String matrizInfo[][] = new String [0][0];
		int i = 0;
		
		try {
			Conexion cnn = new Conexion();
			ResultSet rs =  cnn.abrirConsulta(cnn,"SELECT id_producto,nu_codigo,de_nombre,nu_cantidad,nu_precio_venta FROM negocio.producto ORDER BY de_nombre");
			matrizInfo = new String[cnn.totalFilas()][table.getColumnCount()];
			if (cnn.totalFilas() > 0) {
		        do {
		        	matrizInfo[i][0]=Integer.parseInt(rs.getString("id_producto"))+"";
					matrizInfo[i][1]=Integer.parseInt(rs.getString("nu_codigo"))+"";
					matrizInfo[i][2]=rs.getString("de_nombre")+"";
					matrizInfo[i][3]=Integer.parseInt(rs.getString("nu_cantidad"))+"";
					matrizInfo[i][4]=rs.getFloat("nu_precio_venta")+"";
					i++;
				} while (rs.next());
			}
			cnn.cerrarConsulta();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return matrizInfo;
	}

	private boolean validarDato() {
		boolean salida = false;
		
		if (!tfCodigo.getText().equals(""))
			if (!tfNombre.getText().equals(""))
				if (!tfCantidad.getText().equals(""))
					if (!tfPrecio.getText().equals(""))
						salida = true;
		return salida;
	}
}

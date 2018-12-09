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
	private JTextField textField_3;
	private JTextField tfPrecio;
	private JTextField textField_5;
	private JTable table;
	private int idProducto = 0;
	WProducto wClose;
	private String titulos[]={"codigo","Nombre","Cantidad","Precio"};

	
	public void wClose(WProducto wClose) {
		this.wClose = wClose;
	}
	
	public WProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCodigo = new JLabel("Codigo");
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblMarca = new JLabel("Marca");
		
		JLabel lblTipoProducto = new JLabel("Tipo Producto");
		
		JLabel lblCantidad = new JLabel("Cantidad");
		
		JLabel lblPrecio = new JLabel("Precio");
		
		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		
		tfCantidad = new JTextField();
		tfCantidad.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (validarDato()) {
						Conexion cnn1 = new Conexion();
						Statement st = cnn1.getConnection().createStatement();
	
						String nombre = tfNombre.getText();
						int codigo = Integer.parseInt(tfCodigo.getText());
						int cantidad = Integer.parseInt(tfCantidad.getText());
						int precio = Integer.parseInt(tfPrecio.getText());
	
						if (idProducto == 0)
							st.executeUpdate("INSERT INTO negocio.producto(nu_codigo,de_nombre,nu_cantidad,nu_precio_venta)" + 
						"VALUES ("+""+codigo+",'"+nombre+"',"+cantidad+","+precio+")");
						else 
							st.executeUpdate("UPDATE negocio.producto SET nu_codigo = " + codigo + ", de_nombre = '" + nombre+"'"+",nu_cantidad = +"+cantidad+"nu_precio_venta = "+precio+"WHERE id = " + idProducto);
							
						idProducto = 0;
						table.setModel(new DefaultTableModel(loadDataTable(),titulos));
					}
					else
						JOptionPane.showMessageDialog(null, "Faltan datos, verfique!", "Sistema", JOptionPane.WARNING_MESSAGE);
				} 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Conexion cnn1 = new Conexion();
					Statement st = cnn1.getConnection().createStatement();
					st.executeUpdate("DELETE FROM negocio.producto WHERE id_producto = " + Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					table.setModel(new DefaultTableModel(loadDataTable(), titulos));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCodigo, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(41)
											.addComponent(tfCodigo, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
									.addGap(45)
									.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addGap(29)
									.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(tfCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(41)
											.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
									.addGap(20)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(72)
											.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblTipoProducto, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
									.addGap(46)
									.addComponent(lblPrecio)
									.addGap(10)
									.addComponent(tfPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(95)
							.addComponent(btnGuardar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalir)))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCodigo))
						.addComponent(tfCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblMarca))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCantidad))
						.addComponent(tfCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNombre))
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblTipoProducto))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPrecio))
						.addComponent(tfPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar)
						.addComponent(btnEliminar)
						.addComponent(btnSalir))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Cantidad", "Precio"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(86);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	private String[][] loadDataTable(){
		String matrizInfo[][] = new String [0][0];
		int i = 0;
		
		try {
			Conexion cnn = new Conexion();
			Statement st = cnn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id_producto,nu_codigo,de_nombre,nu_cantidad,nu_precio_venta FROM negocio.producto ORDER BY de_nombre");
			
		    int total = 0;
		    if (rs.last()) { 
		        total = rs.getRow();
		    }
			
			matrizInfo = new String[total][4];
	        rs.first();
			if (total > 0) {
		        do {
					
					matrizInfo[i][0]=Integer.parseInt(rs.getString("nu_codigo"))+"";
					matrizInfo[i][1]=rs.getString("de_nombre")+"";
					matrizInfo[i][2]=Integer.parseInt(rs.getString("nu_cantidad"))+"";
					matrizInfo[i][3]=rs.getFloat("nu_precio_venta")+"";

					i++;
				} while (rs.next());
			}
			rs.close();
			st.close();
			

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

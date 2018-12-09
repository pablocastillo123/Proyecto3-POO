package com.pdcg.SistemaAdministrativo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField tfRif;
	private JTextField tfDireccion;
	private JTextField tfNombre;
	private JTextField tfCantidad;
	private JTextField tfProducto;
	private JTextField tfPrecio;
	private JTextField tfFecha;
	private JFrame frame; 
	private JTable table;
	private String titulos[]={ "Rif", "Nombre", "Direccion"};
	private int idProveedor = 0;
	WProveedor wClose;


	public void wClose(WProveedor wClose) {
		this.wClose = wClose;
	}
	
	
	public WProveedor() {
	initialize();}
	
	public void initialize() {
		frame = new JFrame();
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 132, 517, 114);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(loadDataTable(), titulos));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent arg0) {
				
				tfRif.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				tfNombre.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				tfDireccion.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				
			}
		});
		table.setModel(new DefaultTableModel(loadDataTable(), titulos));
		
		JLabel lblDatosDelCliente = new JLabel("Datos del Proveedor");
		lblDatosDelCliente.setBounds(23, 11, 99, 14);
		contentPane.add(lblDatosDelCliente);
		
		JLabel lblRif = new JLabel("Rif");
		lblRif.setBounds(44, 42, 25, 14);
		contentPane.add(lblRif);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 67, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(23, 104, 46, 14);
		contentPane.add(lblDireccion);
		
		tfRif = new JTextField();
		tfRif.setBounds(74, 39, 86, 20);
		contentPane.add(tfRif);
		tfRif.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(74, 101, 86, 20);
		contentPane.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(74, 70, 86, 20);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblNombreDelProducto = new JLabel("Nombre del Producto");
		lblNombreDelProducto.setBounds(197, 42, 107, 14);
		contentPane.add(lblNombreDelProducto);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(244, 70, 46, 14);
		contentPane.add(lblCantidad);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(437, 42, 46, 14);
		contentPane.add(lblPrecio);
		
		JLabel lblFechaDeEntrega = new JLabel("Fecha de Entrega");
		lblFechaDeEntrega.setBounds(422, 73, 86, 14);
		contentPane.add(lblFechaDeEntrega);
		
		tfCantidad = new JTextField();
		tfCantidad.setBounds(314, 70, 86, 20);
		contentPane.add(tfCantidad);
		tfCantidad.setColumns(10);
		
		tfProducto = new JTextField();
		tfProducto.setBounds(314, 39, 86, 20);
		contentPane.add(tfProducto);
		tfProducto.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setBounds(493, 39, 85, 20);
		contentPane.add(tfPrecio);
		tfPrecio.setColumns(10);
		
		tfFecha = new JTextField();
		tfFecha.setBounds(518, 70, 60, 20);
		contentPane.add(tfFecha);
		tfFecha.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (validarDato()) {
						Conexion cnn1 = new Conexion();
						Statement st = cnn1.getConnection().createStatement();
	
						String nombre = tfNombre.getText();
						String rif = tfRif.getText();
						String direccion = tfDireccion.getText();
						
	
						if (idProveedor == 0)
							st.executeUpdate("INSERT INTO negocio.proveedor(co_rif,de_nombre,de_direccion)" + 
						"VALUES ("+rif+",'"+nombre+"','"+direccion+"')");
						else 
							st.executeUpdate("UPDATE negocio.proveedor SET co_rif = " + rif + ", de_nombre = '" + nombre+"'"+",de_direccion = +'"+direccion+"' WHERE id = " + idProveedor);
							
						idProveedor = 0;
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
		btnGuardar.setBounds(54, 257, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Conexion cnn1 = new Conexion();
					Statement st = cnn1.getConnection().createStatement();
					st.executeUpdate("DELETE FROM negocio.proveedor WHERE id_proveedor = " + Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
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
		btnEliminar.setBounds(153, 257, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wClose.setVisible(false);
			}
		});
		btnSalir.setBounds(252, 257, 89, 23);
		contentPane.add(btnSalir);
	}
	
	private String[][] loadDataTable() {
		String matrizInfo[][] = new String [0][0];
		int i = 0;
		
		try {
			
			Conexion cnn = new Conexion();
			Statement st = cnn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT co_rif,de_nombre,de_direccion FROM negocio.proveedor ORDER BY de_nombre ");

			  int total = 0;
			    if (rs.last()) { 
			        total = rs.getRow();
			    }
				
				matrizInfo = new String[total][3];
		        rs.first();
				if (total > 0) {
			        do {
						
						matrizInfo[i][0]=Integer.parseInt(rs.getString("co_rif"))+"";
						matrizInfo[i][1]=rs.getString("de_nombre")+"";
						matrizInfo[i][2]=rs.getString("de_direccion")+"";
						

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
		
		if (!tfRif.getText().equals(""))
			if (!tfNombre.getText().equals(""))
				if (!tfDireccion.getText().equals(""))
					
						salida = true;
		return salida;
	}

}

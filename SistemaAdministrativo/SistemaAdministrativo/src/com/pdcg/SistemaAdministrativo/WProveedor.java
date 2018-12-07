package com.pdcg.SistemaAdministrativo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class WProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WProveedor frame = new WProveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WProveedor() {
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Rif", "Nombre", "Direccion", "Producto", "Cantidad", "Fecha", "Precio"
			}
		));
		scrollPane.setViewportView(table);
		
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
		
		textField = new JTextField();
		textField.setBounds(74, 39, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(74, 101, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(74, 70, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
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
		
		textField_3 = new JTextField();
		textField_3.setBounds(314, 70, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(314, 39, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(493, 39, 85, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(518, 70, 60, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(54, 257, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(153, 257, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(252, 257, 89, 23);
		contentPane.add(btnLimpiar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(351, 257, 89, 23);
		contentPane.add(btnSalir);
	}

}

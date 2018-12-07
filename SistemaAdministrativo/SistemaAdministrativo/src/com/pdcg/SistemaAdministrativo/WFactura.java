package com.pdcg.SistemaAdministrativo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class WFactura extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WFactura frame = new WFactura();
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
	public WFactura() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblDatosDelCliente = new JLabel("Datos del cliente");
		lblDatosDelCliente.setBounds(30, 11, 86, 14);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(40, 36, 46, 14);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(40, 61, 46, 14);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(40, 89, 46, 14);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(40, 114, 46, 14);
		
		textField = new JTextField();
		textField.setBounds(84, 33, 86, 20);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(84, 61, 86, 20);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(84, 86, 86, 20);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(84, 114, 86, 20);
		textField_3.setColumns(10);
		
		JLabel lblNumeroDeFactura = new JLabel("Numero de Factura");
		lblNumeroDeFactura.setBounds(252, 61, 98, 14);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(313, 36, 37, 14);
		
		textField_4 = new JTextField();
		textField_4.setBounds(360, 58, 86, 20);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(360, 33, 86, 20);
		textField_5.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(lblDatosDelCliente);
		contentPane.add(lblCedula);
		contentPane.add(lblNombre);
		contentPane.add(lblDireccion);
		contentPane.add(lblTelefono);
		contentPane.add(textField);
		contentPane.add(textField_1);
		contentPane.add(textField_2);
		contentPane.add(textField_3);
		contentPane.add(lblNumeroDeFactura);
		contentPane.add(lblFecha);
		contentPane.add(textField_4);
		contentPane.add(textField_5);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(473, 36, 46, 14);
		contentPane.add(lblTotal);
		
		JLabel lblIva = new JLabel("I.V.A.");
		lblIva.setBounds(473, 61, 46, 14);
		contentPane.add(lblIva);
		
		JLabel lblSubtotal = new JLabel("SubTotal");
		lblSubtotal.setBounds(473, 89, 46, 14);
		contentPane.add(lblSubtotal);
		
		textField_6 = new JTextField();
		textField_6.setBounds(529, 33, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(529, 58, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(529, 86, 86, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 160, 592, 142);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Producto", "Descripcion", "Cantidad", "Precio", "Total", "SubTotal"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(81, 313, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(180, 313, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(279, 313, 89, 23);
		contentPane.add(btnLimpiar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(378, 313, 89, 23);
		contentPane.add(btnSalir);
	}

}

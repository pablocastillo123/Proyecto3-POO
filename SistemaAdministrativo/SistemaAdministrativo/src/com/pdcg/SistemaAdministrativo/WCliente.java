package com.pdcg.SistemaAdministrativo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class WCliente extends JFrame {
	private JFrame frame;	
	private JPanel contentPane;
	private JTextField tfCedula;
	private JTextField tfNombre;
	private JTextField tfDireccion;
	private JTextField tfTelefono;
	private JTable table;
	private int idCliente = 0;
	private String encabezado[]={"Id","Cedula","Nombre","Direccion","Telefono"};
	WCliente wClose;



	public void wClose(WCliente wClose) {
		this.wClose = wClose;
	}
	
	public WCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		
		JLabel lblNewLabel_1 = new JLabel("Cedula");
		
		JLabel lblNewLabel_2 = new JLabel("Direcci\u00F3n");
		
		JLabel lblNewLabel_3 = new JLabel("Telefono");
		
		tfCedula = new JTextField();
		tfCedula.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarDato()) {
						Conexion cnn = new Conexion();
						long cedula = Long.parseLong(tfCedula.getText());
						String nombre = tfNombre.getText();
						String direccion = tfDireccion.getText();
						String telefono = tfTelefono.getText();
	
						if (idCliente == 0)
							cnn.ejecutarCambio(cnn,"INSERT INTO negocio.cliente(nu_cedula,de_nombre,de_direccion,nu_telefono)" + "VALUES ("+cedula+",'"+nombre+"','"+direccion+"','"+telefono+"')");
						else 
							cnn.ejecutarCambio(cnn,"UPDATE negocio.cliente SET nu_cedula = " + cedula + ", de_nombre = '" + nombre+"', de_direccion = '"+direccion+"', nu_telefono = '"+telefono+"' WHERE id_cliente = " + idCliente);
						
						loadDataTable();
					}
					else
						JOptionPane.showMessageDialog(null, "Faltan datos, verfique!", "Sistema", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Conexion cnn = new Conexion();
					cnn.ejecutarCambio(cnn,"DELETE FROM negocio.cliente WHERE id_cliente = " + Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
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
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGuardar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalir))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(tfCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(45)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(tfDireccion))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(45)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(tfTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1))
						.addComponent(tfCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_2))
						.addComponent(tfDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_3))
						.addComponent(tfTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar)
						.addComponent(btnEliminar)
						.addComponent(btnSalir))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},encabezado));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				idCliente = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				tfCedula.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				tfNombre.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				tfDireccion.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				tfTelefono.setText(table.getValueAt(table.getSelectedRow(),4).toString());
				}
			});
		loadDataTable();
	}
	
	private void loadDataTable() {
		table.setModel(new DefaultTableModel(findDataTable(), encabezado));
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		idCliente = 0;
		tfCedula.setText("");
		tfNombre.setText("");
		tfDireccion.setText("");
		tfTelefono.setText("");
	}
	
private String[][] findDataTable(){
	String matrizInfo[][] = new String [0][0];
	int i = 0;
	
	try {
		Conexion cnn = new Conexion();
		ResultSet rs = cnn.abrirConsulta(cnn,"SELECT id_cliente , nu_cedula , de_nombre , de_direccion , nu_telefono FROM negocio.cliente ORDER BY de_nombre");
	    matrizInfo = new String[cnn.totalFilas()][table.getColumnCount()];
        if (cnn.totalFilas() > 0) {
	        do {
				matrizInfo[i][0]=Integer.parseInt(rs.getString("id_cliente"))+"";
				matrizInfo[i][1]=Integer.parseInt(rs.getString("nu_cedula"))+"";
				matrizInfo[i][2]=rs.getString("de_nombre")+"";
				matrizInfo[i][3]=rs.getString("de_direccion")+"";
				matrizInfo[i][4]=rs.getString("nu_telefono")+"";
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
	
	if (!tfCedula.getText().equals(""))
		if (!tfNombre.getText().equals(""))
			if (!tfDireccion.getText().equals(""))
				if (!tfTelefono.getText().equals(""))


					salida = true;
	return salida;
}
}

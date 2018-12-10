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
	private JFrame frame; 
	private JTable table;
	private String encabezado []={ "Id","Rif", "Nombre", "Direccion"};
	private int idProveedor = 0;
	WProveedor wClose;


	public void wClose(WProveedor wClose) {
		this.wClose = wClose;
	}
	
	
	public WProveedor() {
		setTitle("Proveedor");
	
	
	
		frame = new JFrame();
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JScrollPane scrollPane = new JScrollPane();
		
	
		
		JLabel lblDatosDelCliente = new JLabel("Datos del Proveedor");
		
		JLabel lblRif = new JLabel("Rif");
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblDireccion = new JLabel("Direccion");
		
		tfRif = new JTextField();
		tfRif.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarDato()) {
					Conexion cnn = new Conexion();
					if (idProveedor == 0)
						cnn.ejecutarCambio(cnn,"INSERT INTO negocio.proveedor(co_rif,de_nombre,de_direccion) VALUES ('"+tfRif.getText()+"','"+ tfNombre.getText() +"','"+tfDireccion.getText()+"')");
						else 
							cnn.ejecutarCambio(cnn,"UPDATE negocio.proveedor SET co_rif = " + tfRif.getText() + ", de_nombre = '" + tfNombre.getText()+"'"+",de_direccion = +'"+tfDireccion.getText()+"' WHERE id_proveedor = " + idProveedor);
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
				cnn.ejecutarCambio(cnn, "DELETE FROM negocio.proveedor WHERE id_proveedor = " + Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
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
					.addGap(18)
					.addComponent(lblDatosDelCliente, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addComponent(lblRif, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(tfRif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(32)
					.addComponent(lblDireccion, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(tfDireccion, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 517, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(137)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(lblDatosDelCliente)
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblRif))
						.addComponent(tfRif, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNombre))
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblDireccion))
						.addComponent(tfDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar)
						.addComponent(btnEliminar)
						.addComponent(btnSalir)))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},	encabezado));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
					idProveedor = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					tfRif.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					tfNombre.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					tfDireccion.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			  }
			});
		loadDataTable();
	}
	
	private void loadDataTable() {
		table.setModel(new DefaultTableModel(findDataTable(), encabezado));
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		idProveedor = 0;
		tfRif.setText("");
		tfNombre.setText("");
		tfDireccion.setText("");
	}
	
	private String[][] findDataTable(){
		String matrizInfo[][] = new String [0][0];
		int i = 0;
		
		try {
			Conexion cnn = new Conexion();
			ResultSet rs = cnn.abrirConsulta(cnn, "SELECT id_proveedor, co_rif, de_nombre,de_direccion FROM negocio.proveedor ORDER BY de_nombre");
			matrizInfo = new String[cnn.totalFilas()][table.getColumnCount()];
			if (cnn.totalFilas() > 0) {
		        do {
		        	matrizInfo[i][0]=Integer.parseInt(rs.getString("id_proveedor"))+"";
					matrizInfo[i][1]=rs.getString("co_rif")+"";
					matrizInfo[i][2]=rs.getString("de_nombre")+"";
					matrizInfo[i][3]=rs.getString("de_direccion")+"";
					i++;
				} while (rs.next());
			}
			cnn.cerrarConsulta();
		}
		catch (SQLException e) {
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

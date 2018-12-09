package com.pdcg.SistemaAdministrativo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class WVendedor extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField tfCedula;
	private JTextField tfNombre;
	private int idVendedor = 0;
	private JTable table;
	WVendedor wClose;
	String[] encabezado = new String[] {"Id", "Cedula", "Nombre"};

	public void wClose(WVendedor wClose) {
		this.wClose = wClose;
	}
	
	public WVendedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCedula = new JLabel("Cedula");
		
		tfCedula = new JTextField();
		tfCedula.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarDato()) {
					Conexion cnn = new Conexion();
					if (idVendedor == 0)
						cnn.ejecutarCambio(cnn, "INSERT INTO negocio.vendedor(de_nombre, nu_cedula)" + "VALUES ('" + tfNombre.getText() + "', " + Integer.parseInt(tfCedula.getText()) + ")");
					else 
						cnn.ejecutarCambio(cnn, "UPDATE negocio.vendedor SET nu_cedula = " + Integer.parseInt(tfCedula.getText()) + ", de_nombre = '" + tfNombre.getText() +"' WHERE id_vendedor = " + idVendedor);
					
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
				cnn.ejecutarCambio(cnn, "DELETE FROM negocio.vendedor WHERE id_vendedor = " + Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
				loadDataTable();
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wClose.setVisible(false);
			}
		});
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnGuardar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalir))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCedula, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(tfCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(55)
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(26))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCedula))
						.addComponent(tfCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNombre))
						.addComponent(tfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar)
						.addComponent(btnEliminar)
						.addComponent(btnSalir)))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},	encabezado));
		scrollPane_1.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
					idVendedor = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					tfCedula.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					tfNombre.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				}
			});
		loadDataTable();
	}
	
	private void loadDataTable() {
		table.setModel(new DefaultTableModel(findDataTable(), encabezado));
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		idVendedor = 0;
		tfCedula.setText("");
		tfNombre.setText("");
	}
	
	private String[][] findDataTable(){
		String matrizInfo[][] = new String [0][0];
		int i = 0;
		
		try {
			Conexion cnn = new Conexion();
			ResultSet rs = cnn.abrirConsulta(cnn, "SELECT id_vendedor, nu_cedula, de_nombre FROM negocio.vendedor ORDER BY de_nombre");
			matrizInfo = new String[cnn.totalFilas()][table.getColumnCount()];
			if (cnn.totalFilas() > 0) {
		        do {
		        	matrizInfo[i][0]=Integer.parseInt(rs.getString("id_vendedor"))+"";
					matrizInfo[i][1]=Integer.parseInt(rs.getString("nu_cedula"))+"";
					matrizInfo[i][2]=rs.getString("de_nombre")+"";
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
		
		if (!tfCedula.getText().equals(""))
			if (!tfNombre.getText().equals(""))
						salida = true;
		return salida;
	}
}

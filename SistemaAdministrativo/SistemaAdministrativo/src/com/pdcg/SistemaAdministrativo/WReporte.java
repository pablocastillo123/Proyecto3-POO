package com.pdcg.SistemaAdministrativo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class WReporte extends JFrame {

	private JPanel contentPane;
	private JTable table;
	String[] encabezado = new String[] {"Producto", "Cantidad", "Ganancia"};
	WReporte wClose;

	public void wClose(WReporte wClose) {
		this.wClose = wClose;
	}
	public WReporte() {
		WFactura wf = new WFactura();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(27, 42, 56, 14);
		contentPane.add(lblProducto);
		
		JComboBox cmbReporte = new JComboBox();
		wf.loadDataProducto(cmbReporte);
		cmbReporte.setBounds(93, 36, 226, 27);
		contentPane.add(cmbReporte);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 74, 380, 103);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, encabezado));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(new DefaultTableModel(loadDataReporte(cmbReporte), encabezado));
			}
		});
		btnNewButton.setBounds(118, 188, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wClose.setVisible(false);
			}
		});
		btnSalir.setBounds(217, 188, 89, 23);
		contentPane.add(btnSalir);
	}
	
	private String[][] loadDataReporte(JComboBox cp){
		String matrizInfo[][] = new String [0][0];
		int i = 0;
		String condicion = "";
		Producto pp = (Producto)cp.getSelectedItem();
		
		try {
			if (pp.getNombre() != null)
				condicion = "WHERE p.nu_codigo = " + pp.getCodigo();
				
			Conexion cnn = new Conexion();
			ResultSet rs = cnn.abrirConsulta(cnn, "SELECT p.de_nombre, SUM(df.nu_cantidad)cant, (df.nu_precio_venta * SUM(df.nu_cantidad)) - (pp.nu_precio_costo * SUM(df.nu_cantidad)) ganancia " + 
					" FROM " + 
					"	negocio.proveedor_producto pp " + 
					"	INNER JOIN negocio.detalle_factura df on pp.id_producto = df.id_producto" + 
					"	INNER JOIN negocio.producto p on df.id_producto = p.id_producto " + condicion + " GROUP BY p.de_nombre , df.nu_precio_venta , pp.nu_precio_costo");
			matrizInfo = new String[cnn.totalFilas()][table.getColumnCount()];
			if (cnn.totalFilas() > 0) {
		        do {
		        	matrizInfo[i][0]=rs.getString("de_nombre")+"";
		        	matrizInfo[i][1]=Integer.parseInt(rs.getString("cant"))+"";
					matrizInfo[i][2]=Integer.parseInt(rs.getString("ganancia"))+"";
					
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
	}

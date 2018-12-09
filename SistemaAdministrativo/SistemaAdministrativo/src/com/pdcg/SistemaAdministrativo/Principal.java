package com.pdcg.SistemaAdministrativo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFactura = new JMenu("Factura");
		mnFactura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				WFactura v1frame = new WFactura();
				v1frame.setVisible(true);
				v1frame.wClose(v1frame);
			}
		});

		menuBar.add(mnFactura);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WCliente v1frame = new WCliente();
				v1frame.setVisible(true);
				v1frame.wClose(v1frame);
			}
		});
		mnMantenimiento.add(mntmCliente);
		
		JMenuItem mntmVendedor = new JMenuItem("Vendedor");
		mntmVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WVendedor vframe = new WVendedor();
				vframe.setVisible(true);
				vframe.wClose(vframe);
			}
		});
		mnMantenimiento.add(mntmVendedor);
		
		JMenuItem mntmProducto = new JMenuItem("Producto");
		mntmProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WProducto v1frame = new WProducto();
				v1frame.setVisible(true);
				v1frame.wClose(v1frame);
			}
		});
		mnMantenimiento.add(mntmProducto);
		
		JMenuItem mntmProveedor = new JMenuItem("Proveedor");
		mntmProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WProveedor v1frame = new WProveedor();
				v1frame.setVisible(true);
				v1frame.wClose(v1frame);
			}
		});
		mnMantenimiento.add(mntmProveedor);
		
		JMenu mnReporteDeVenta = new JMenu("Reporte de Venta");
		mnReporteDeVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				WReporte v1frame = new WReporte();
				v1frame.setVisible(true);
				v1frame.wClose(v1frame);
			}
		});
		
		menuBar.add(mnReporteDeVenta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}
}

package com.pdcg.SistemaAdministrativo;

import java.sql.*;

import javax.swing.JOptionPane;


/**
 * Clase que permite conectar con la base de datos
 * @author chenao
 *
 */
public class Conexion {
	private static String bd = "punto_venta";
	private static String login = "postgres";
	private static String password = "vaio";
	private  static String url = "jdbc:postgresql://localhost:5432/" + bd;

   Connection conn = null;
   private Statement st = null;
   private ResultSet rs = null;
   private int totalFilas = 0;
   
   
   /** Constructor de DbConnection */
   public Conexion() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("org.postgresql.Driver");
         //obtenemos la conexión
         conn = DriverManager.getConnection(url,login,password);

         if (conn!=null){
            //System.out.println("Conección a base de datos "+bd+" OK");
         }
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
   }
   /**Permite retornar la conexión*/
   public Connection getConnection(){
      return conn;
   }

   public void desconectar(){
      conn = null;
   }
   
   public int totalFilas() {
	   return totalFilas;
   }
   
   public boolean ejecutarCambio(Conexion cnn, String sql) {
		try {
			System.out.println("SLQ ARMADO-------------> " + sql);
			Statement st = cnn.getConnection().createStatement();
			st.executeUpdate(sql);
			st.close();
			return true;
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
   }
   
   public ResultSet abrirConsulta(Conexion cnn, String sql) {
		totalFilas = 0;
		
		try {
			System.out.println("SLQ ARMADO-------------> " + sql);
			st = cnn.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(sql);
		    if (rs.last()) { 
		    	totalFilas = rs.getRow();
		        rs.first();
		        return rs;
		    }
		    return null;
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
   }

   public void cerrarConsulta() {
	   try {
		   rs.close();
		   st.close();
	   }
	   catch (SQLException e) {
		   System.out.println(e.getMessage());
		   JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);
	   }
   }
}
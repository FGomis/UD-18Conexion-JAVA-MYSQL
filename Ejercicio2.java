import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class Ejercicio2 {
	
		public static Connection conexion;	

		public static void main(String[] args) {
			// LLAMADA A METODOS MYSQL
			openConnection();
			createDB("Ejercicio2");
			createTableDepartamentos("Ejercicio2","departamentos");
			createTableEmpleados("Ejercicio2","empleados");
			insertDataDepartamentos("Ejercicio2","departamentos","1", "departamento1", "10000");
			insertDataDepartamentos("Ejercicio2","departamentos","2", "departamento2", "20000");
			insertDataDepartamentos("Ejercicio2","departamentos","3", "departamento3", "30000");
			insertDataDepartamentos("Ejercicio2","departamentos","4", "departamento4", "40000");
			insertDataDepartamentos("Ejercicio2","departamentos","5", "departamento5", "50000");
			insertDataEmpleados("Ejercicio2","empleados", "12345678", "Pepito", "Grillo", "1");
			insertDataEmpleados("Ejercicio2","empleados", "23456781", "Maria", "Garcia", "2");
			insertDataEmpleados("Ejercicio2","empleados", "34567812", "Jose", "Gomez", "3");
			insertDataEmpleados("Ejercicio2","empleados", "45678123", "Laura", "Sanchez", "4");
			insertDataEmpleados("Ejercicio2","empleados", "56781234", "Lidia", "Martin", "5");
			closeConnection();
		}

		
		//METODO QUE ABRE LA CONEXION CON SERVER MYSQL
		public static void openConnection() {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion=DriverManager.getConnection("jdbc:mysql://192.168.1.51:9090?useTimeone=true&serverTimezone=UTC","remote","Password123");//credenciales temporales
				System.out.print("Server Connected");
				
			}catch(SQLException | ClassNotFoundException ex  ){
				System.out.print("No se ha podido conectar con mi base de datos");
				System.out.println(ex.getMessage());
				
			}
			
		}
			
		//METODO QUE CIERRA LA CONEXION CON SERVER MYSQL
		public static void closeConnection() {
			try {
		
				conexion.close();
				System.out.print("Server Disconnected");
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.print("Error cerrando conexion");
			}
		}
		
		//METODO QUE CREA LA BASE DE DATOS
		public static void createDB(String name) {
			try {
				String Query="CREATE DATABASE "+ name;
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("DB creada con exito!");
				
			JOptionPane.showMessageDialog(null, "Se ha creado la DB " +name+ "de forma exitosa.");
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error creando la DB.");
			}	
		}

		//METODO QUE CREA TABLAS MYSQL
		public static void createTableDepartamentos(String db,String name) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE "+name+""
						+ "(Codigo INT PRIMARY KEY, Nombre VARCHAR(100), Presupuesto INT)";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
		}
			
			public static void createTableEmpleados(String db,String name) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
					
					String Query = "CREATE TABLE "+name+""
							+ "(DNI VARCHAR(8) PRIMARY KEY, Nombre VARCHAR(100), Apellidos VARCHAR(255),"
							+ "Departamento INT NOT NULL,"
							+ " KEY(Departamento),"
							+ " FOREIGN KEY (Departamento) REFERENCES departamentos (Codigo) ON DELETE CASCADE ON UPDATE CASCADE";
					Statement st= conexion.createStatement();
					st.executeUpdate(Query);
					System.out.println("Tabla creada con exito!");
					
				}catch (SQLException ex){
					System.out.println(ex.getMessage());
					System.out.println("Error crando tabla.");
					
				}
			
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public static void insertDataDepartamentos(String db, String tabla, String codigo, String nombre, String presupuesto) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + tabla + " (Codigo, Nombre, Presupuesto) VALUE(" 
						+ "\"" + codigo + "\", "
						+ "\"" + nombre + "\", "
						+ "\"" + presupuesto + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}

		public static void insertDataEmpleados(String db, String tabla, String dni, String nombre, String apellidos, String departamento) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + tabla + " (DNI, Nombre, Apellidos, Departamento) VALUE(" 
						+ "\"" + dni + "\", "
						+ "\"" + nombre + "\", "
						+ "\"" + apellidos + "\", "
						+ "\"" + departamento + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
}
	

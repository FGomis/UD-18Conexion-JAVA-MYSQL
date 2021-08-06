import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class Ejercicio1 {
	
		public static Connection conexion;	

		public static void main(String[] args) {
			// LLAMADA A METODOS MYSQL
			openConnection();
			createDB("Ejercicio1");
			createTableFabricantes("Ejercicio1","fabricantes");
			createTableArticulos("Ejercicio1","articulos");
			insertDataFabricantes("Ejercicio1","fabricantes","fabricante1");
			insertDataFabricantes("Ejercicio1","fabricantes","fabricante2");
			insertDataFabricantes("Ejercicio1","fabricantes","fabricante3");
			insertDataFabricantes("Ejercicio1","fabricantes","fabricante4");
			insertDataFabricantes("Ejercicio1","fabricantes","fabricante5");
			insertDataArticulos("Ejercicio1","articulos","articulo1", "10", "1");
			insertDataArticulos("Ejercicio1","articulos","articulo2", "20", "2");
			insertDataArticulos("Ejercicio1","articulos","articulo3", "30", "3");
			insertDataArticulos("Ejercicio1","articulos","articulo4", "40", "4");
			insertDataArticulos("Ejercicio1","articulos","articulo5", "50", "5");
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
		public static void createTableFabricantes(String db,String name) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE "+name+""
						+ "(Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(100))";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
		}
			
			public static void createTableArticulos(String db,String name) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
					
					String Query = "CREATE TABLE "+name+""
							+ "(Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(100),Precio INT,"
							+ "fabricante INT NOT NULL,"
							+ " KEY(fabricante),"
							+ " FOREIGN KEY (fabricante) REFERENCES fabricantes (Codigo) ON DELETE CASCADE ON UPDATE CASCADE";
					Statement st= conexion.createStatement();
					st.executeUpdate(Query);
					System.out.println("Tabla creada con exito!");
					
				}catch (SQLException ex){
					System.out.println(ex.getMessage());
					System.out.println("Error crando tabla.");
					
				}
			
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public static void insertDataFabricantes(String db, String tabla, String nombre) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + tabla + " (Nombre) VALUE(" 
						+ "\"" + nombre + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}

		public static void insertDataArticulos(String db, String tabla, String nombre, String precio, String fabricante) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + tabla + " (Nombre, Precio, Fabricante) VALUE(" 
						+ "\"" + nombre + "\", "
						+ "\"" + precio + "\", "
						+ "\"" + fabricante + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
}
	

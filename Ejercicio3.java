import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class Ejercicio3 {
	
		public static Connection conexion;	

		public static void main(String[] args) {
			// LLAMADA A METODOS MYSQL
			openConnection();
			createDB("Ejercicio3");
			createTableAlmacenes("Ejercicio3","almacenes");
			createTableCajas("Ejercicio3","cajas");
			insertDataAlmacenes("Ejercicio3","almacenes", "Tarragona", "100");
			insertDataAlmacenes("Ejercicio3","almacenes", "Barcelona", "200");
			insertDataAlmacenes("Ejercicio3","almacenes", "Bilbao", "300");
			insertDataAlmacenes("Ejercicio3","almacenes", "Cordoba", "400");
			insertDataAlmacenes("Ejercicio3","almacenes", "Paris", "500");
			insertDataCajas("Ejercicio3","cajas", "ABCDE", "contenido1", "100", "1");
			insertDataCajas("Ejercicio3","cajas", "BCDEA", "contenido2", "200", "2");
			insertDataCajas("Ejercicio3","cajas", "CDEAB", "contenido3", "300", "3");
			insertDataCajas("Ejercicio3","cajas", "DEABC", "contenido4", "400", "4");
			insertDataCajas("Ejercicio3","cajas", "EABCD", "contenido5", "500", "5");

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
		public static void createTableAlmacenes(String db,String name) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE "+name+""
						+ "(Codigo INT PRIMARY KEY AUTO_INCREMENT, Lugar VARCHAR(100), Capacidad INT)";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
		}
			
			public static void createTableCajas(String db,String name) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
					
					String Query = "CREATE TABLE "+name+""
							+ "(NumReferencia char(5) PRIMARY KEY, Contenido VARCHAR(100), Valor INT,"
							+ "Almacen INT NOT NULL,"
							+ " KEY(Almacen),"
							+ " FOREIGN KEY (Almacen) REFERENCES almacenes (Codigo) ON DELETE CASCADE ON UPDATE CASCADE";
					Statement st= conexion.createStatement();
					st.executeUpdate(Query);
					System.out.println("Tabla creada con exito!");
					
				}catch (SQLException ex){
					System.out.println(ex.getMessage());
					System.out.println("Error crando tabla.");
					
				}
			
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public static void insertDataAlmacenes(String db, String tabla, String lugar, String capacidad) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + tabla + " (Lugar, Capacidad) VALUE(" 
						+ "\"" + lugar + "\", "
						+ "\"" + capacidad + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}

		public static void insertDataCajas(String db, String tabla, String numreferencia, String contenido, String valor, String almacen) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + tabla + " (NumReferencia, Contenido, Valor, Almacen) VALUE(" 
						+ "\"" + numreferencia + "\", "
						+ "\"" + contenido + "\", "
						+ "\"" + valor + "\", "
						+ "\"" + almacen + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
}
	
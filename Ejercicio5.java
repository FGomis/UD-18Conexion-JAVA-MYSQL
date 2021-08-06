import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class Ejercicio5 {
	
		public static Connection conexion;	

		public static void main(String[] args) {
			// LLAMADA A METODOS MYSQL
			openConnection();
			createDB("Ejercicio5");
			createTableDespachos("Ejercicio5","despachos");
			createTableDirectores("Ejercicio5","directores");
			insertDataDespachos("Ejercicio5","despachos", "1", "10");
			insertDataDespachos("Ejercicio5","despachos", "2", "20");
			insertDataDespachos("Ejercicio5","despachos", "3", "30");
			insertDataDespachos("Ejercicio5","despachos", "4", "40");
			insertDataDespachos("Ejercicio5","despachos", "5", "50");
			insertDataDirectores("Ejercicio5","directores", "12345678", "Pepito Grillo", "0", "1");
			insertDataDirectores("Ejercicio5","directores", "23456781", "Maria Garcia", "12345678", "2");
			insertDataDirectores("Ejercicio5","directores", "34567812", "Jose Gomez", "23456781", "3");
			insertDataDirectores("Ejercicio5","directores", "45678123", "Laura Sanchez", "34567812", "4");
			insertDataDirectores("Ejercicio5","directores", "56781234", "Lidia Martin", "45678123", "5");
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
		public static void createTableDespachos(String db,String name) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE "+name+""
						+ "(Numero INT PRIMARY KEY, Capacidad INT)";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
		}
			
			public static void createTableDirectores(String db,String name) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
					
					String Query = "CREATE TABLE " + name 
							+ "( Dni VARCHAR(8) PRIMARY KEY,\r\n" + " Nomapels VARCHAR(255),\r\n"
							+ " Dnijefe VARCHAR(8) NULL,\r\n" + " KEY (Dnijefe),\r\n"
							+ " FOREIGN KEY (Dnijefe) REFERENCES directores (Dni) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
							+ " Despacho INT NOT NULL,\r\n" + " KEY (Despacho),\r\n"
							+ " FOREIGN KEY (Despacho) REFERENCES despachos (Numero) ON DELETE CASCADE ON UPDATE CASCADE)";
					Statement st= conexion.createStatement();
					st.executeUpdate(Query);
					System.out.println("Tabla creada con exito!");
					
				}catch (SQLException ex){
					System.out.println(ex.getMessage());
					System.out.println("Error crando tabla.");
					
				}
			
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public static void insertDataDespachos(String db, String tabla, String numero, String capacidad) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + tabla + " (Numero, Capacidad) VALUE(" 
						+ "\"" + numero + "\", "
						+ "\"" + capacidad + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}

		public static void insertDataDirectores(String db, String tabla, String dni, String nomapels, String dnijefe, String despacho) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + tabla + " (Dni, Nomapels, Dnijefe, Despacho) VALUE(" 
						+ "\"" + dni + "\", "
						+ "\"" + nomapels + "\", "
						+ "\"" + dnijefe + "\", "
						+ "\"" + despacho + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
}
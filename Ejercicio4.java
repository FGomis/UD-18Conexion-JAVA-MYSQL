import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class Ejercicio4 {
	
		public static Connection conexion;	

		public static void main(String[] args) {
			// LLAMADA A METODOS MYSQL
			openConnection();
			createDB("Ejercicio4");
			createTablePeliculas("Ejercicio4","peliculas");
			createTableSalas("Ejercicio4","salas");
			insertDataPeliculas("Ejercicio4","peliculas", "pelicula1", "7");
			insertDataPeliculas("Ejercicio4","peliculas", "pelicula2", "13");
			insertDataPeliculas("Ejercicio4","peliculas", "pelicula3", "7");
			insertDataPeliculas("Ejercicio4","peliculas", "pelicula4", "18");
			insertDataPeliculas("Ejercicio4","peliculas", "pelicula5", "0");
			insertDataSalas("Ejercicio4","salas", "sala1", "1");
			insertDataSalas("Ejercicio4","salas", "sala2", "2");
			insertDataSalas("Ejercicio4","salas", "sala3", "3");
			insertDataSalas("Ejercicio4","salas", "sala4", "4");
			insertDataSalas("Ejercicio4","salas", "sala5", "5");
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
		public static void createTablePeliculas(String db,String name) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE "+name+""
						+ "(Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(100), CalificacionEdad INT)";
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("Tabla creada con exito!");
				
			}catch (SQLException ex){
				System.out.println(ex.getMessage());
				System.out.println("Error crando tabla.");
				
			}
		}
			
			public static void createTableSalas(String db,String name) {
				try {
					String Querydb = "USE "+db+";";
					Statement stdb= conexion.createStatement();
					stdb.executeUpdate(Querydb);
					
					String Query = "CREATE TABLE "+name+""
							+ "(Codigo INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(100), Pelicula INT NOT NULL,"
							+ " KEY(Pelicula),"
							+ " FOREIGN KEY (Pelicula) REFERENCES peliculas (Codigo) ON DELETE CASCADE ON UPDATE CASCADE";
					Statement st= conexion.createStatement();
					st.executeUpdate(Query);
					System.out.println("Tabla creada con exito!");
					
				}catch (SQLException ex){
					System.out.println(ex.getMessage());
					System.out.println("Error crando tabla.");
					
				}
			
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public static void insertDataPeliculas(String db, String tabla, String nombre, String calificacion) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + tabla + " (Nombre, CalificacionEdad) VALUE(" 
						+ "\"" + nombre + "\", "
						+ "\"" + calificacion + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}

		public static void insertDataSalas(String db, String tabla, String nombre, String pelicula) {
			try {
				String Querydb = "USE "+db+";";
				Statement stdb= conexion.createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO " + tabla + " (Nombre, Pelicula) VALUE(" 
						+ "\"" + nombre + "\", "
						+ "\"" + pelicula + "\"); ";
				Statement st = conexion.createStatement();
				st.executeUpdate(Query);
				
				System.out.println("Datos almacenados correctamente");;
				
			} catch (SQLException ex ) {
				System.out.println(ex.getMessage());
				JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
			}
						
		}
}
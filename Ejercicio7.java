import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Ejercicio7 {

	public static Connection conexion;

	public static void main(String[] args) {
		// LLAMADA A METODOS MYSQL
		openConnection();
		createDB("Ejercicio7");
		createTableCientificos("Ejercicio7", "cientificos");
		createTableProyectos("Ejercicio7", "proyectos");
		createTableAsignadoA("Ejercicio7", "asignado_a");
		insertDataCientificos("Ejercicio7","cientificos", "12345678", "Pepito Grillo");
		insertDataCientificos("Ejercicio7","cientificos", "23456781", "Maria Garcia");
		insertDataCientificos("Ejercicio7","cientificos", "34567812", "Jose Gomez");
		insertDataCientificos("Ejercicio7","cientificos", "45678123", "Laura Sanchez");
		insertDataCientificos("Ejercicio7","cientificos", "56781234", "Lidia Martin");

		insertDataProyectos("Ejercicio7", "proyectos", "pro1", "Proyecto 1", "100");
		insertDataProyectos("Ejercicio7", "proyectos", "pro2", "Proyecto 2", "200");
		insertDataProyectos("Ejercicio7", "proyectos", "pro3", "Proyecto 3", "300");
		insertDataProyectos("Ejercicio7", "proyectos", "pro4", "Proyecto 4", "400");
		insertDataProyectos("Ejercicio7", "proyectos", "pro5", "Proyecto 5", "500");

		insertDataAsignadoA("Ejercicio7", "asignado_a", "pro1", "12345678");
		insertDataAsignadoA("Ejercicio7", "asignado_a", "pro2", "23456781");
		insertDataAsignadoA("Ejercicio7", "asignado_a", "pro3", "34567812");
		insertDataAsignadoA("Ejercicio7", "asignado_a", "pro4", "45678123");
		insertDataAsignadoA("Ejercicio7", "asignado_a", "pro5", "56781234");
		closeConnection();
	}

	// METODO QUE ABRE LA CONEXION CON SERVER MYSQL
	public static void openConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.1.51:9090?useTimeone=true&serverTimezone=UTC",
					"remote", "Password123");// credenciales temporales
			System.out.print("Server Connected");

		} catch (SQLException | ClassNotFoundException ex) {
			System.out.print("No se ha podido conectar con mi base de datos");
			System.out.println(ex.getMessage());

		}

	}

	// METODO QUE CIERRA LA CONEXION CON SERVER MYSQL
	public static void closeConnection() {
		try {

			conexion.close();
			System.out.print("Server Disconnected");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.print("Error cerrando conexion");
		}
	}

	// METODO QUE CREA LA BASE DE DATOS
	public static void createDB(String name) {
		try {
			String Query = "CREATE DATABASE " + name;
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("DB creada con exito!");

			JOptionPane.showMessageDialog(null, "Se ha creado la DB " + name + "de forma exitosa.");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando la DB.");
		}
	}

	// METODO QUE CREA TABLAS MYSQL
	public static void createTableCientificos(String db, String name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE " + name +" (\r\n"
					+ " dni VARCHAR(8) PRIMARY KEY,\r\n"
					+ " nomapels VARCHAR(255));";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}
	}

	public static void createTableProyectos(String db, String name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE " + name +" (\r\n"
					+ " id CHAR(4) PRIMARY KEY,\r\n"
					+ " nombre VARCHAR(255),\r\n"
					+ " horas INT);";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}
	}

	public static void createTableAsignadoA(String db, String name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE " + name + " (\r\n"
					+ " proyecto CHAR(4) NOT NULL,\r\n"
					+ " KEY (proyecto),\r\n"
					+ " FOREIGN KEY (proyecto) REFERENCES proyecto (id) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
					+ " cientifico VARCHAR(8) NOT NULL,\r\n"
					+ " KEY (cientifico),\r\n"
					+ " FOREIGN KEY (cientifico) REFERENCES cientificos (dni) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
					+ " PRIMARY KEY (proyecto, cientifico));";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}

	}

	// METODO QUE INSERTA DATOS EN TABLAS MYSQL
	public static void insertDataCientificos(String db, String tabla, String dni, String nomapels) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + tabla + " (dni, nomapels) VALUE(" + "\"" + dni + "\", " + "\""
					+ nomapels + "\"); ";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}

	}
	
	public static void insertDataProyectos(String db, String tabla, String id, String nombre, String horas) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + tabla + " (id, nombre, horas) VALUE(" + "\"" + id + "\", " + "\""
					+ nombre + "\"" + horas +"); ";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}

	}

	public static void insertDataAsignadoA(String db, String tabla, String cientifico, String proyecto) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + tabla + " (dni, nomapels) VALUE(" + "\"" + cientifico + "\", " + "\""
					+ proyecto + "\"); ";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}

	}
}
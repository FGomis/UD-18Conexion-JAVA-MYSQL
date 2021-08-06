import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Ejercicio8 {

	public static Connection conexion;

	public static void main(String[] args) {
		// LLAMADA A METODOS MYSQL
		openConnection();
		createDB("Ejercicio8");
		createTableProductos("Ejercicio8", "productos");
		createTableCajeros("Ejercicio8", "cajeros");
		createTableMaquinasRegistradoras("Ejercicio8", "maquinas_registradoras");
		createTableVenta("Ejercicio8", "venta");
		insertDataProductos("Ejercicio8","productos", "pan", "1");
		insertDataProductos("Ejercicio8","productos", "agua", "2");
		insertDataProductos("Ejercicio8","productos", "queso", "3");
		insertDataProductos("Ejercicio8","productos", "embutido", "4");
		insertDataProductos("Ejercicio8","productos", "galletas", "5");
		
		insertDataCajeros("Ejercicio8","cajeros", "Maria Garcia");
		insertDataCajeros("Ejercicio8","cajeros", "Jose Gomez");
		insertDataCajeros("Ejercicio8","cajeros", "Laura Sanchez");
		insertDataCajeros("Ejercicio8","cajeros", "Lidia Martin");
		insertDataCajeros("Ejercicio8","cajeros", "Daniel Costana");

		insertDataMaquinasRegistradoras("Ejercicio8", "maquinas_registradoras", "1");
		insertDataMaquinasRegistradoras("Ejercicio8", "maquinas_registradoras", "2");
		insertDataMaquinasRegistradoras("Ejercicio8", "maquinas_registradoras", "3");
		insertDataMaquinasRegistradoras("Ejercicio8", "maquinas_registradoras", "4");
		insertDataMaquinasRegistradoras("Ejercicio8", "maquinas_registradoras", "5");

		insertDataVenta("Ejercicio8", "asignado_a", "1", "1", "1");
		insertDataVenta("Ejercicio8", "asignado_a", "2", "2", "2");
		insertDataVenta("Ejercicio8", "asignado_a", "3", "3", "3");
		insertDataVenta("Ejercicio8", "asignado_a", "4", "4", "4");
		insertDataVenta("Ejercicio8", "asignado_a", "5", "5", "5");
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
	public static void createTableProductos(String db, String name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = " CREATE TABLE " + name +" (\r\n"
					+ " codigo INT PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ " nombre VARCHAR(100),\r\n"
					+ " precio INT);";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}
	}
	
	public static void createTableCajeros(String db, String name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE " + name +" (\r\n"
					+ " codigo INT PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ " nomapels VARCHAR(255));";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}
	}

	public static void createTableMaquinasRegistradoras(String db, String name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = " CREATE TABLE " + name +" (\r\n"
					+ " codigo INT PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ " piso INT);";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}
	}

	public static void createTableVenta(String db, String name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE " + name +" (\r\n"
					+ " cajero INT NOT NULL,\r\n"
					+ " KEY (cajero),\r\n"
					+ " FOREIGN KEY (cajero) REFERENCES cajeros (codigo) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
					+ " maquina INT NOT NULL,\r\n"
					+ " KEY (maquina),\r\n"
					+ " FOREIGN KEY (maquina) REFERENCES maquinas_registradoras (codigo) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
					+ " producto INT NOT NULL,\r\n"
					+ " KEY (producto),\r\n"
					+ " FOREIGN KEY (producto) REFERENCES productos (codigo) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
					+ " PRIMARY KEY (cajero, maquina, producto));";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}

	}

	// METODO QUE INSERTA DATOS EN TABLAS MYSQL
	public static void insertDataProductos(String db, String tabla, String nombre, String precio) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + tabla + " (nombre, precio) VALUE(" + "\"" + nombre + "\", " + "\""
					+ precio + "\"); ";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}
	}
	
	public static void insertDataCajeros(String db, String tabla, String nomapels) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + tabla + " (nomapels) VALUE("+nomapels+"); ";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}
	}
	
	public static void insertDataMaquinasRegistradoras(String db, String tabla, String piso) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + tabla + " (piso) VALUE("+piso+"); ";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}
	}
	

	public static void insertDataVenta(String db, String tabla, String cajero, String maquina, String producto) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + tabla + " (cajero, maquina, producto) VALUE(" + "\"" + cajero + "\", " + "\""
					+ maquina + "\"" + producto +"); ";
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Ejercicio6 {

	public static Connection conexion;

	public static void main(String[] args) {
		// LLAMADA A METODOS MYSQL
		openConnection();
		createDB("Ejercicio6");
		createTablePiezas("Ejercicio6", "piezas");
		createTableProveedores("Ejercicio6", "proveedores");
		createTableSuministra("Ejercicio6", "suministra");
		insertDataPiezas("Ejercicio6", "piezas", "Tornillo");
		insertDataPiezas("Ejercicio6", "piezas", "Tuerca");
		insertDataPiezas("Ejercicio6", "piezas", "Taco");
		insertDataPiezas("Ejercicio6", "piezas", "Engranaje");
		insertDataPiezas("Ejercicio6", "piezas", "Clavo");
		insertDataProveedores("Ejercicio6", "proveedores", "pro1", "Proveedor 1");
		insertDataProveedores("Ejercicio6", "proveedores", "pro2", "Proveedor 2");
		insertDataProveedores("Ejercicio6", "proveedores", "pro3", "Proveedor 3");
		insertDataProveedores("Ejercicio6", "proveedores", "pro4", "Proveedor 4");
		insertDataProveedores("Ejercicio6", "proveedores", "pro5", "Proveedor 5");
		insertDataSuministra("Ejercicio6", "suministra", "1", "pro1", "10");
		insertDataSuministra("Ejercicio6", "suministra", "2", "pro2", "20");
		insertDataSuministra("Ejercicio6", "suministra", "3", "pro3", "30");
		insertDataSuministra("Ejercicio6", "suministra", "4", "pro4", "40");
		insertDataSuministra("Ejercicio6", "suministra", "5", "pro5", "50");
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
	public static void createTablePiezas(String db, String name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE " + name + " (\r\n"
					+ " codigo INT PRIMARY KEY AUTO_INCREMENT NOT NULL,\r\n"
					+ " nombre VARCHAR(100));";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}
	}

	public static void createTableProveedores(String db, String name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE " + name + " (\r\n"
					+ " id CHAR(4) PRIMARY KEY,\r\n"
					+ " nombre VARCHAR(100));";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}
	}

	public static void createTableSuministra(String db, String name) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE " + name +" (\r\n"
					+ " precio INT,\r\n"
					+ " codigopieza INT NOT NULL,\r\n"
					+ " KEY (codigopieza),\r\n"
					+ " FOREIGN KEY (codigopieza) REFERENCES piezas (codigo) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
					+ " idproveedor CHAR(4) NOT NULL,\r\n"
					+ " KEY (idproveedor),\r\n"
					+ " FOREIGN KEY (idproveedor) REFERENCES proveedores (id) ON DELETE CASCADE ON UPDATE CASCADE,\r\n"
					+ " PRIMARY KEY (codigopieza, idproveedor));";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}

	}

	// METODO QUE INSERTA DATOS EN TABLAS MYSQL
	public static void insertDataPiezas(String db, String tabla, String nombre) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + tabla + " (Nombre) VALUE(" + "\"" + nombre + "\"); ";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}

	}
	
	public static void insertDataProveedores(String db, String tabla, String id, String nombre) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + tabla + " (id, nombre) VALUE(" + "\"" + id + "\", " + "\""
					+ nombre + "\"); ";
			Statement st = conexion.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}

	}

	public static void insertDataSuministra(String db, String tabla, String codigopieza, String idproveedor, String precio) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = conexion.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + tabla + " (codigopieza, idproveedor, precio) VALUE(" + "\"" + codigopieza + "\", "
					+ "\"" + idproveedor + "\", " + "\"" + precio + "\"); ";
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
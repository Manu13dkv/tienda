package servidor;

import java.rmi.RemoteException;
import java.security.Identity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cliente.Almacen;
import cliente.Producto;

public class AlmacenImpl implements Almacen {

	private int obtenerCantidad(int id){

		int cantidad = 0;

		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/dit"; 
			String user = "dit";
			String pass = "dit";

			Connection conn = DriverManager.getConnection(url, user, pass);

			String consultabd = 
			"SELECT cantidad FROM inventario WHERE" + 
			"id=?";

			PreparedStatement statement = conn.prepareStatement(consultabd);

			statement.setString(1, ((Integer) id).toString());
	
			ResultSet rs = statement.executeQuery();

			if(rs.next()) {
				cantidad = rs.getInt("cantidad");
			}
			
			rs.close();
			statement.close();
			conn.close();
			
		} catch (SQLException e) {

			System.out.println("Excepcion SQL Exception: " + e.getMessage());
			System.out.println("No ha podido obtenerse la cantidad de este producto");
			e.printStackTrace();
		
		} catch (ClassNotFoundException e) {

			System.out.println("No ha podido obtenerse la cantidad de este producto");
			e.printStackTrace();
		}

		return cantidad;
	}

    public int insertarProducto( int id, String codigo_referencia, String marca, String talla, String color, String tipo, String modelo, int cantidad ) throws RemoteException
    {

		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/dit"; 
			String user = "dit";
			String pass = "dit";

			Connection conn = DriverManager.getConnection(url, user, pass);

			String consultabd = 
			"INSERT INTO inventario" + 
			"(id, codigo_referencia, marca, talla, color, tipo, modelo, cantidad)" + 
			"VALUES (?,?,?,?,?,?,?,?)";

			PreparedStatement statement = conn.prepareStatement(consultabd);

			statement.setString(1, ((Integer) id).toString());
			statement.setString(1, codigo_referencia);
			statement.setString(1, marca);
			statement.setString(1, talla);
			statement.setString(1, color);
			statement.setString(1, tipo);
			statement.setString(1, modelo);
			statement.setString(1, ((Integer) cantidad).toString());

			ResultSet rs = statement.executeQuery();

			if(rs.next()) {
				System.out.println("Insercion de producto exitosa");
			}
			
			rs.close();
			statement.close();
			conn.close();
		
		} catch (SQLException e) {

			System.out.println("Excepcion SQL Exception: " + e.getMessage());
			System.out.println("El producto no ha podido insertarse en la BBDD.");
			e.printStackTrace();
			return -1;
		
		} catch (ClassNotFoundException e) {

			System.out.println("El producto no ha podido insertarse en la BBDD.");
			e.printStackTrace();
			return -1;
		}

		return 0;

	}


	public int comprar(int id, int cantidad) throws RemoteException{
		
		int nueva_cantidad = 0;
		int exito = -1;

		try {
			
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/dit"; 
			String user = "dit";
			String pass = "dit";

			Connection conn = DriverManager.getConnection(url, user, pass);

			//Comprobamos la cantidad de este producto.
			nueva_cantidad = obtenerCantidad(id) - cantidad;


			String consultabd = 
				"UPDATE inventario" +
				"SET cantidad=?" +
				"WHERE codigo_referencia=?";

			PreparedStatement statement = conn.prepareStatement(consultabd);

			statement.setInt(1, nueva_cantidad);
			statement.setInt(2, id);

			ResultSet rs = statement.executeQuery();

			if(rs.next()) {
				System.out.println("Compra exitosa.");
			}
			
			rs.close();
			statement.close();
			conn.close();

			exito = 0;
			return exito;
        
		} catch (SQLException e) {
        	System.out.println("Excepcion SQL Exception: " + e.getMessage());
			System.out.println("La compra no ha sido realizada con exito.");
        	e.printStackTrace();
    
		} catch (ClassNotFoundException e) {
			System.out.println("La compra no ha sido realizada con exito.");
        	e.printStackTrace();
		}
		return exito;
    }


    public List<Producto> obtenerProductosPorTipo( String tipo ) throws RemoteException{
		
		List<Producto> productos = new ArrayList<Producto>();

		try {

			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/dit"; 
			String user = "dit";
			String pass = "dit";

			Connection conn = DriverManager.getConnection(url, user, pass);

			String consultabd = 
			"SELECT * FROM inventario WHERE" + 
			"tipo =?";

			PreparedStatement statement = conn.prepareStatement(consultabd);

			statement.setString(1, tipo);

			ResultSet rs = statement.executeQuery();

			if(rs.next()) {

				productos.add(
					new ProductoImpl(
						rs.getInt("id"),
						rs.getString("codigo_referencia"),
						rs.getString("marca"),
						rs.getString("talla"),
						rs.getString("color"),
						rs.getString("tipo"),
						rs.getString("modelo"),
						rs.getInt("cantidad")
					)
				);
			}	    
			
			rs.close();
			statement.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("Excepcion SQL Exception: " + e.getMessage());
			System.out.println("Error al obtener productos.");
			e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			System.out.println("Error al obtener productos.");
			e.printStackTrace();
		}

		return productos;
	}

}


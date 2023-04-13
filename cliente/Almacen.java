package cliente;

import java.rmi.*;
import java.util.*;


public interface Almacen extends Remote {
    
    public int insertarProducto(
        
        int id,
        String codigo_referencia,
        String marca,
        String talla, 
        String color,
        String tipo,
        String modelo,
        int cantidad
        
    ) throws RemoteException;

    public int comprar(int id, int cantidad) throws RemoteException;

    public List<Producto> obtenerProductosPorTipo( String tipo) throws RemoteException;

}

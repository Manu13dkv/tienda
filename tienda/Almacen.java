package tienda;

import java.rmi.*;
import java.util.*;

interface Almacen extends Remote {
    
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

    public void comprar(int id, int cantidad) throws RemoteException;

    public List<Producto> obtenerProductosPorTipo( String tipo) throws RemoteException;

}

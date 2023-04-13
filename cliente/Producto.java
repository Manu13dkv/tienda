package cliente;

import java.rmi.*;

public interface Producto extends Remote {

    public int getId() throws RemoteException;
    public String getCodigoReferencia() throws RemoteException;
    public String getMarca() throws RemoteException;
    public String getColor() throws RemoteException;
    public String getTipo() throws RemoteException;
    public String getModelo() throws RemoteException;
    public int getCantidad() throws RemoteException;
 
}

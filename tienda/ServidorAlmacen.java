package tienda;

import java.rmi.*;
import java.rmi.server.*;

class ServidorAlmacen {
    static public void main(String args[]) {
        if (args.length != 1) {
            System.err.println("Uso: ServidorProducto numPuertoRegistro");
            return;
        }
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            AlmacenImpl srv = new AlmacenImpl();
            Naming.rebind("rmi://localhost:" + args[0] + "/Almacen", srv);
        } catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Excepcion en ServidorAlmacen:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ClienteAlmacen {
	static public void main (String args[]) {

        if (args.length!=2) {
            System.err.println("Uso: Cliente hostregistro numPuertoRegistro");
            System.exit(0);
        }

       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {

            Almacen almacen = (Almacen) Naming.lookup("//" + args[0] + ":" + args[1] + "/Almacen");

           	int opcion = 0;
            String message = "¡Bienvenido a nuestra tienda clothes's Teleco!";
      		String border = "".repeat(message.length() + 6);
      		System.out.println(border);
      		System.out.println("   " + message + "   *");
      		System.out.println(border);
			System.out.println("¿Que quiere realizar una búsqueda o una compra?/n");
			
			while(opcion != 3){
			
				System.out.println("Introduce 1 si quiere buscar/n");
				System.out.println("Introduce 2 si quiere comprar/n");
				System.out.println("Introduce 3 si quieres salir de la tienda/n");
				
				Scanner scanner = new Scanner(System.in);
				opcion = scanner.nextInt();
				
				if (opcion == 1) 
				{

						// Buscar ropa
						System.out.println("Ingrese el tipo de la ropa que desea buscar:");
						String tipo = scanner.next();

						List<Producto> ropa = almacen.obtenerProductosPorTipo(tipo);

						if (ropa != null) 
							{
								System.out.println("Se encontró el siguiente producto en el inventario:");
								System.out.println(ropa);
							} 
							else {
								System.out.println("No se encontró el producto en el inventario.");
							}
				}
					
				else 
				{
					if (opcion == 2) 
					{
							System.out.println("Ingrese el id del producto que desea comprar:");
							int id = scanner.nextInt();

							System.out.println("Ingrese la cantidad que desea comprar:");
							int cantidad = scanner.nextInt();

							int exito = almacen.comprar(id, cantidad);
							if (exito == 0) {
								System.out.println("Compra exitosa!");
							} else {
								System.out.println("Error no hay suficiente inventario para completar la compra.");
							} 
					} else if(opcion == 3){
						System.out.println("Gracias por visitar nuestra tienda, esperamos su retorno/n");
						System.exit(0);
					} else{
						System.out.println("Opcion invalida.");
					}
				}
			}
		}
		catch (RemoteException e) {
			System.err.println("Error de comunicacion: " + e.toString());
		}
		catch (Exception e) {
			System.err.println("Excepcion en Cliente:");
			e.printStackTrace();
		}
	}	
		
}

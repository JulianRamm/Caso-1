package comunicacion;

public class Servidor extends Thread {

	private Buffer buffer = Buffer.getInstance();
	private Mensaje mensaje;

	public Servidor() {
	}

	public void run() {
		while (true) {
			mensaje = buffer.darMensaje();
			if (mensaje != null) {
				synchronized (mensaje) {
					System.out.println("El servidor con nombre de Thread: " + this.getName()
							+ " esta respondiendo el mensaje con id: " + mensaje.getId()
							+ " el cual tiene un valor de: " + mensaje.getVariable() + " perteneciente al Thread: "
							+ mensaje.getCliente().getName());
					responder();
					mensaje.notify();
				}
			} 
			else {
				yield();
			}
		}
	}

	public void responder() {
		System.out.println("entra a responder() en Servidor");
		mensaje.addOneToMessage();
		System.out.println("El servidor con nombre de Thread: " + this.getName() + " respondio el mensaje con id: "
				+ mensaje.getId() + " con un valor de: " + mensaje.getVariable() + " perteneciente al Thread: "
				+ mensaje.getCliente().getName());

	}
}
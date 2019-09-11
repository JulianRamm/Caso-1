package comunicacion;

public class Servidor extends Thread {

	private Buffer buffer = Buffer.getInstance();
	private Mensaje mensaje;

	public Servidor() {
	}

	public void run() {	
		System.out.println("entra a run() en Servidor");
			mensaje = buffer.darMensaje();
			responder();
	}

	public void responder() {
		System.out.println("entra a responder() en Servidor");
		mensaje.addOneToMessage();
	}
}
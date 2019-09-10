package comunicacion;

public class Servidor extends Thread {

	private Buffer buffer = Buffer.getInstance();
	private Mensaje mensaje;

	public Servidor() {
	}

	public void run() {	
			mensaje = buffer.darMensaje();
			responder();
	}

	public void responder() {
		mensaje.addOneToMessage();
	}
}
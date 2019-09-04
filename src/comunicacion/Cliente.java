package comunicacion;

public class Cliente extends Thread {
	private int numeroConsultas;
	private Mensaje[] mensajes;

	public Cliente(int numeroMensajes) {
		numeroConsultas = numeroMensajes;
		mensajes = new Mensaje[numeroMensajes];
	}

	public void enviarMensaje(int idMen) {
      mensajes[idMen].enviarse();
	}

}

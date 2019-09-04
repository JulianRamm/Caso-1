package comunicacion;

import java.util.ArrayList;

public class Buffer {

	private int tama�oMaximo;
	private int numeroClientes;
	private ArrayList<Mensaje> mensajesRecibidos;
	private ArrayList<Mensaje> mensajesRespuesta;

	public Buffer(int tama�oMaximo, int numeroCLientes) {
		this.tama�oMaximo = tama�oMaximo;
		this.numeroClientes = numeroCLientes;
		this.mensajesRecibidos = new ArrayList<Mensaje>(tama�oMaximo);
		this.mensajesRespuesta = new ArrayList<Mensaje>(tama�oMaximo);
	}

	public void almacenarMensaje(Mensaje men) {
		mensajesRecibidos.add(men.getId(), men);
	}

	public Mensaje darMensaje() {
		return mensajesRecibidos.remove(0);
		
	}

	public void almacenarRespuesta(Mensaje mensaje) {
        mensajesRespuesta.add(mensaje.getId(), mensaje);
	}

}

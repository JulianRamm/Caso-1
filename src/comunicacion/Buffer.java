package comunicacion;

import java.util.ArrayList;

public class Buffer {

	private int tamañoMaximo;
	private int numeroClientes;
	private ArrayList<Mensaje> mensajesRecibidos;
	private ArrayList<Mensaje> mensajesRespuesta;

	public Buffer(int tamañoMaximo, int numeroCLientes) {
		this.tamañoMaximo = tamañoMaximo;
		this.numeroClientes = numeroCLientes;
		this.mensajesRecibidos = new ArrayList<Mensaje>(tamañoMaximo);
		this.mensajesRespuesta = new ArrayList<Mensaje>(tamañoMaximo);
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

package comunicacion;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

	private static int tama�oMaximo;
	private int noMensajes = 0;
	private static Queue<Mensaje> mensajesRecibidos;
	public static String ruta = "algo";
	private static final Buffer BUFF = new Buffer(tama�oMaximo);

	public Buffer(int tama�oMaximo) {
		Buffer.tama�oMaximo = tama�oMaximo;
		Buffer.mensajesRecibidos = new LinkedList<Mensaje>();
	}

	public static Buffer getInstance() {
		return BUFF;
	}

	public synchronized void almacenarMensaje(Mensaje men) {
		System.out
				.println("mensaje " + men.getId() + " esta entrando al buffer almacenarMensaje(men), con un valor de: "
						+ men.getVariable() + " perteneciente al Thread: " + men.getCliente().getName());
		System.out.println("nomensajes: " + noMensajes);
		while (noMensajes == tama�oMaximo) {
			System.out.println("bufferesta lleno");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
		}
		synchronized (men) {
			if (mensajesRecibidos.size() < tama�oMaximo) {
				mensajesRecibidos.add(men);
				noMensajes++;
				System.out.println("Se agreg� el mensaje con id: " + men.getId() + " el cual tiene un valor de: "
						+ men.getVariable() + " perteneciente al Thread: " + men.getCliente().getName());
				try {
					men.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Mensaje darMensaje() {
		Mensaje r = null;
		while (noMensajes == 0) {
			Thread.yield();
		}
		r = mensajesRecibidos.peek();
		synchronized (r) {
			System.out.println("revio?");
			r = mensajesRecibidos.remove();
			noMensajes--;
		}
		return r;
	}

	public int getNoMensajes() {
		return noMensajes;
	}

	public void setNoMensajes(int noMensajes) {
		this.noMensajes = noMensajes;
	}

	public static String getRuta() {
		return ruta;
	}

	public static void setRuta(String ruta) {
		Buffer.ruta = ruta;
	}

	public static void main(String[] args) {
		int tama�oMaximo = 20;
		int numeroClientes = 5;
		int numeroServidores = 6;
		int numeroMensajes = 2;
		Buffer.tama�oMaximo = tama�oMaximo;
		for (int i = 0; i < numeroServidores; i++) {
			Servidor s = new Servidor();
			s.start();

		}
		for (int i = 0; i < numeroClientes; i++) {
			Cliente c = new Cliente(numeroMensajes);
			c.start();

		}
	}
}

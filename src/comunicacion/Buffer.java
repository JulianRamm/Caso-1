package comunicacion;

import java.util.ArrayList;

public class Buffer {

	private int tamañoMaximo;
	private int noMensajes;
	private ArrayList<Mensaje> mensajesRecibidos;
	public static String ruta;

	public Buffer(int tamañoMaximo, int numeroClientes) {
		this.tamañoMaximo = tamañoMaximo;
		this.mensajesRecibidos = new ArrayList<Mensaje>(tamañoMaximo);
	}

	public void almacenarMensaje(Mensaje men) {

		synchronized (this) {
			while (noMensajes == tamañoMaximo) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		synchronized (men) {
			mensajesRecibidos.add(men.getId(), men);
			try {
				men.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			noMensajes++;
		}
	}

	public Mensaje darMensaje() {
		Mensaje r = new Mensaje(1, -1, null);
		synchronized (this) {
			while (noMensajes == 0) {
				Thread.yield();
			}
			noMensajes--;
			r = mensajesRecibidos.remove(0);
			r.notify();
		}
		return r;
	}

	public static void main(String[] args) {
		int tamañoMaximo = 20;
		int numeroClientes = 50;
		int numeroServidores = 100;
		int numeroMensajes = 10;
		System.out.println("sateg1");
		Buffer buff = new Buffer(tamañoMaximo, numeroClientes);
		for (int i = 0; i < numeroServidores; i++) {
			Servidor s = new Servidor(buff);
			s.start();
		}
		System.out.println("sateg2");
		for (int i = 0; i < numeroClientes; i++) {
			Cliente c = new Cliente(numeroMensajes);
			try {
				c.start();
				c.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("sateg3");

		}
	}
}

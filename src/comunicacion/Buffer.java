package comunicacion;

import java.util.ArrayList;

public class Buffer {

	private static int tamañoMaximo;
	private int noMensajes = 0;
	private static ArrayList<Mensaje> mensajesRecibidos;
	public static String ruta = "algo";
	private static final Buffer BUFF = new Buffer(tamañoMaximo);

	public Buffer(int tamañoMaximo) {
		Buffer.tamañoMaximo = tamañoMaximo;
		Buffer.mensajesRecibidos = new ArrayList<Mensaje>(tamañoMaximo);
	}

	public static Buffer getInstance() {
		return BUFF;
	}

	public synchronized void almacenarMensaje(Mensaje men) {
		System.out.println("asnjklsdak");
		System.out.println("mensaje " + men.getId() + " esta entrando al buffer almacenarMensaje(men)");
		System.out.println("nomensajes: " + noMensajes);
		while (noMensajes == tamañoMaximo) {
			System.out.println("bufferesta lleno");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
		}
		System.out.println("entra?");
		synchronized (men) {
			System.out.println("????????????????");
			mensajesRecibidos.add(men.getId(), men);
			noMensajes++;
			System.out.println(
					"Se agregó el mensaje con id: " + men.getId() + " el cual tiene un valor de: " + men.getVariable());
			try {
				System.out.println(" revisando mensaje");
				men.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Mensaje darMensaje() {
		System.out.println("se usa?");
		Mensaje r = new Mensaje(1, -1, null);
		while (noMensajes == 0) {
			Thread.yield();
		}
		synchronized (this) {
			
			System.out.println("no dormir?");
			r = mensajesRecibidos.remove(0);
			noMensajes--;
			r.notify();
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
		int tamañoMaximo = 20;
		int numeroClientes = 5;
		int numeroServidores = 6;
		int numeroMensajes = 2;
		Buffer.tamañoMaximo = tamañoMaximo;
		for (int i = 0; i < numeroServidores; i++) {
			Servidor s = new Servidor();
			s.start();

		}
		System.out.println("fase 2");
		for (int i = 0; i < numeroClientes; i++) {
			Cliente c = new Cliente(numeroMensajes);
			c.start();

		}
	}
}

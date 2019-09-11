package comunicacion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

	private static int tamañoMaximo;
	private int noMensajes = 0;
	private static ArrayList<Mensaje> mensajesRecibidos;
	public static String ruta = "algo";
	private static final Buffer BUFF = new Buffer(tamañoMaximo);

	public Buffer(int tamañoMaximo) {
		Buffer.tamañoMaximo = tamañoMaximo;
		Buffer.mensajesRecibidos = new ArrayList<Mensaje>();
	}

	public static Buffer getInstance() {
		return BUFF;
	}

	public void almacenarMensaje(Mensaje men) {
		System.out
				.println("mensaje " + men.getId() + " esta entrando al buffer almacenarMensaje(men), con un valor de: "
						+ men.getVariable() + " perteneciente al Thread: " + men.getCliente().getName());
		System.out.println("nomensajes: " + noMensajes);
		while (true) {
			synchronized (men) {
				if (mensajesRecibidos.size() < tamañoMaximo) {
					mensajesRecibidos.add(men);
					noMensajes++;
					System.out.println("Se agregó el mensaje con id: " + men.getId() + " el cual tiene un valor de: "
							+ men.getVariable() + " perteneciente al Thread: " + men.getCliente().getName());
					try {
						men.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("bufferesta lleno");
					synchronized (this) {
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}

		}
	}
	
	public Mensaje darMensaje() {
		Mensaje r = null;
		synchronized (this) {
			if (!(Buffer.mensajesRecibidos.isEmpty())) {
				r = mensajesRecibidos.remove(0);
				noMensajes--;
			}
			notifyAll();
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
		int tamañoMaximo = 3;
		int numeroClientes = 2;
		int numeroServidores = 2;
		int numeroMensajes = 2;
		Buffer.tamañoMaximo = tamañoMaximo;
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

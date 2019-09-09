package comunicacion;

import java.util.ArrayList;

public class Buffer {

	private int tamañoMaximo;
	private int noMensajes = 0;
	private ArrayList<Mensaje> mensajesRecibidos;
	public static String ruta;

	public Buffer(int tamañoMaximo, int numeroClientes) {
		this.tamañoMaximo = tamañoMaximo;
		this.mensajesRecibidos = new ArrayList<Mensaje>(tamañoMaximo);
	}

	public void almacenarMensaje(Mensaje men) {
		System.out.println("mensaje"+men.getId()+" esta entrando al buffer almacenarMensaje(men)");
		System.out.println("nomensajes: "+noMensajes);
		synchronized (this) {
			while (noMensajes == tamañoMaximo) {
				System.out.println("bufferesta lleno");
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
			}
		}
		System.out.println("aqui?");
		synchronized (men) {
			mensajesRecibidos.add(men.getId(), men);
			noMensajes++;
			try {
				System.out.println(" revisando mensaje");
				System.out.println(men.getId());
				men.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			noMensajes++;
		}
	}

	public Mensaje darMensaje() {
		Mensaje r = new Mensaje(1, -1, null, this);
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
		int numeroClientes = 5;
		int numeroServidores = 6;
		int numeroMensajes = 2;
		int menInicial = 0;
		Buffer buff = new Buffer(tamañoMaximo, numeroClientes);
		for (int i = 0; i < numeroServidores; i++) {
			Servidor s = new Servidor(buff);
			s.start();
			System.out.println("haber" + i);
		}
		
		System.out.println("sateg2");
		
		for (int i = 0; i < numeroClientes; i++) {
			menInicial = (int) (Math.random() * 10000);
			Cliente c = new Cliente(numeroMensajes);
			for (int j = 0; j < c.getMensajes().length; j++) {
				c.getMensajes()[j] = new Mensaje(menInicial, j, c, buff);
				menInicial++;
			}
			try {
				c.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("sateg3");

		}
	}
}

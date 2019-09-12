package comunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Buffer {

	private static int tamañoMaximo;
	private int noMensajes = 0;
	private static ArrayList<Mensaje> mensajesRecibidos;

	public static String ruta = "configuracion.txt";
	private static final Buffer BUFF = new Buffer(tamañoMaximo);

	public Buffer(int tamañoMaximo) {
		Buffer.tamañoMaximo = tamañoMaximo;
		Buffer.mensajesRecibidos = new ArrayList<Mensaje>(tamañoMaximo);
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
							e.printStackTrace();
						}
					}
				}
			}

		}
	}

	public synchronized Mensaje darMensaje() {
		Mensaje r = null;
		while (!(Buffer.mensajesRecibidos.isEmpty())) {
			r = mensajesRecibidos.remove(0);
			noMensajes--;
		}
		notifyAll();
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

	public static void leerArchivo(String ruta) {
		int tamañoMaximo = 0;
		int numeroClientes = 0;
		int numeroServidores = 0;
		int numeroMensajes = 0;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(ruta))) {
			String line;
			while ((line = br.readLine()) != null) {
				String asd = line.split(":")[1];
				String valorS = line.split(":")[1].substring(0, asd.length() - 1);
				int valor = Integer.parseInt(valorS);
				if (line.contains("Max")) {
					tamañoMaximo = valor;
				} else if (line.contains("Cli")) {
					numeroClientes = valor;
				} else if (line.contains("Serv")) {
					numeroServidores = valor;
				} else {
					numeroMensajes = valor;
				}
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
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

	public static void main(String[] args) {
		leerArchivo(Buffer.ruta);

	}
}

package comunicacion;

public class Cliente extends Thread {
	private int numeroConsultas;
	private Mensaje[] mensajes;
	private int menInicial = (int) (Math.random() * 10000);

	public Cliente(int numeroMensajes) {
		this.numeroConsultas = numeroMensajes;
		mensajes = new Mensaje[numeroConsultas];
	}

	public void run() {
		for (int i = 0; i < mensajes.length; i++) {
			mensajes[i] = new Mensaje(menInicial, (int) (Math.random() * 10000), this);
			System.out
					.println("Mensaje de id: " + mensajes[i].getId() + " con un valor de: " + mensajes[i].getVariable()
							+ " perteneciente al Thread: " + this.getName() + " esta siendo creado");
			menInicial++;
		}
		for (int i = 0; i < mensajes.length; i++) {
			System.out
					.println("Mensaje de id: " + mensajes[i].getId() + " con un valor de: " + mensajes[i].getVariable()
							+ " perteneciente al Thread: " + this.getName() + " esta siendo enviado");
			mensajes[i].enviarse();
			mensajes[i]=null;
		}
	}

	public Mensaje[] getMensajes() {
		return mensajes;
	}

}

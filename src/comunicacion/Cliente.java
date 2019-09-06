package comunicacion;

public class Cliente extends Thread {
	private int numeroConsultas;
	private Mensaje[] mensajes;
    private int menInicial = (int) (Math.random()*10000);
	public Cliente(int numeroMensajes) {
		numeroConsultas = numeroMensajes;
		mensajes = new Mensaje[numeroMensajes];
		for (int i = 0; i < mensajes.length; i++) {
			mensajes[i]=new Mensaje(menInicial, i, this);
			menInicial++;
		}
	}
	
	public void run(){
		for (int i = 0; i < mensajes.length; i++) {
			mensajes[i].enviarse();
		}
      
	}

}

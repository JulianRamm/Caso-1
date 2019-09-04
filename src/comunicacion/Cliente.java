package comunicacion;

public class Cliente extends Thread{
	private int numeroConsultas;

	public Cliente(int numeroMensajes){
		numeroConsultas=numeroMensajes;
	}
}

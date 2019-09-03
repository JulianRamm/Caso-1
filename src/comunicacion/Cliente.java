package comunicacion;

public class Cliente extends Thread{
	private int consultas;

	public Cliente(int numeroMensajes){
		consultas=numeroMensajes;
	}
}

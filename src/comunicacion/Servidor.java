package comunicacion;

public class Servidor extends Thread{
	
	private Buffer buffer;
	private Mensaje mensaje;
	
	public void solicitar(){
		 mensaje = buffer.darMensaje();
	}
	
	public void responder(){
		mensaje.addOneToMessage();
		buffer.almacenarRespuesta(mensaje);
	}
}
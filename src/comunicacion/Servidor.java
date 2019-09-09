package comunicacion;

public class Servidor extends Thread{
	
	private Buffer buffer;
	private Mensaje mensaje;
	
	public Servidor(Buffer buffer){
		this.buffer=buffer;
	}
	public void run(){
		 mensaje = buffer.darMensaje();
		 responder();
		 System.out.println("Mensaje de id: "+mensaje.getId() + " con un valor de: "+mensaje.getVariable() + " fue respondido con el valor:" + mensaje.getVariable());
		 
	}
	
	public void responder(){
		mensaje.addOneToMessage();
		synchronized (this) {
	    	 if(mensaje.getId()==mensaje.getCliente().getMensajes().length-1){
	    		 buffer.notify();
	    	 }
		}	
	}
}
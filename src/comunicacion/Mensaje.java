package comunicacion;

public class Mensaje {

	private int variable;
	private int id;
	private Buffer buffer = Buffer.getInstance();
	private Cliente cliente;

	public Mensaje(int mensaje, int id, Cliente cliente) {
		variable = mensaje;
		this.setId(id);
		this.setCliente(cliente);
	}

	public void addOneToMessage() {
		System.out.println("El mensaje con id: " + this.id + "ha sido repondido con el valor de: " + variable + " el cual es perteneciente al Thread: "+cliente.getName() );
		variable++;
	}

	public void substractOneToMessage() {
		variable--;
	}

	public int getVariable() {
		return variable;
	}

	public void enviarse() {
		System.out.println("Mensaje " + this.id +" con valor de: "+this.variable+ " perteneciente al Thread: "+this.cliente.getName()+  " esta entrando al buffer enviarse()");
		buffer.almacenarMensaje(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

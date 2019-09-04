package comunicacion;

public class Mensaje {

	private int variable;
	private int id;
    private Buffer buffer;
    private Cliente cliente;
	public Mensaje(int mensaje, int id, Cliente cliente) {
		variable = mensaje;
		this.setId(id);
		this.setCliente(cliente);
	}

	public void addOneToMessage() {
		variable++;
	}

	public void substractOneToMessage() {
		variable--;
	}

	public int getVariable() {
		return variable;
	}
	public void enviarse(){
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

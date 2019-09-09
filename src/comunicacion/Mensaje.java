package comunicacion;

public class Mensaje {

	private int variable;
	private int id;
    private Buffer buffer;
    private Cliente cliente;
    
	public Mensaje(int mensaje, int id, Cliente cliente, Buffer buff) {
        this.buffer = buff;
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
		System.out.println("mensaje"+this.id+" esta entrando al buffer enviarse()");
		buffer.almacenarMensaje(this);
	}

	public int getId() {
		return id;
	}
    public void hacerEsperar(){
    	try {
			cliente.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

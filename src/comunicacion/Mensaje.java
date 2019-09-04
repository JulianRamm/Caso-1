package comunicacion;

public class Mensaje {

	private int variable;

	public Mensaje(int mensaje){
		variable = mensaje;
	}

	public void addOneToMessage(){
		variable++;
	}
	public void substractOneToMessage(){
		variable--;
	}
	public int getVariable(){
		return variable;
	}
}

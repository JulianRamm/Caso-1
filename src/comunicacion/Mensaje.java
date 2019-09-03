package comunicacion;

public class Mensaje {

	private int variable;

	public Mensaje(){
		variable=0;
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

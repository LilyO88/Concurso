package concurso;


public abstract class Participante {
	
	final byte NUM_CASILLAS = 8;
	byte[] resultado = new byte[NUM_CASILLAS];
	
	public void setResultado(byte[] resultado) {
		this.resultado = resultado;
	}
	
	public abstract byte[] crearCombPropuesta();

	public abstract byte[] crearCombSecreta();
}

package concurso;


public abstract class Participante {
	
	private final byte NUM_CASILLAS_RESULTADO = 2;
	protected byte[] resultado = new byte[NUM_CASILLAS_RESULTADO];
	
	public void setResultado(byte[] resultado) {
		this.resultado = resultado;
	}
	
	public abstract byte[] crearCombPropuesta();

	public abstract byte[] crearCombSecreta();
}

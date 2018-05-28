package concurso;

public class Combate {

	// Variables
	private String jugador1;
	private String jugador2;
	private byte rondasGanadasJ1;
	private byte rondasGanadasJ2;
	
	// Constructor
	Combate(String jugador1,String jugador2) {
		this.jugador1=jugador1;
		this.jugador2=jugador2;
		rondasGanadasJ1=0;
		rondasGanadasJ2=0;
	}
	
	// Métodos
	protected String comprobarGanadorCombate(byte maxRondas) {
		String ganador=null;
		byte numPartida=1;
		rondasGanadasJ1=0;
		rondasGanadasJ2=0;
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("\nTableros de la izquierda: "+jugador1+"\nTableros de la derecha: "+jugador2+"\n");
		do {
			if (numPartida!=1)
				System.out.print("\n\n\n");
			System.out.println("Partida nº "+numPartida);
			jugarPartida();
			if (rondasGanadasJ1==maxRondas) {
				ganador=jugador1;
				System.out.println("Ha ganado "+jugador1);
				System.out.print("\n\n\n");
			}else if (rondasGanadasJ2==maxRondas) {
				ganador=jugador2;
				System.out.println("Ha ganado "+jugador2);
				System.out.print("\n\n\n");
			}else {
				System.out.println("Recuento de Puntos:");
				System.out.printf("%s: %d/%d\n",jugador1,rondasGanadasJ1,maxRondas);
				System.out.printf("%s: %d/%d\n",jugador2,rondasGanadasJ2,maxRondas);
			}
			numPartida++;
		}while(ganador==null);
		return ganador;
	}
	private void jugarPartida() {
		byte ganador;
		Partida partida = new Partida(jugador1,jugador2);
		ganador=partida.partidaDificil();
		if (ganador==1)
			rondasGanadasJ1++;
		else if (ganador==2)
			rondasGanadasJ2++;
	}
}

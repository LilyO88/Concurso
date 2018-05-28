package concurso;

import java.util.Arrays;

import esther.Esther;
import migue.Migue;

public class Partida {
	/*
	 * La clase Partida instancia una partida de maximo 200 intentos donde dos
	 * jugadores juegan para adivinar el codigo contrario.
	 *
	 */
	protected int numRonda = 1;
	private Participante jugador1;
	private Participante jugador2;
	private byte[] resultado1;
	private byte[] resultado2;
	private boolean esGanador;
	private final int NUMRONDAS = 200;
	private byte[] combinacionOcultaJugador1;
	private byte[] combinacionOcultaJugador2;
	private byte[] combinacionPropuestaJugador1;
	private byte[] combinacionPropuestaJugador2;
	private byte[] mejorCombinacionJugador1 = { 0, 0 };
	private byte[] mejorCombinacionJugador2 = { 0, 0 };

	// Instancia una partida
	
	Partida(String jugador1, String jugador2) {
		this.jugador1=seleccionaJugadores(jugador1);
		this.jugador2=seleccionaJugadores(jugador2);
	}
	
	private Participante seleccionaJugadores(String participanteString) {
		Participante jugador;
		//jugador = new ();
		//*
		switch(participanteString) {
		case "Pablo":
			jugador = new Pablo();
			break;
		case "Migue":
			jugador = new Migue();
			break;
		case "Salva":
			jugador = new Salva();
			break;
		case "AleDiaz":
			jugador = new AleDiaz();
			break;
		case "AleSanchez":
			jugador = new AleSanchez();
			break;
		case "Ruben":
			jugador = new Ruben();
			break;
		case "Jaime":
			jugador = new Jaime();
			break;
		case "Adri":
			jugador = new Adri();
			break;
		case "Ismael":
			jugador = new Ismael();
			break;
		case "Esther":
			jugador = new Esther();
			break;
		case "Lidia":
			jugador = new Lidia();
			break;
		case "Lolo":
			jugador = new Lolo();
			break;
		case "Maria":
			jugador = new Maria();
			break;
		case "Nicolast":
			jugador = new Nicolast();
			break;
		}//*/
		return jugador;
	}
	
	public byte partidaDificil() {

		byte resultado = 0;		
		
		/*
		 * 1. Los jugadores introducen las combinaciones secretas. 
		 * 2. El jugador introduce la combinación propuesta 
		 * 3. Tras obtener la combinación propuesta, se comprueba el resultado. 
		 * 4. Se comprueba el ganador: 
		 * 		4.1. Si obtiene todos los pinchos negros gana. 
		 * 		4.2. Si no obtiene todos los pinchos negros sigue jugando hasta llegar al nº máximo de intentos. 
		 * 5. A la hora de ganar o perder la partida: 
		 * 		5.1. Si gana jugador 1, devuelve 1. 
		 * 		5.2. Si gana jugador 2, devuelve 2. 
		 * 		5.3. Si los intentos llegan al nº máximo de intentos y ambos jugadores no han ganado, devuelve 0.
		 */

		esGanador = false;

		// 1. Los jugadores introducen las combinaciones secretas.
		combinacionOcultaJugador1 = jugador1.crearCombSecreta();
		combinacionOcultaJugador2 = jugador2.crearCombSecreta();

		// 2. El jugador introduce la combinación propuesta
		for (int i = 0; i < NUMRONDAS && !esGanador; i++) {
			combinacionPropuestaJugador1 = jugador1.crearCombPropuesta();
			combinacionPropuestaJugador2 = jugador2.crearCombPropuesta();

			resultado1 = calcularResultado(combinacionOcultaJugador1, combinacionPropuestaJugador2);
			resultado2 = calcularResultado(combinacionOcultaJugador2, combinacionPropuestaJugador1);
			
			//	Se le pasa a jugador la respuesta (pinchos) que ha generado
			
			jugador1.setResultado(resultado2);
			jugador2.setResultado(resultado1);
			
			// 3. Tras obtener la combinación propuesta, se comprueba el resultado.
			comprobarResultado(mejorCombinacionJugador1, resultado1);
			comprobarResultado(mejorCombinacionJugador2, resultado2);

			// Dibujar
			/*		SOLO LA ÚLTIMA COMBINACIÓN
			if (numRonda==1) {
				System.out.print("     ");
				dibujar(combinacionOcultaJugador2,null);
				System.out.print("                 ");
				dibujar(combinacionOcultaJugador1,null);
				System.out.println();
				try {
					Thread.sleep(1000);		// Milisegundos que tarda en pasar de ronda
				}catch (InterruptedException e){
					System.out.println();
				}
			}
			if (Arrays.equals(combinacionOcultaJugador1, combinacionPropuestaJugador2)
					|| Arrays.equals(combinacionOcultaJugador2, combinacionPropuestaJugador1)) {
				esGanador =  true;
				System.out.printf("%3d  ",numRonda);
				dibujar(combinacionPropuestaJugador1, resultado2);
				System.out.print("  ");
				dibujar(combinacionPropuestaJugador2, resultado1);
				System.out.println();
			}
			numRonda++;//*/
			
			
			//*		TODAS LAS COMBINACIONES
			if (numRonda==1) {
				System.out.print("     ");
				dibujar(combinacionOcultaJugador2,null);
				System.out.print("                 ");
				dibujar(combinacionOcultaJugador1,null);
				System.out.println();
			}
			try {
				Thread.sleep(250);		// Milisegundos que tarda en pasar de ronda
			}catch (InterruptedException e){
				System.out.println();
			}
			System.out.printf("%3d  ",numRonda);
			numRonda++;
			dibujar(combinacionPropuestaJugador1, resultado2);
			System.out.print("  ");
			dibujar(combinacionPropuestaJugador2, resultado1);
			System.out.println();//*/
			//*
			if (Arrays.equals(combinacionOcultaJugador1, combinacionPropuestaJugador2)
					|| Arrays.equals(combinacionOcultaJugador2, combinacionPropuestaJugador1)) {
				esGanador =  true;
			}//*/

			
			
		}
		if (!esGanador) {
			// 4. Se comprueba el ganador (caso empate)
			resultado = comprobarGanador(mejorCombinacionJugador1, mejorCombinacionJugador2);
			
		} else if (Arrays.equals(combinacionOcultaJugador1, combinacionPropuestaJugador2)
				&& Arrays.equals(combinacionOcultaJugador2, combinacionPropuestaJugador1)) {
			resultado = 0;
		} else if (Arrays.equals(combinacionOcultaJugador1, combinacionPropuestaJugador2)) {
			resultado = 2;
		} else if (Arrays.equals(combinacionOcultaJugador2, combinacionPropuestaJugador1)) {
			resultado = 1;
		}

		return resultado;

	}

	public void comprobarResultado(byte[] resultadoAntiguo, byte[] resultadoNuevo) {
		if (resultadoNuevo[0] > resultadoAntiguo[0]) {
			resultadoAntiguo[0] = resultadoNuevo[0];
			resultadoAntiguo[1] = resultadoNuevo[1];
		} else if (resultadoNuevo[0] == resultadoAntiguo[0]) {
			if (resultadoNuevo[1] > resultadoAntiguo[1]) {
				resultadoAntiguo[0] = resultadoNuevo[0];
				resultadoAntiguo[1] = resultadoNuevo[1];
			}
		}

	}

	public byte comprobarGanador(byte[] combinacion1, byte[] combinacion2) {
		byte resultado = 0;

		if (combinacion1[0] > combinacion2[0]) {
			resultado = 1;
		} else if (combinacion1[0] < combinacion2[0]) {
			resultado = 2;
		} else if (combinacion1[0] == combinacion2[0]) {
			if (combinacion1[1] > combinacion2[1]) {
				resultado = 1;
			} else if (combinacion1[1] < combinacion2[1]) {
				resultado = 2;
			}
		}
		return resultado;

	}

	private void dibujar(byte[] combinacion, byte[] resultado) {
		int i;
		for (i = 0; i < 8; i++) {
			System.out.printf("%s " + (char) 9209 + RESET, traducirColor(combinacion[i]));
		}
		if (resultado!=null) {
			System.out.printf(" | ");
			for (i = 0; i < resultado[0]; i++) {
				System.out.printf("%s " + (char) 9210 + RESET, traducirColor((byte) 10));
			}
			for (i = 0; i < resultado[1]; i++) {
				System.out.printf("%s " + (char) 9210 + RESET, traducirColor((byte) 11));
			}
			for (i = resultado[0] + resultado[1]; i < 8; i++) {
				System.out.printf("\u001B[37m" + "\u2298 " + RESET);
			} 
		}

	}

	private byte[] calcularResultado(byte combinacionOculta[], byte combinacionPropuesta[]) {
		byte negrasBlancas[] = { 0, 0 };
		byte contadorNegras = 0;
		byte contadorBlancas = 0;
		final byte NUM_CASILLAS = 8;
		boolean aparece;
		boolean comprobadaOculta[] = new boolean[NUM_CASILLAS];
		for (byte i = 0; i < comprobadaOculta.length; i++) // ---- Esto sirve para que las casillas
			comprobadaOculta[i] = false; // comprobadas no se vuelvan a comprobar.
		for (byte i = 0; i < combinacionPropuesta.length; i++) { // ---- Compara las casillas de la
			aparece = false; // combinación propuesta por
			for (byte j = 0; j < combinacionOculta.length && !aparece; j++) { // las casillas una a una de
				if (combinacionPropuesta[i] == combinacionOculta[j] && !comprobadaOculta[j]) { // la combinación
																								// original.
					if (combinacionPropuesta[i] == combinacionOculta[i] && !comprobadaOculta[i]) { // Al hacer esto se
																									// meten las
						contadorNegras++; // casillas comprobadas de la
						comprobadaOculta[i] = true; // combinación original en un
					} else if (combinacionPropuesta[j] == combinacionOculta[j]) { // array para marcarlas y que
						contadorNegras++; // no se comparen 2 veces. La
						comprobadaOculta[j] = true; // ccombinación propuesta no
					} else { // lo necesita porque se
						contadorBlancas++; // recorre de uno en uno.
						comprobadaOculta[j] = true; // Si el color de ficha de
					} // las 2 casillas es la misma,
					aparece = true; // según la posición de ambas
				} // se marcará como ficha negra
			} // o ficha blanca.
		}
		negrasBlancas[0] = contadorNegras;
		negrasBlancas[1] = contadorBlancas;
		return negrasBlancas;
	}

	private String traducirColor(byte byteColor) {
		String color = "";
		switch (byteColor) {
		case 0:
			color = "\u001B[31m";
			break;
		case 1:
			color = "\u001B[32m";
			break;
		case 2:
			color = "\u001B[33m";
			break;
		case 3:
			color = "\u001B[34m";
			break;
		case 4:
			color = "\u001B[35m";
			break;
		case 5:
			color = "\u001B[36m";
			break;
		case 6:
			color = "\u001B[38;5;208m";
			break;
		case 7:
			color = "\u001B[90m";
			break;
		case 8:
			color = "\u001B[95m";
			break;
		case 9:
			color = "\u001B[92m";
			break;
		case 10:
			color = "\u001B[30m";
			break;
		case 11:
			color = "\u001B[37m";
			break;
		}
		return color;
	}

	private static final String RESET = "\u001B[0m";

}

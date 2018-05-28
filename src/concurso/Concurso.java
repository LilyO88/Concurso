package concurso;

import java.util.ArrayList;
import java.util.Random;

public class Concurso {

	ArrayList<String> participantes = new ArrayList<String>();
	ArrayList<String> ganadores = new ArrayList<String>();

	Concurso() {
		participantes.add("Pablo");
		participantes.add("Migue");
		participantes.add("Salva");
		participantes.add("AleDiaz");
		participantes.add("AleSanchez");
		participantes.add("Ruben");
		participantes.add("Jaime");
		participantes.add("Adri");
		participantes.add("Ismael");
		participantes.add("Esther");
		participantes.add("Lidia");
		participantes.add("Lolo");
		participantes.add("Maria");
		participantes.add("Nicolast");
	}
	
	//Si se le introducen menos de 4 participantes, no funciona.

	public void comprobarGanador() {
		final String MARRON = "\u001B[33m";
		final String GRIS = "\u001B[1;30m";
		final String AMARILLO = "\u001B[1;33m";
		final String RESET = "\u001B[0m";
		Random rnd = new Random();
		Combate combate, semifinal1, semifinal2, tercerPuesto, ganadorFinal;
		int numero, i;
		String podio[] = new String[3];
		final byte NUMRONDAS = 5;
		String jugador1, jugador2, jugador3, jugador4;
		
		// No entra en el bucle si la cantidad de participantes es 4 o menos, o sea, la ronda semifinal.
		
		while (!(participantes.size() <= 4 && ganadores.isEmpty())) {
			if(participantes.size()+ganadores.size()==4 || participantes.size()<=1) {
				if (!participantes.isEmpty()) {
					for (i = 0; i < participantes.size(); i++) {
						ganadores.add(participantes.get(i));
					}
				}
				for (i = 0; i < ganadores.size(); i++) {
					participantes.add(ganadores.get(i));
				}
			}else {
				numero = rnd.nextInt(participantes.size());
				jugador1 = participantes.get(numero);
				participantes.remove(numero);

				numero = rnd.nextInt(participantes.size());
				jugador2 = participantes.get(numero);
				participantes.remove(numero);

				combate = new Combate(jugador1, jugador2);

				ganadores.add(combate.comprobarGanadorCombate(NUMRONDAS));
			}
		}

		// Termina el bucle cuando llega a semifinales.

		// SEMIFINALES.
		jugador1 = participantes.get(0);
		jugador2 = participantes.get(1);
		jugador3 = participantes.get(2);
		jugador4 = participantes.get(3);

		semifinal1 = new Combate(jugador1, jugador2);
		ganadores.add(semifinal1.comprobarGanadorCombate(NUMRONDAS));
		participantes.remove(semifinal1.comprobarGanadorCombate(NUMRONDAS));

		semifinal2 = new Combate(jugador3, jugador4);
		ganadores.add(semifinal2.comprobarGanadorCombate(NUMRONDAS));
		participantes.remove(semifinal2.comprobarGanadorCombate(NUMRONDAS));

		// TERCER PUESTO Y FINAL.
		tercerPuesto = new Combate(participantes.get(0), participantes.get(1));
		ganadorFinal = new Combate(ganadores.get(0), ganadores.get(1));
		ganadores.remove(ganadorFinal.comprobarGanadorCombate(NUMRONDAS));

		podio[0] = AMARILLO + "1º " + ganadorFinal.comprobarGanadorCombate(NUMRONDAS).getClass().getSimpleName() + RESET;
		podio[1] = GRIS + "2º " + ganadores.get(0) + RESET;
		podio[2] = MARRON + "3º " + tercerPuesto.comprobarGanadorCombate(NUMRONDAS).getClass().getSimpleName() + RESET;
		
		System.out.printf("\tPODIO:%n%s%n%s%n%s", podio[0], podio[1], podio[2]);

	}

}

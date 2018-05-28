package concurso;

import java.util.ArrayList;
import java.util.Random;

public class Concurso {

	ArrayList<String> participantes = new ArrayList<String>();
	ArrayList<String> ganadores = new ArrayList<String>();

	Concurso() {
		/*
		participantes.add("1");
		participantes.add("2");
		participantes.add("3");
		participantes.add("4");
		participantes.add("5");
		participantes.add("6");
		participantes.add("7");
		participantes.add("8");
		participantes.add("9");
		participantes.add("10");
		participantes.add("11");
		participantes.add("12");
		participantes.add("13");
		participantes.add("14");
		participantes.add("15");
		participantes.add("16");//*/
		//*
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
		participantes.add("Nicolast");//*/
	}
	
	//Si se le introducen menos de 8 participantes, no funciona.

	public void comprobarGanador() {
		final String MARRON = "\u001B[33m";
		final String GRIS = "\u001B[1;30m";
		final String AMARILLO = "\u001B[1;33m";
		final String RESET = "\u001B[0m";
		boolean eliminatoriaDecidida=false;
		Random rnd = new Random();
		Combate combate;
		int numero;
		String podio[] = new String[3];
		final byte NUMRONDAS = 5;
		String jugador1, jugador2, jugador3, jugador4, ganador;
		
		for (byte i=0;i<participantes.size()&&!eliminatoriaDecidida;i++) {
			if (i==16-participantes.size()) {
				eliminatoriaDecidida=true;
				for(byte j=0;j<i;j++) {
					numero = rnd.nextInt(participantes.size());
					System.out.println("Ha pasado de ronda "+participantes.get(numero));
					ganadores.add(participantes.get(numero));
					participantes.remove(numero);
				}
			}
		}
		do {
			do {
				if (participantes.size()==4&&ganadores.isEmpty()) {
					System.out.println("-------------------------");
					System.out.println("COMIENZAN LAS SEMIFINALES");
					System.out.println("-------------------------\n");
					jugador1 = participantes.get(0);
					jugador2 = participantes.get(1);
					jugador3 = participantes.get(2);
					jugador4 = participantes.get(3);	
					
					combate = new Combate(jugador1, jugador2);
					ganador = combate.comprobarGanadorCombate(NUMRONDAS);
					ganadores.add(ganador);
					participantes.remove(ganador);
					
					combate = new Combate(jugador3, jugador4);
					ganador = combate.comprobarGanadorCombate(NUMRONDAS);
					ganadores.add(ganador);
					participantes.remove(ganador);
						
					System.out.println("-----------------");
					System.out.println("COMIENZA LA FINAL");
					System.out.println("-----------------\n");
					combate = new Combate(ganadores.get(0),ganadores.get(1));
					ganador = combate.comprobarGanadorCombate(NUMRONDAS);
					if (ganador == ganadores.get(0)) {
						podio[0] = ganadores.get(0);
						podio[1] = ganadores.get(1);
					}else {
						podio[0] = ganadores.get(1);
						podio[1] = ganadores.get(0);
					}
					System.out.println("-------------------");
					System.out.println("MATANZA POR LOS 10€");
					System.out.println("-------------------\n");
					combate = new Combate(participantes.get(0),participantes.get(1));
					podio[2] = combate.comprobarGanadorCombate(NUMRONDAS);
					
					podio[0] = AMARILLO + "1º " + podio[0] + RESET;
					podio[1] = GRIS + "2º " + podio[1] + RESET;
					podio[2] = MARRON + "3º " + podio[2] + RESET;
					System.out.printf("\tPODIO:\n%s\n%s\n%s", podio[0], podio[1], podio[2]);
					ganadores.clear();
					participantes.clear();
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
			}while(!participantes.isEmpty());
			for (String s:ganadores)
				participantes.add(s);
			ganadores.clear();
		}while(!ganadores.isEmpty()||!participantes.isEmpty());

	}

}

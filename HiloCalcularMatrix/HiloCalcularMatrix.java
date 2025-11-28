package hilos;

import java.util.ArrayList;

public class HiloCalcularMatrix extends Thread {

	String nombre;
	int fila[];
	int numeroColumna;
	int matrizB[][];
	int resultado[][];

	public HiloCalcularMatrix(String nombre, int[] fila, int[][] matrizB, int numeroColumna) {
		super();
		this.nombre = nombre;
		this.fila = fila;
		this.matrizB = matrizB;
		this.numeroColumna = numeroColumna;
	}

	@Override
	public void run() {
		int i;
		int j;
		ArrayList<Integer> resultadoColumnaXfila = new ArrayList<Integer>();

		// Calcula el producto de cada elemento de la fila con la columna
		for (i = 0; i < fila.length; i++) {
			for (j = 0; j < matrizB.length; j++) {
				int columnaXfila = fila[i] * matrizB[j][numeroColumna];
				resultadoColumnaXfila.add(columnaXfila);
				System.out.println(
						nombre + " - fila[" + i + "] * matrizB[" + j + "][" + numeroColumna + "] = " + columnaXfila);
			}
		}

		// Suma todos los productos para obtener el valor final de la celda
		int suma = 0;
		for (Integer valor : resultadoColumnaXfila) {
			suma += valor;
		}

		System.out.println(nombre + " - Resultado final para la columna " + numeroColumna + ": " + suma);
	}

}
package hilos;

import java.util.ArrayList;
import java.util.Arrays;

class ScalarProductMat {

	public static void scalarProductMat(int[][] mat, int k) {

		// number of rows
		int n = mat.length;

		// number of columns
		int m = mat[0].length;

		// Multiply each element by the scalar k
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				mat[i][j] *= k;
	}

	public static class Hilo extends Thread {

		int[] trozoMatriz;
		int numeroEscalar;

		public Hilo(int[] trozoMatriz, int numeroEscalar) {
			super();
			this.trozoMatriz = trozoMatriz;
			this.numeroEscalar = numeroEscalar;
			this.start();
		}

		@Override
		public void run() {
			for (int i = 0; i < trozoMatriz.length; i++) {
				trozoMatriz[i] = trozoMatriz[i] * numeroEscalar;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		long inicio = System.currentTimeMillis();

		int[][] mat = { { 1, 2, 3, 12, 47, 48, 63 }, 
						{ 4, 5, 6, 8, 9, 12, 150, 8 }, 
						{ 7, 8, 9, 33, 25, 87, 22, 36 } };

		int k = 5;

		ArrayList<Hilo> listaHilosCreados = new ArrayList<ScalarProductMat.Hilo>();

		for (int i = 0; i < mat.length; i++) {
			listaHilosCreados.add(new Hilo(mat[i], k));
		}
		for (int j = 0; j < mat.length; j++) {
			listaHilosCreados.get(j).join();
		}

		// scalarProductMat(mat, k);

		for (int[] row : mat) {
			for (int val : row)
				System.out.print(val + " ");
			System.out.println();
		}

		long fin = System.currentTimeMillis();

		System.out.println("Tiempo: " + (fin - inicio) + " ms");
	}

}

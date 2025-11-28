package hilos;

// Driver Class
class MatrixMultiplication {

	// Function to print Matrix
	static void printMatrix(int M[][], int rowSize, int colSize) {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++)
				System.out.print(M[i][j] + " ");
			System.out.println();
		}
	}

	// Function to multiply two matrices A[][] and B[][]
	static void multiplyMatrix(int row1, int col1, int A[][], int row2, int col2, int B[][]) {
		int i, j, k;

		// Print the matrices A and B
		System.out.println("\nMatrix A:");
		printMatrix(A, row1, col1);
		System.out.println("\nMatrix B:");
		printMatrix(B, row2, col2);

		// Check if multiplication is possible
		if (row2 != col1) {
			System.out.println("\nMultiplication Not Possible");
			return;
		}

		// Matrix to store the result
		// The product matrix will be of size row1 x col2
		int C[][] = new int[row1][col2];

		// Multiply the two matrices
		for (i = 0; i < row1; i++) {
			for (j = 0; j < col2; j++) {
				for (k = 0; k < row2; k++)
					C[i][j] += A[i][k] * B[k][j];
			}
		}

		// Print the result
		System.out.println("\nResultant Matrix:");
		printMatrix(C, row1, col2);
	}
}

public class MatrizPorMatriz {
	// Driver code
	public static void main(String[] args) {

		int row1 = 4, col1 = 3, row2 = 3, col2 = 4;

//		 int A[][] = { { 1, 1, 1 },
//                 { 2, 2, 2 },
//                 { 3, 3, 3 },
//                 { 4, 4, 4 } };
//
//   int B[][] = { { 1, 1, 1, 1 },
//                 { 2, 2, 2, 2 },
//                 { 3, 3, 3, 3 } };

		int A[][] = { { 1, 4, 7 },
				// [0][0], [0][1], [0][2]
				{ 2, 5, 8 },
				// [1][0], [1][1], [1][2]
				{ 3, 6, 9 },
				// [2][0], [2][1], [2][2]
				{ 10, 11, 12 }
				// [3][0], [3][1], [3][2]
		};

		// 3x4
		int B[][] = { { 13, 14, 15, 16 },
				// [0][0], [0][1], [0][2], [0][3]
				{ 17, 18, 19, 20 },
				// [1][0], [1][1], [1][2], [1][3]
				{ 21, 22, 23, 24 }
				// [2][0], [2][1], [2][2], [2][3]
		};

//		System.out.println(B.length);
//		System.out.println("-------------");
//		new HiloCalcularMatrix("hiloFila1", A[0], B, 0).run();
//		System.out.println("-------------");
//		new HiloCalcularMatrix("hiloFila1", A[1], B, 1).run();
//		System.out.println("-------------");
//		new HiloCalcularMatrix("hiloFila1", A[2], B, 2).run();
//		System.out.println("-------------");
//		new HiloCalcularMatrix("hiloFila1", A[3], B, 3).run();

		HiloCalcularMatrix h1 = new HiloCalcularMatrix("hiloFila1", A[0], B, 0);
		HiloCalcularMatrix h2 = new HiloCalcularMatrix("hiloFila2", A[1], B, 1);
		HiloCalcularMatrix h3 = new HiloCalcularMatrix("hiloFila3", A[2], B, 2);
		HiloCalcularMatrix h4 = new HiloCalcularMatrix("hiloFila4", A[3], B, 3);

		h1.start();
		h2.start();
		h3.start();
		h4.start();

		// Esperar a que terminen
		try {
			h1.join();
			h2.join();
			h3.join();
			h4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("-------------");

		MatrixMultiplication.multiplyMatrix(row1, col1, A, row2, col2, B);
	}
}
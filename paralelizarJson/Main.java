package paralelizarJson;

public class Main {
	public static void main(String[] args) {

		//object cola
		//JsonObjectQueue theObjectQueue = new JsonObjectQueue();
		//JsonObjectQueue theObjectQueueNormalizar = new JsonObjectQueue();
		
		JsonObjectMultiQueue theQueue = new JsonObjectMultiQueue();
		JsonObjectMultiQueue theQueueNormalizar = new JsonObjectMultiQueue();


		
		//array hilos
		int numeroDeHilos = 3;
		Thread[] hilos = new Thread[numeroDeHilos];
		
		//Leer Objeto
		Runnable task1 = new JsonFileProcessor("movies.json",theQueue);
		
		//Normalizar obejeto
		Runnable task2 = new JsonObjectProcessing(theQueue,theQueueNormalizar);
		
		//Escribir objeto
		Runnable task3 = new JsonFileWriter(theQueueNormalizar);

		
		hilos[0] = new Thread(task1);
		hilos[1] = new Thread(task2);
		hilos[2] = new Thread(task3);

		hilos[0].start();
		hilos[1].start();
		hilos[2].start();

		// Esperar a que todos los hilos terminen
		for (int i = 0; i < numeroDeHilos; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

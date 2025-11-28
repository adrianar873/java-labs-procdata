package daoAccesoPeliculas;

public class MainProbarAccesoPeliDao {
	public static void main(String[] args) throws InterruptedException {

		AccesoPelículasDao daoPeli = new AccesoPelículasDao();
		daoPeli.start();
		
		daoPeli.buscar(4) ;

		Thread.sleep(3000);
		daoPeli.stop();

	}
}

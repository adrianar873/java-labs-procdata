package exOrm;

import io.github.cdimascio.dotenv.Dotenv;
import exOrm.dataWeather.Weather;
import org.bson.types.ObjectId;

import java.util.List;

public class WeatherApp {

	private WeatherDao weatherDao;

	public void run() {
		Dotenv dotenv = Dotenv.load();
		String password = dotenv.get("DB_Password");
		String uri = "mongodb+srv://adrianar:" + password
				+ "@cluster0.hgcsubk.mongodb.net/?retryWrites=true&w=majority";

		MongoConnection.init(uri, "sample_weatherdata");
		weatherDao = new WeatherDao("data");

		insertExample();
		findExample();
		findAllExample();
		updateExample();
		deleteExample();

		MongoConnection.close();
		System.out.println("MongoDB connection closed.");
	}

	private void insertExample() {
		Weather newWeather = new Weather();
		newWeather.setSt("ST123");
		newWeather.setElevation(100);
		weatherDao.insert(newWeather);
		System.out.println("=== Inserted Weather ===");
		System.out.println(newWeather + "\n");
	}

	private void findExample() {
		Weather first = weatherDao.findAll().stream().findFirst().orElse(null);
		if (first != null) {
			System.out.println("=== Found First Weather ===");
			System.out.println(first + "\n");
		}
	}

	private void findAllExample() {
	    System.out.println("=== All Weather Records (up to 10) ===");
	    List<Weather> all = weatherDao.findAll();
	    all.stream().limit(10).forEach(System.out::println);
	    System.out.println();
	}


	private void updateExample() {
		Weather first = weatherDao.findAll().stream().findFirst().orElse(null);
		if (first != null) {
			first.setElevation(first.getElevation() + 50);
			weatherDao.update(first);
			System.out.println("=== Updated Weather ===");
			System.out.println(weatherDao.findById(first.getId()) + "\n");
		}
	}

	private void deleteExample() {
		Weather first = weatherDao.findAll().stream().findFirst().orElse(null);
		if (first != null) {
			ObjectId id = first.getId();
			weatherDao.delete(id);
			System.out.println("=== Deleted Weather with ID: " + id + " ===\n");
		}
	}
}

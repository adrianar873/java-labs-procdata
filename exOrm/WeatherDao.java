package exOrm;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import exOrm.dataWeather.Weather;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

public class WeatherDao {

	private final MongoCollection<Weather> collection;

	public WeatherDao(String collectionName) {
		MongoDatabase db = MongoConnection.getDatabase();
		if (db == null) {
			throw new IllegalStateException(
					"La base de datos no ha sido inicializada. Llama a MongoConnection.init() primero.");
		}
		this.collection = db.getCollection(collectionName, Weather.class);
		if (this.collection == null) {
			throw new IllegalStateException("No se pudo obtener la colección: " + collectionName);
		}
	}

	public void insert(Weather weather) {
		if (weather == null)
			return;
		collection.insertOne(weather);
	}

	public Weather findById(ObjectId id) {
		if (id == null)
			return null;
		return collection.find(Filters.eq("_id", id)).first();
	}

	public List<Weather> findAll() {
		List<Weather> result = new ArrayList<>();
		collection.find().into(result);
		return result;
	}

	public void update(Weather weather) {
		if (weather == null || weather.getId() == null)
			return;
		collection.replaceOne(Filters.eq("_id", weather.getId()), weather);
	}

	public void delete(ObjectId id) {
		if (id == null)
			return;
		collection.deleteOne(Filters.eq("_id", id));
	}
}

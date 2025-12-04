package exOrm;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConnection {

	private static MongoClient mongoClient;
	private static MongoDatabase database;

	// Evita instanciación externa
	private MongoConnection() {
	}

	/**
	 * Inicializa la conexión con MongoDB.
	 * 
	 * @param uri    La URI de conexión (incluyendo usuario y contraseña)
	 * @param dbName Nombre de la base de datos
	 */
	public static void init(String uri, String dbName) {
		if (mongoClient != null) {
			return; // ya está inicializado
		}

		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		ConnectionString connectionString = new ConnectionString(uri);

		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(pojoCodecRegistry).build();

		mongoClient = MongoClients.create(settings);
		database = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
	}

	/**
	 * Devuelve la base de datos inicializada.
	 */
	public static MongoDatabase getDatabase() {
		if (database == null) {
			throw new IllegalStateException("La conexión no ha sido inicializada. Llama a init() primero.");
		}
		return database;
	}

	/**
	 * Cierra la conexión con MongoDB.
	 */
	public static void close() {
		if (mongoClient != null) {
			mongoClient.close();
			mongoClient = null;
			database = null;
		}
	}
}

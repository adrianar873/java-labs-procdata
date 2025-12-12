package aemet;

import io.github.cdimascio.dotenv.Dotenv;
import javax.json.JsonArray;

/**
 * Clase principal de la aplicación que coordina
 * la obtención de datos de AEMET y su inserción en MongoDB.
 */
public class App {

    private final String apiKey;
    private final AemetConnection aemetConnection;
    private final MongoHandler mongoHandler;

    public App() {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("api_key");

        this.aemetConnection = new AemetConnection(apiKey);
        this.mongoHandler = new MongoHandler();
    }

    /**
     * Ejecuta el flujo completo: obtiene estaciones y las inserta en MongoDB.
     */
    public void run() {
        JsonArray estaciones = aemetConnection.getEstacionesArray();

        if (estaciones == null) {
            System.out.println("Error al obtener datos de AEMET");
            return;
        }

        mongoHandler.insertJsonArray("estaciones", estaciones);
    }
}

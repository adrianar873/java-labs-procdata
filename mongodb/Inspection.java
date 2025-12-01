package mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.lang.reflect.*;

class Address {
	private String city;
	private int zip;
	private String street;
	private int number;

	public Address(String city, int zip, String street, int number) {
		this.city = city != null ? city : "";
		this.zip = zip;
		this.street = street != null ? street : "";
		this.number = number;
	}

	public void print() {
		System.out.println("Address: " + number + " " + street + ", " + city + " " + zip);
	}
}

public class Inspection {
	private String id;
	private int certificate_number;
	private String business_name;
	private String date;
	private String result;
	private String sector;
	private Address address;
	Field[] classFields;

	public static Field[] studyAClass(Class<Inspection> aClass) {
		Field[] theFields = aClass.getDeclaredFields();
		return theFields;
	}

	public Inspection(String id, int certificate_number, String business_name, String date, String result,
			String sector, Address address) {

		this.id = id != null ? id : "";
		this.certificate_number = certificate_number;
		this.business_name = business_name != null ? business_name : "";
		this.date = date != null ? date : "";
		this.result = result != null ? result : "";
		this.sector = sector != null ? sector : "";
		this.address = address;
	}

	public void print() {
		System.out.println("=================================");
		System.out.println("ID: " + id);
		System.out.println("Certificate Number: " + certificate_number);
		System.out.println("Business Name: " + business_name);
		System.out.println("Date: " + date);
		System.out.println("Result: " + result);
		System.out.println("Sector: " + sector);
		if (address != null) {
			address.print();
		}
		System.out.println("=================================");
	}

	public static void searchByCertificateNumber(int certificateNumber) {
		Dotenv dotenv = Dotenv.load();
		String password = dotenv.get("DB_Password");
		String uri = "mongodb+srv://adrianar:" + password
				+ "@cluster0.hgcsubk.mongodb.net/?retryWrites=true&w=majority";

		try (MongoClient mongoClient = MongoClients.create(uri)) {
			MongoDatabase database = mongoClient.getDatabase("sample_training");
			MongoCollection<Document> collection = database.getCollection("inspections");

			Bson filter = Filters.eq("certificate_number", certificateNumber);
			FindIterable<Document> results = collection.find(filter);

			boolean found = false;

			for (Document doc : results) {

				Document addrDoc = doc.get("address", Document.class);
				Address address = null;
				if (addrDoc != null) {
					int zipInt = addrDoc.getInteger("zip", 0);
					int numberInt = addrDoc.getInteger("number", 0);
					address = new Address(addrDoc.getString("city"), zipInt, addrDoc.getString("street"), numberInt);
				}

				int certNumInt = doc.getInteger("certificate_number", 0);

				Inspection inspection = new Inspection(doc.getString("id"), certNumInt, doc.getString("business_name"),
						doc.getString("date"), doc.getString("result"), doc.getString("sector"), address);

				inspection.print();
				found = true;
			}

			if (!found) {
				System.out.println("No inspections found with certificate_number: " + certificateNumber);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		searchByCertificateNumber(9278806);
		System.out.println(studyAClass(Inspection.class)); 
		
	}
}
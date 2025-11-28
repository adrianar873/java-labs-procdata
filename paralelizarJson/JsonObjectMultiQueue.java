package paralelizarJson;

import java.util.ArrayList;

import javax.json.JsonObject;

public class JsonObjectMultiQueue {

	Boolean emptyQueue = true, lastElement = false;

	ArrayList<JsonObject> JsonObjects = new ArrayList<JsonObject>();

	public synchronized int getSize() {

		int size;
		if (emptyQueue)
			size = 0;
		else
			size = JsonObjects.size() <= 0 ? 1 : JsonObjects.size();

		return size;
	}

	public synchronized void setLast() {

		lastElement = true;
	}

	public synchronized boolean isLast() {

		return lastElement;
	}

	public synchronized JsonObject getObject() {

		while (emptyQueue) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}

		emptyQueue = true;
		notify();

		JsonObject aux = JsonObjects.get(0);
		JsonObjects.remove(0);
		
		return aux;

	}

	public synchronized void addObject(JsonObject aJsonObject) {

		while (!emptyQueue) {
			try {

				wait();

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		JsonObjects.add(aJsonObject);
		emptyQueue = false;

		notify();
	}
}

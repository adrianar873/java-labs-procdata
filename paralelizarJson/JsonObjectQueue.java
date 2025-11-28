package paralelizarJson;

import javax.json.*;

public class JsonObjectQueue {

	Boolean emptyQueue = true, lastElement = false;

	JsonObject oneJsonObject;

	public synchronized int getSize() {

		int size;
		if (emptyQueue)
			size = 0;
		else
			size = 1;

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

		return oneJsonObject;

	}

	public synchronized void addObject(JsonObject aJsonObject) {

		while (!emptyQueue) {
			try {

				wait();

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		oneJsonObject = aJsonObject;
		emptyQueue = false;

		notify();
	}

}

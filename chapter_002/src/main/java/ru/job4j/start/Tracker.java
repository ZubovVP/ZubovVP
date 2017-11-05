package ru.job4j.start;

import ru.job4j.models.*;
import java.util.*;

public class Tracker {

	private Item[] items = new Item[100];
	private int position = 0;
	private static final Random RN = new Random();

	public Item add(Item item) {
		item.setId(this.generateId());
		this.items[position++] = item;
		return item;
	}
	protected Item findById(String id) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getId().equals(id)) {
				result = item;
				break;
			}
		}
		return result;
	}
	protected Item findByName(String name) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getName().equals(name)) {
				result = item;
				break;
			}
		}
		return result;
	}
	public void update(Item item) {
		for (int i = 0; i < this.items.length; i++) {
			Item it = this.items[i];
			if (it != null && it.getId().equals(item.getId())) {
				this.items[i] = item;
			}
		}
	}
	public void delete(String id) {
		for (int i = 0; i < this.items.length; i++) {
			if (this.items [i] != null && this.items[i].getId().equals(id)) {
				 for (  ; i < this.position - 1; i++) {
				 	this.items[i] = this.items[i + 1];
				 }
				 break;
			}
			this.position--;
		}
	}
	public Item[] getAll() {
		Item[] result = new Item[position];
		for (int index = 0; index != this.position; index++) {
			result[index] = this.items[index];
		}
		return result;
	}
	protected String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
}
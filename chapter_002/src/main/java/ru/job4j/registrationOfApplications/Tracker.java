package ru.job4j.registrationOfApplications;

import java.util.*;

public class Tracker {
	private Item[] items = new Item[100];
	private int position = 0;
	private static final Random RN = new Random();

	protected Item add(Item item) {
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
	protected void update(Item item) {
		for(Item itemOne : items){
			if(itemOne !=null && itemOne.getId().equals(item.getId())){
				itemOne = item;
			}
		}
	}
	protected void delete(String id) {
		for (Item item : items) {
			if (item != null && item.getId().equals(id)){
				 for(int x = this.position; x < items.length-1; x++ ){
				 	items[x] = items[x+1];
				 }
				 break;

			}
		}
	}
	protected Item[] getAll() {
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
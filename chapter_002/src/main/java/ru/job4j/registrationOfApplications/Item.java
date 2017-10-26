package ru.job4j.registrationOfApplications;
public class Item{
	private String id;
	public String name;
	public String description;
	public long create;
	public Item(){
		
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Item item = (Item) o;

		if (create != item.create) return false;
		if (id != null ? !id.equals(item.id) : item.id != null) return false;
		if (name != null ? !name.equals(item.name) : item.name != null) return false;
		return description != null ? description.equals(item.description) : item.description == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (int) (create ^ (create >>> 32));
		return result;
	}

	public Item(String name, String decription, long create){
		this.name = name;
		this.description = description;
		this.create = create;
	}
	public String getName(){
		return this.name;
	}
	public String getDescription(){
		return this.description;
	}
	public long getCreate(){
		return this.create;
	}
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;  
	}
}
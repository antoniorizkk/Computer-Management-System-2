
public class Product {
	private String name, type,storage,ram,display,price,id;
	

	public Product(String name, String type, String storage, String ram, String display, String price,String id) {
		super();
		this.name = name;
		this.type = type;
		this.storage = storage;
		this.ram = ram;
		this.display = display;
		this.price = price;
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getStorage() {
		return storage;
	}

	public String getRam() {
		return ram;
	}

	public String getDisplay() {
		return display;
	}

	public String getPrice() {
		return price;
	}

	public String getId() {
		return id;
	}
	
	
}

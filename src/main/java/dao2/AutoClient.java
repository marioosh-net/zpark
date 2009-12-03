package dao2;

// obiekt do zlaczen tabeli Client i Auto
public class AutoClient {
	private Client client;
	private Auto auto;
	
	public AutoClient(Auto auto, Client client) {
		this.auto = auto;
		this.client = client;
	}

	public String getCname() {
		return this.client.getName();
	}
	
	public String getNr() {
		return this.auto.getNr();
	}
	
	public Integer getType() {
		return this.auto.getType();
	}	
	
	public String getCSurname() {
		return this.client.getSurname();
	}
}

package dao2;

public class AutoTime {

	private Auto auto;
	private Time time;

	public AutoTime(Auto auto, Time time) {
		this.auto = auto;
		this.time = time;
	}

	public String getNr() {
		return this.auto.getNr();
	}

	public java.util.Date getTimeFrom() {
		return this.time.getTimeFrom();
	}

	public java.util.Date getTimeTo() {
		return this.time.getTimeTo();
	}
}

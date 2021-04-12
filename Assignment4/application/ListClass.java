package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ListClass {
	private final SimpleStringProperty Airline;
	private final SimpleStringProperty AirlineNo;
	private final SimpleStringProperty Departure;
	private final SimpleStringProperty Arrival;
	
	public ListClass(String Airline, String AirlineNo, String Departure, String Arrival) {
		this.Airline = new SimpleStringProperty(Airline);
		this.AirlineNo = new SimpleStringProperty(AirlineNo);
		this.Departure = new SimpleStringProperty(Departure);
		this.Arrival = new SimpleStringProperty(Arrival);
	}
	public StringProperty ArlineProperty() {
		return Airline;
	}
	
	public StringProperty ArlineNoProperty() {
		return AirlineNo;
	}
	
	public StringProperty DepartureProperty() {
		return Departure;
	}
	
	public StringProperty ArrivalProperty() {
		return Arrival;
	}
}

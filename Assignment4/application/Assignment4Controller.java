package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Assignment4Controller implements Initializable{
	
	@FXML
    private AnchorPane window;
	
    @FXML
    private ChoiceBox<String> airlineChoice;

    @FXML
    private ChoiceBox<String> airlineNumberChoice;

    @FXML
    private ChoiceBox<String> departureAirportChoice;

    @FXML
    private ChoiceBox<String> arrivalAirportChoice;

    @FXML
    private ListView<String> listInfo;

    @FXML
    private RadioButton AirlineRadio;

    @FXML
    private ToggleGroup airlineRadio;

    @FXML
    private RadioButton AirlineNumberRadio;

    @FXML
    private RadioButton DepartureAirportRadio;

    @FXML
    private RadioButton ArrivalAirportRadio;
    

//create list for each choicebox to insert values
    ObservableList<String> choiceList = FXCollections.observableArrayList("Air Canada", "Air India");
    ObservableList<String> choiceList2 = FXCollections.observableArrayList("ACNumber", "AINumnber");
    ObservableList<String> choiceList3 = FXCollections.observableArrayList("YVR", "YYZ", "NRT");
    ObservableList<String> choiceList4 = FXCollections.observableArrayList("YVR", "YYZ");
    ObservableList<String> listView = FXCollections.observableArrayList();
    ObservableList<String> txtFlight = FXCollections.observableArrayList();
    @FXML
    
    //on radio click, some choiceboxes are disabled and some are left active, different for each radio click
    void OnlyAIrline(ActionEvent event) {
    	Path fp = Paths.get("C:\\Flight\\Flight.txt");
    	if(AirlineRadio.isSelected()) {
    		airlineNumberChoice.setDisable(true);
    		departureAirportChoice.setDisable(true);
    		arrivalAirportChoice.setDisable(true);
    		airlineChoice.setDisable(false);
    		
    		airlineChoice.getItems().removeAll(choiceList);
    		airlineChoice.getItems().addAll(choiceList);
    	}
    	
    	else if(AirlineNumberRadio.isSelected()) {
    		airlineChoice.setDisable(true);
    		departureAirportChoice.setDisable(true);
    		arrivalAirportChoice.setDisable(true);
    		airlineNumberChoice.setDisable(false);
    		
    		airlineNumberChoice.getItems().removeAll(choiceList2);
    		airlineNumberChoice.getItems().addAll(choiceList2);
    	}
    	
    	else if(ArrivalAirportRadio.isSelected()) {
    		airlineNumberChoice.setDisable(true);
    		airlineChoice.setDisable(true);
    		departureAirportChoice.setDisable(true);
    		arrivalAirportChoice.setDisable(false);
    		
    		arrivalAirportChoice.getItems().removeAll(choiceList3);
    		arrivalAirportChoice.getItems().addAll(choiceList3);
    	}
    	
    	else if(DepartureAirportRadio.isSelected()) {
    		airlineNumberChoice.setDisable(true);
    		airlineChoice.setDisable(true);
    		arrivalAirportChoice.setDisable(true);
    		departureAirportChoice.setDisable(false);
    		
    		departureAirportChoice.getItems().removeAll(choiceList4);
    		departureAirportChoice.getItems().addAll(choiceList4);
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		// TODO Auto-generated method stub
		loadListView();
		
	}
	@FXML
	void OnlyDepartureAirport(ActionEvent event) {
		
	}
	
	@FXML
    void onCloseAction(ActionEvent event) {
		Stage stage = (Stage) window.getScene().getWindow();
		stage.close();
		
		
    }
	//loads defult values to list view
	public void loadListView() {
		String airline = "Airline        AirlineNo        Departure       arrival";
		listView.add(airline);
		listInfo.getItems().addAll(listView);
	}
	
	//when search button is clicked, the selection on choiceboxes displays flight.txt
	@FXML
    void onSearch(ActionEvent event) {
		if(AirlineRadio.isSelected())
			airlineList();
		if(AirlineNumberRadio.isSelected())
		    airlineNumberList();
		if(DepartureAirportRadio.isSelected())
		    departureAirportList();
		if(ArrivalAirportRadio.isSelected())
			arrivalAirportList();
    }
	
	public void airlineList() {
		if(airlineChoice.getValue() == "Air Canada") {
			listInfo.getItems().clear();
			FileReader fr;
			try {
				fr = new FileReader("C:\\Flight\\Flight.txt");
				BufferedReader br = new BufferedReader(fr);
				String r;
				
				for(int i = 0; i < 4; i++) {
					r = br.readLine();
					r = r.replaceAll(",", "          ");
					listInfo.getItems().addAll(r);
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
				System.out.print("File is not there "+e);
			}
		}
		else {
			listInfo.getItems().clear();
			FileReader fr2;
			try {
				fr2 = new FileReader("C:\\Flight\\Flight.txt");
				BufferedReader br2 = new BufferedReader(fr2);
				String r2;
				
				for(int i = 0; i < 5; i++) {
					if(i ==4) {
						r2 = br2.readLine();
						r2 = r2.replaceAll(",", "          ");
						listInfo.getItems().addAll(r2);
					}else {
						br2.readLine();
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
				System.out.print("File is not there "+e);
			}
		}
	}
	
	public void airlineNumberList() {
		listInfo.getItems().clear();
		if(airlineNumberChoice.getValue() == "ACNumber") {
			FileReader fr;
			try {
				fr = new FileReader("C:\\Flight\\Flight.txt");
				BufferedReader br = new BufferedReader(fr);
				String r;
				
				for(int i = 0; i < 4; i++) {
					r = br.readLine();
					r = r.replaceAll(",", "          ");
					listInfo.getItems().addAll(r);
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
				System.out.print("File is not there "+e);
			}
		}
		else {
			listInfo.getItems().clear();
			FileReader fr2;
			try {
				fr2 = new FileReader("C:\\Flight\\Flight.txt");
				BufferedReader br2 = new BufferedReader(fr2);
				String r2;
				
				for(int i = 0; i < 5; i++) {
					if(i ==4) {
						r2 = br2.readLine();
						r2 = r2.replaceAll(",", "          ");
						listInfo.getItems().addAll(r2);
					}else {
						br2.readLine();
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
				System.out.print("File is not there "+e);
			}
		}
	}
	
	public void departureAirportList() {
		listInfo.getItems().clear();
		if(departureAirportChoice.getValue() == "YVR") {
			FileReader fr;
			try {
				fr = new FileReader("C:\\Flight\\Flight.txt");
				BufferedReader br = new BufferedReader(fr);
				String r;
				
				for(int i = 0; i < 4; i++) {
					if(i ==0 || i ==1 || i==3 || i==4) {
						r = br.readLine();
						r = r.replaceAll(",", "          ");
						listInfo.getItems().addAll(r);
					}else {
						br.readLine();
					}
					
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
				System.out.print("File is not there "+e);
			}
		}
		else {
			listInfo.getItems().clear();
			FileReader fr2;
			try {
				fr2 = new FileReader("C:\\Flight\\Flight.txt");
				BufferedReader br2 = new BufferedReader(fr2);
				String r2;
				
				for(int i = 0; i < 5; i++) {
					if(i ==2) {
						r2 = br2.readLine();
						r2 = r2.replaceAll(",", "          ");
						listInfo.getItems().addAll(r2);
					}else {
						br2.readLine();
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
				System.out.print("File is not there "+e);
			}
		}
	}
	
	@FXML
    void onClose(ActionEvent event) {

    }
	
	public void arrivalAirportList(){
		listInfo.getItems().clear();
		if(arrivalAirportChoice.getValue() == "YYZ") {
			FileReader fr;
			try {
				fr = new FileReader("C:\\Flight\\Flight.txt");
				BufferedReader br = new BufferedReader(fr);
				String r;
				
				for(int i = 0; i < 4; i++) {
					if(i ==0 || i ==1) {
						r = br.readLine();
						r = r.replaceAll(",", "          ");
						listInfo.getItems().addAll(r);
					}else {
						br.readLine();
					}
					
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
				System.out.print("File is not there "+e);
			}
		}
		
		if(arrivalAirportChoice.getValue() == "NRT") {
			listInfo.getItems().clear();
			FileReader fr2;
			try {
				fr2 = new FileReader("C:\\Flight\\Flight.txt");
				BufferedReader br2 = new BufferedReader(fr2);
				String r2;
				
				for(int i = 0; i < 5; i++) {
					if(i ==3 || i==4) {
						r2 = br2.readLine();
						r2 = r2.replaceAll(",", "          ");
						listInfo.getItems().addAll(r2);
					}else {
						br2.readLine();
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
				System.out.print("File is not there "+e);
			}
		}
		if(arrivalAirportChoice.getValue() == "YVR") {
			listInfo.getItems().clear();
			FileReader fr2;
			try {
				fr2 = new FileReader("C:\\Flight\\Flight.txt");
				BufferedReader br2 = new BufferedReader(fr2);
				String r2;
				
				for(int i = 0; i < 5; i++) {
					if(i ==2) {
						r2 = br2.readLine();
						r2 = r2.replaceAll(",", "          ");
						listInfo.getItems().addAll(r2);
					}else {
						br2.readLine();
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
				System.out.print("File is not there "+e);
			}
		}
		
	}
		
}
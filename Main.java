//Laisvydas Vavilovas R00081683 COM3-B 04/12/2016
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {

	Stage window;
	BorderPane layout,destinationsLayout,timesLayout,passengersLayout,checkoutLayout,finalLayout;
	ComboBox<String> comboBox1,comboBox2,cardExpYear,cardExpMonth;
	ComboBox<Integer> comboBox,comboBox3,comboBox4,comboBox5;
	ListView<String> listView,listView2;
	Scene scene,scene2,scene3,scene4,scene5,scene6;
	ArrayList<CheckBox> spanish,outBag,returnBag,spanish2,outBag2,returnBag2;
	ArrayList<TextField> name,name2,name3,dni,dni2;
	ArrayList<DatePicker> dob,dob2,dob3;
	ArrayList<VBox> questions,questions2,inputs,inputs2,infant;
	ArrayList<HBox> adult,child;
	int count = 1;
	int count2=0;
	int count3=0;
	double price=0.0;
	VBox p,p2,p3;
	RadioButton oneway;
	TextField creditCardInput,cvvNo;
	DatePicker departureDate,returnDate;
	Label names,routes,minors,basicPrice,totalCost,flightBackTimeLabel,refNumber;
	int[][] fares= new int[7][7];
	Button nextButton1;
	ScrollPane scrollPane;
	Random rand;

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("JavaAir");


		////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
		/////////                          FIRST PAGE						///////////////
		///////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////

		Button startButton = new Button("Book a Flight");
		startButton.setMinSize(250, 75);
		startButton.setOnAction(e -> window.setScene(scene2));
		Label welcome = new Label("Welcome to JavaAir");
		welcome.setFont(Font.font("Cambria", FontWeight.BOLD, 76));

		VBox firstPage =new VBox();
		firstPage.setSpacing(100);
		firstPage.setMargin(welcome, new Insets(100,100,100,250));
		firstPage.setMargin(startButton, new Insets(100,100,100,500));
		firstPage.getChildren().addAll(welcome,startButton);

		////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
		/////////                        SECOND PAGE						///////////////
		///////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		Label destinationsLabel = new Label("Select Origin and Destination");
		destinationsLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
		Label travelDatesLabel = new Label("Select Travel Dates");
		travelDatesLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
		Label noOfPassangersLabel = new Label("Select number of passengers");
		Label retDateLabel = new Label("Select Return Date");

		Separator separator1 = new Separator();
		Separator separator2 = new Separator();

		oneway = new RadioButton("One Way");
		oneway.getStyleClass().add("radio-color");

		returnDate = new DatePicker();
		returnDate.setDisable(true);
		departureDate = new DatePicker();
		departureDate.setOnAction(e-> {returnDate.setDisable(false);
		nextButton1.setDisable(false);
		});
		departureDate.setValue(LocalDate.now());
		returnDate.setValue(LocalDate.now().plusDays(1));
		regularDates();

		oneway.setOnAction(e-> {
			if(oneway.isSelected())
			{	
				retDateLabel.setVisible(false);
				returnDate.setDisable(true);
				returnDate.setStyle("-fx-opacity: 0");
				returnDate.getEditor().setStyle("-fx-opacity: 0");
				returnDate.setValue(departureDate.getValue().plusDays(1));
			}
			else{
				retDateLabel.setVisible(true);
				returnDate.setDisable(false);
				returnDate.setStyle("-fx-opacity: 1");
				returnDate.getEditor().setStyle("-fx-opacity: 1");
			}		
		});

		VBox depDate = new VBox();
		depDate.getChildren().addAll(new Label("Select Departure Date"),departureDate);

		VBox retDate = new VBox();
		retDate.getChildren().addAll(retDateLabel,returnDate);

		HBox dates = new HBox();
		dates.setSpacing(175);
		dates.getChildren().addAll(depDate,retDate);

		comboBox1 = new ComboBox<>();
		comboBox1.setPromptText("Origin");
		comboBox1.setMinWidth(250);
		comboBox1.setMinHeight(75);
		comboBox1.setOnAction(e -> {	

			try{			
				comboBox2.getItems().clear();
				comboBox2.setValue("Destination");
				comboBox2.getItems().addAll("Cork(ORK)","Madrid(MAD)","St Brieuc(SBK)","Jersey(JER)","Paris(CDG)","Stansted(STN)","Malaga(AGP)");
				comboBox2.getItems().removeAll(comboBox1.getSelectionModel().getSelectedItem());

				if(comboBox1.getSelectionModel().getSelectedItem().equals("St Brieuc(SBK)"))
				{
					comboBox2.getItems().remove("Jersey(JER)");
				}
				if(comboBox1.getSelectionModel().getSelectedItem().equals("Jersey(JER)"))
				{
					comboBox2.getItems().remove("St Brieuc(SBK)");
				}

			}catch(NullPointerException n)
			{
				System.out.println("");
			}
		});

		comboBox1.getItems().addAll("Cork(ORK)","Madrid(MAD)","St Brieuc(SBK)","Jersey(JER)","Paris(CDG)","Stansted(STN)","Malaga(AGP)");
		comboBox2 = new ComboBox<>();
		comboBox2.setPromptText("Destination");
		comboBox2.setMinWidth(250);
		comboBox2.setMinHeight(75);
		comboBox2.getItems().addAll("Cork(ORK)","Madrid(MAD)","St Brieuc(SBK)","Jersey(JER)","Paris(CDG)","Stansted(STN)","Malaga(AGP)");
		comboBox2.setOnAction(e-> {

			try{
				if(comboBox2.getSelectionModel().getSelectedItem().equals("St Brieuc(SBK)"))
				{
					comboBox1.getItems().remove("Jersey(JER)");
				}
				if(comboBox2.getSelectionModel().getSelectedItem().equals("Jersey(JER)"))
				{
					comboBox1.getItems().remove("St Brieuc(SBK)");
				}
				complicatedDates();
			}catch(NullPointerException n)
			{
				System.out.println("");
			}
		});

		comboBox = new ComboBox<>();
		comboBox.getItems().addAll(1,2,3,4,5,6,7,8);
		comboBox.setValue(1);

		HBox originAndDestination = new HBox();
		originAndDestination.setSpacing(100);
		originAndDestination.getChildren().addAll(comboBox1,comboBox2);

		nextButton1 = new Button("Next");
		nextButton1.setDisable(true);
		nextButton1.setMinSize(250, 75);
		nextButton1.getStyleClass().add("button-blue");
		nextButton1.setOnAction(e -> {	
			if(comboBox1.getSelectionModel().isEmpty() || comboBox2.getSelectionModel().isEmpty())
			{
				alert("Please select both, origin and destination");
			}
			else if(returnDate.getValue().isEqual(departureDate.getValue()) || departureDate.getValue().isAfter(returnDate.getValue()))
			{
				alert("Cannot fly out and back on the same day or your departure date is after your return date");
			}
			else{
				flightTimes();			
				window.setScene(scene3);
				if(oneway.isSelected())
				{
					listView2.setDisable(true);
					flightBackTimeLabel.setStyle("-fx-opacity: 0");
					listView2.setStyle("-fx-opacity: 0");
				}
			}
		});

		HBox numOfPassengers = new HBox();
		numOfPassengers.setSpacing(20);
		numOfPassengers.getChildren().addAll(noOfPassangersLabel,comboBox);

		VBox pageTwo = new VBox();
		pageTwo.setSpacing(1);
		firstPage.setMargin(destinationsLabel, new Insets(30,30,30,100));
		firstPage.setMargin(originAndDestination, new Insets(30,30,30,300));
		firstPage.setMargin(travelDatesLabel, new Insets(30,30,30,100));
		firstPage.setMargin(oneway, new Insets(30,30,30,250));
		firstPage.setMargin(dates, new Insets(30,30,30,300));
		firstPage.setMargin(numOfPassengers, new Insets(30,30,30,250));
		firstPage.setMargin(nextButton1, new Insets(30,30,30,450));
		pageTwo.getChildren().addAll(destinationsLabel,separator1,originAndDestination,travelDatesLabel,separator2,oneway,dates,numOfPassengers,nextButton1);


		////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
		/////////                        THIRD PAGE						///////////////
		///////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////

		Label availableTimeLabel = new Label("Select Available Times");
		availableTimeLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 26));

		Label flightOutTimeLabel = new Label("Flight Out");
		flightOutTimeLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 16));
		flightBackTimeLabel = new Label("Flight Back");
		flightBackTimeLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 16));		


		Button nextButton2 = new Button("Next");
		nextButton2.setDisable(true);
		nextButton2.setOnAction(e -> {
			calculateBasicPrice();
			displayBasicPrice();
			window.setScene(scene4);
			comboBox3.getItems().clear();
			for(int i=1; i<comboBox.getSelectionModel().getSelectedItem()+1;i++)
			{		
				comboBox3.getItems().addAll(i);
			}
		});
		listView = new ListView<String>();
		listView2 = new ListView<String>();
		listView2.setDisable(true);
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(oneway.isSelected()){
					if(newValue!=null){
						nextButton2.setDisable(false);
					}
				}
				else{
					if(newValue!=null){
						listView2.setDisable(false);
						listView2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
							@Override
							public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
								if(newValue!=null){
									nextButton2.setDisable(false);
								}
							}
						});
					}
				}
			}
		});

		nextButton2.setMinSize(250, 75);
		Button backButton1 = new Button("Back");
		backButton1.setOnAction(e -> window.setScene(scene2));

		VBox flightOut =new VBox();
		flightOut.getChildren().addAll(flightOutTimeLabel,listView);

		VBox flightBack =new VBox();
		flightBack.getChildren().addAll(flightBackTimeLabel,listView2);

		HBox flightTimes = new HBox();
		flightTimes.setSpacing(150);
		flightTimes.getChildren().addAll(flightOut,flightBack);
		
		HBox buttons = new HBox();
		buttons.setSpacing(10);
		buttons.setMargin(backButton1, new Insets(30,0,0,0));
		buttons.getChildren().addAll(backButton1,nextButton2);

		VBox pageThree =new VBox();
		pageThree.setMargin(availableTimeLabel, new Insets(30,30,30,100));
		pageThree.setMargin(flightTimes, new Insets(30,30,30,300));
		pageThree.setMargin(buttons, new Insets(30,30,30,450));
		pageThree.getChildren().addAll(availableTimeLabel,flightTimes,buttons);


		////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
		/////////                        FOURTH PAGE						///////////////
		///////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////

		Label passengerDetailsLabel = new Label("Passenger Details");
		passengerDetailsLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
		Label priceLabel = new Label("Basic Single Fare:");
		priceLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 26));

		Separator separator3 = new Separator();

		comboBox3 = new ComboBox<>();
		comboBox3.setMinWidth(200);
		comboBox3.setMinHeight(25);

		comboBox3.setPromptText("Adults 6+");
		comboBox3.setOnAction(e-> {createAdult();

		comboBox4.getItems().clear();
		for(int i=0; i<comboBox.getSelectionModel().getSelectedItem()-comboBox3.getSelectionModel().getSelectedItem()+1;i++)
		{	
			comboBox4.getItems().addAll(i);			
		}
		dobDates();
		});
		comboBox4 = new ComboBox<>();
		comboBox4.setMinWidth(200);
		comboBox4.setMinHeight(25);
		comboBox4.setOnAction(e -> {
			try{
				createChild();
				comboBox5.getItems().clear();
				for(int i=0; i<comboBox.getSelectionModel().getSelectedItem()-(comboBox3.getSelectionModel().getSelectedItem()+comboBox4.getSelectionModel().getSelectedItem())+1;i++)
				{	
					comboBox5.getItems().addAll(i);
				}
				dobDates();
			}catch(NullPointerException n)
			{
				System.out.println("");
			}
		});
		comboBox4.setPromptText("Children(2-5)");
		comboBox5 = new ComboBox<>();
		comboBox5.setMinWidth(200);
		comboBox5.setMinHeight(25);
		comboBox5.setPromptText("Infants(Under 2)");
		comboBox5.setOnAction(e ->{
			try{
				createInfant();
				dobDates();
			}catch(NullPointerException p)
			{
				System.out.println("");
			}
		});


		Button nextButton3 = new Button("Proceed To Checkout");
		nextButton3.setOnAction(e -> {
			if(comboBox3.getSelectionModel().isEmpty() || comboBox4.getSelectionModel().isEmpty()||comboBox5.getSelectionModel().isEmpty())
			{
				alert("You must select all passengers values");
			}
			else if((comboBox3.getSelectionModel().getSelectedItem()*2)<(comboBox4.getSelectionModel().getSelectedItem()+comboBox5.getSelectionModel().getSelectedItem()))
			{
				alert("One Adult cannot book more than 2 children under 6 ears of age");
			}
			else if(comboBox3.getSelectionModel().getSelectedItem()+comboBox4.getSelectionModel().getSelectedItem()+comboBox5.getSelectionModel().getSelectedItem()!=comboBox.getSelectionModel().getSelectedItem())
			{
				alert("You did not select enough passengers");
			}
			else
			{
				int count =0;
				for(int i=0; i<dob.size();i++)
				{
					if(dob.get(i).getValue().isAfter(LocalDate.now().minusYears(18)))
					{
						count++;
					}
				}
				if(count==comboBox3.getSelectionModel().getSelectedItem())
				{
					alert("At least one adult must be present");
				}
				else if(((comboBox3.getSelectionModel().getSelectedItem()-count)*2)<(comboBox4.getSelectionModel().getSelectedItem()+comboBox5.getSelectionModel().getSelectedItem()))
				{
					alert("One Adult cannot book more than 2 children under 6 ears of age");
				}
				else
				{
					displayDetails();
					window.setScene(scene5);
				}
			}
		});
		basicPrice = new Label("");

		nextButton3.setMinSize(500, 25);
		Button backButton3 = new Button("Back");
		backButton3.setOnAction(e -> window.setScene(scene3));
		HBox priceLabels= new HBox();
		priceLabels.getChildren().addAll(priceLabel,basicPrice);

		HBox pageFourLabels = new HBox();
		pageFourLabels.setSpacing(450);
		pageFourLabels.getChildren().addAll(passengerDetailsLabel,priceLabels);

		p = new VBox();
		p2 = new VBox();
		p3= new VBox();

		VBox adultBox=new VBox();
		adultBox.setSpacing(10);
		adultBox.getChildren().addAll(comboBox3,p);

		VBox childBox=new VBox();
		childBox.setSpacing(10);
		childBox.getChildren().addAll(comboBox4,p2);

		VBox infantBox=new VBox();
		infantBox.setSpacing(10);
		infantBox.getChildren().addAll(comboBox5,p3);


		HBox numOfPassangersInputs = new HBox();
		numOfPassangersInputs.setSpacing(100);
		numOfPassangersInputs.getChildren().addAll(adultBox,childBox,infantBox);

		VBox pageFour =new VBox();
		pageFour.setMargin(pageFourLabels, new Insets(30,30,30,100));
		pageFour.setMargin(numOfPassangersInputs, new Insets(30,30,30,100));
		pageFour.setMargin(nextButton3, new Insets(30,30,30,400));
		pageFour.getChildren().addAll(pageFourLabels,separator3,numOfPassangersInputs,nextButton3);


		////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
		/////////                        FIFTH PAGE						///////////////
		///////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////

		Label flightDetails = new Label("Flight Details");
		flightDetails.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
		Separator separator4 = new Separator();
		Label route = new Label("Route: ");
		route.setFont(Font.font("Cambria", FontWeight.BOLD, 14));
		Separator separator5 = new Separator();
		Label passengers = new Label("Passengers:");
		passengers.setFont(Font.font("Cambria", FontWeight.BOLD, 14));
		Label minorsLabel = new Label("Minors:");
		minorsLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 14));
		Separator separator6 = new Separator();
		Label finalPrice = new Label("Total Cost: ");
		finalPrice.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
		Separator separator7 = new Separator();
		names = new Label("");
		routes = new Label("");
		minors = new Label("");
		totalCost= new Label("");

		HBox fCost= new HBox();
		fCost.getChildren().addAll(finalPrice,totalCost);


		Label payment = new Label("Payment");
		payment.setFont(Font.font("Cambria", FontWeight.BOLD, 18));
		Label cardLabel= new Label("Enter credit card number");
		creditCardInput= new TextField();

		cardExpYear= new ComboBox();
		cardExpYear.setPromptText("Year");
		cardExpYear.getItems().addAll("2017","2018","2019","2020","2021","2022");
		cardExpMonth= new ComboBox();
		cardExpMonth.setPromptText("Month");
		cardExpMonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
		cvvNo= new TextField("CVV");
		cvvNo.setMaxWidth(50);
		Label expDateLabel = new Label("Enter Expiry Date:");

		Button checkoutBtn=new Button("Checkout");
		checkoutBtn.setMinSize(250,50);
		checkoutBtn.setOnAction(e-> {
			if(cardExpYear.getSelectionModel().isEmpty() || cardExpMonth.getSelectionModel().isEmpty())
			{
				alert("Please fill out all credit card details");
			}
			else
			{
				cardCheck();
				rand = new Random();
				int ref=rand.nextInt(100000000);
				refNumber.setText("Ref No.: "+ref);
			}
		});

		HBox expDate= new HBox();
		expDate.setSpacing(5);
		expDate.getChildren().addAll(expDateLabel,cardExpYear,cardExpMonth);


		VBox detailsLabels = new VBox();
		detailsLabels.setMargin(flightDetails, new Insets(1,10,10,50));
		detailsLabels.setMargin(route, new Insets(1,10,10,50));
		detailsLabels.setMargin(passengers, new Insets(1,10,10,50));
		detailsLabels.setMargin(names, new Insets(1,10,10,50));
		detailsLabels.setMargin(routes, new Insets(1,10,10,200));
		detailsLabels.setMargin(minorsLabel, new Insets(1,10,10,50));
		detailsLabels.setMargin(minors, new Insets(1,10,10,50));
		detailsLabels.setMargin(finalPrice, new Insets(1,10,10,50));
		detailsLabels.getChildren().addAll(flightDetails,separator4,route,routes,separator5,passengers,names,separator6,minorsLabel,minors,separator7,fCost);


		HBox paymentValidation = new HBox();
		paymentValidation.setSpacing(5);
		paymentValidation.getChildren().addAll(cardLabel,creditCardInput);

		VBox paymentDetails =new VBox();
		paymentDetails.setMargin(cvvNo, new Insets(20,10,10,100));
		paymentDetails.getChildren().addAll(expDate,cvvNo,paymentValidation);

		VBox pageFive = new VBox();
		pageFive.setSpacing(5);
		pageFive.setMargin(payment, new Insets(20,10,10,550));
		pageFive.setMargin(paymentDetails, new Insets(20,10,10,475));
		pageFive.setMargin(checkoutBtn, new Insets(20,10,10,500));
		pageFive.getChildren().addAll(detailsLabels,payment,paymentDetails,checkoutBtn);
		//////////////////////////////////////////////////////////////////////////////////////////////////
		////////////                       LAST  PAGE                                           ////////
		/////////////////////////////////////////////////////////////////////////////////////////////////
		Button restartButton = new Button("Book another Filght");
		restartButton.setMinSize(250, 75);
		restartButton.setOnAction(e -> {window.setScene(scene);
		comboBox1.getItems().clear();
		comboBox2.getItems().clear();
		departureDate.setValue(null);
		returnDate.setValue(null);
		});
		Label congrats = new Label("Booking Successful");
		refNumber = new Label("");
		congrats.setFont(Font.font("Cambria", FontWeight.BOLD, 76));
		refNumber.setFont(Font.font("Cambria", FontWeight.BOLD, 36));

		VBox lastPage =new VBox();
		lastPage.setSpacing(20);
		lastPage.setMargin(congrats, new Insets(50,50,50,275));
		lastPage.setMargin(refNumber, new Insets(50,50,50,425));
		lastPage.setMargin(restartButton, new Insets(50,50,50,500));
		lastPage.getChildren().addAll(congrats,refNumber,restartButton);


		////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
		/////////                          LAYOUT SECTION						///////////////
		///////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		finalLayout=new BorderPane();
		finalLayout.setCenter(lastPage);

		checkoutLayout = new BorderPane();
		checkoutLayout.setCenter(pageFive);

		passengersLayout = new BorderPane();
		passengersLayout.setCenter(pageFour);
		scrollPane = new ScrollPane(passengersLayout);
		scrollPane.setFitToHeight(true);

		destinationsLayout = new BorderPane();
		destinationsLayout.setCenter(pageTwo);

		timesLayout = new BorderPane();
		timesLayout.setCenter(pageThree);


		layout = new BorderPane();
		layout.setCenter(firstPage);
		scene = new Scene(layout, 1200, 800);
		scene.getStylesheets().add("Style.css");

		scene2 = new Scene(destinationsLayout, 1200, 800);
		scene2.getStylesheets().add("Style.css");
		scene3 = new Scene(timesLayout, 1200, 800);
		scene3.getStylesheets().add("Style.css");
		scene4=new Scene(scrollPane,1200,800);
		scene4.getStylesheets().add("Style.css");
		scene5=new Scene(checkoutLayout,1200,800);
		scene5.getStylesheets().add("Style.css");
		scene6=new Scene(finalLayout,1200,800);
		scene6.getStylesheets().add("Style.css");

		window.getIcons().add(new Image(Main.class.getResourceAsStream( "icon.png" ))); 
		window.setScene(scene);
		window.show();

	}
	public void createAdult()
	{
		p.getChildren().clear();
		count=comboBox3.getSelectionModel().getSelectedItem();
		arrays();
		for (int i= 0; i < count; i++)
		{
			adult.add(i,new HBox());
			questions.add(i,new VBox());
			inputs.add(i,new VBox());		
			spanish.add(i,new CheckBox("Spanish?"));
			outBag.add(i,new CheckBox("Outbound Bag?"));
			returnBag.add(i,new CheckBox("Return Bag?"));
			name.add(i,new TextField("Name"));
			dob.add(i,new DatePicker());
			int x = i;
			dni.add(i,new TextField("Enter DNI"));
			dni.get(i).setVisible(false);
			spanish.get(i).setOnAction(e->{
				if(spanish.get(x).isSelected() &&(comboBox1.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox1.getSelectionModel().getSelectedItem().equals("Malaga(AGP)") || comboBox2.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox2.getSelectionModel().getSelectedItem().equals("Malaga(AGP)")))
					dni.get(x).setVisible(true);
				else
					dni.get(x).setVisible(false);
			});

			questions.get(i).getChildren().addAll(spanish.get(i),outBag.get(i),returnBag.get(i));
			questions.get(i).setSpacing(1);
			inputs.get(i).getChildren().addAll(name.get(i),dob.get(i),dni.get(i));
			inputs.get(i).setSpacing(5);
			adult.get(i).getChildren().addAll(inputs.get(i),questions.get(i));
			adult.get(i).setSpacing(5);
			p.getChildren().addAll(adult.get(i));
			p.setSpacing(15);
		}
	}
	public void createChild()
	{
		p2.getChildren().clear();
		count2=comboBox4.getSelectionModel().getSelectedItem();
		arrays2();
		for (int i= 0; i < count2; i++)
		{
			child.add(i,new HBox());
			questions2.add(i,new VBox());
			inputs2.add(i,new VBox());		
			int x = i;
			spanish2.add(i,new CheckBox("Spanish?"));
			outBag2.add(i,new CheckBox("Outbound Bag?"));
			returnBag2.add(i,new CheckBox("Return Bag?"));
			name2.add(i,new TextField("Name"));
			dob2.add(i,new DatePicker());
			dni2.add(i,new TextField("Enter DNI"));
			dni2.get(i).setVisible(false);
			spanish2.get(i).setOnAction(e->{
				if(spanish2.get(x).isSelected() &&(comboBox1.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox1.getSelectionModel().getSelectedItem().equals("Malaga(AGP)") || comboBox2.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox2.getSelectionModel().getSelectedItem().equals("Malaga(AGP)")))
					dni2.get(x).setVisible(true);
				else
					dni2.get(x).setVisible(false);
			});

			questions2.get(i).getChildren().addAll(spanish2.get(i),outBag2.get(i),returnBag2.get(i));
			questions2.get(i).setSpacing(1);
			inputs2.get(i).getChildren().addAll(name2.get(i),dob2.get(i),dni2.get(i));
			inputs2.get(i).setSpacing(5);
			child.get(i).getChildren().addAll(inputs2.get(i),questions2.get(i));
			child.get(i).setSpacing(5);

			p2.getChildren().addAll(child.get(i));
			p2.setSpacing(15);	
		}
	}
	public void createInfant()
	{
		p3.getChildren().clear();
		count3=comboBox5.getSelectionModel().getSelectedItem();
		arrays3();
		for (int i= 0; i < count3; i++)
		{
			infant.add(i,new VBox());			
			name3.add(i,new TextField("Name"));
			dob3.add(i,new DatePicker());
			infant.get(i).getChildren().addAll(name3.get(i),dob3.get(i));
			infant.get(i).setSpacing(5);
			p3.getChildren().addAll(infant.get(i));
			p3.setSpacing(15);	
		}
	}

	public void arrays(){
		adult = new ArrayList<HBox>(count);
		questions = new ArrayList<VBox>(count);
		inputs = new ArrayList<VBox>(count);
		spanish = new ArrayList<CheckBox>(count);
		outBag = new ArrayList<CheckBox>(count);
		returnBag = new ArrayList<CheckBox>(count);
		name = new ArrayList<TextField>(count);
		dni = new ArrayList<TextField>(count);
		dob = new ArrayList<DatePicker>(count);
	}
	public void arrays2(){
		child = new ArrayList<HBox>(count2);
		questions2 = new ArrayList<VBox>(count2);
		inputs2 = new ArrayList<VBox>(count2);
		spanish2 = new ArrayList<CheckBox>(count2);
		outBag2 = new ArrayList<CheckBox>(count2);
		returnBag2 = new ArrayList<CheckBox>(count2);
		name2 = new ArrayList<TextField>(count2);
		dni2 = new ArrayList<TextField>(count2);
		dob2 = new ArrayList<DatePicker>(count2);
	}
	public void arrays3(){

		infant = new ArrayList<VBox>(count3);
		name3 = new ArrayList<TextField>(count3);	
		dob3 = new ArrayList<DatePicker>(count3);
	}
	public void flightTimes()
	{
		String origin=comboBox1.getSelectionModel().getSelectedItem();
		String destination=comboBox2.getSelectionModel().getSelectedItem();
		listView.getItems().clear();
		listView2.getItems().clear();

		if(origin.equals("Cork(ORK)") && destination.equals("Madrid(MAD)")){
			listView.getItems().add("09:20-13:00");
			listView2.getItems().add("18:00-20:00");
		}
		if(destination.equals("Cork(ORK)") && origin.equals("Madrid(MAD)")){
			listView2.getItems().add("09:20-13:00");
			listView.getItems().add("18:00-20:00");
		}
		if(origin.equals("Cork(ORK)") && destination.equals("St Brieuc(SBK)")){
			listView.getItems().add("10:30-14:00");
			listView2.getItems().add("19:00-20:20");
		}
		if(destination.equals("Cork(ORK)") && origin.equals("St Brieuc(SBK)")){
			listView2.getItems().add("10:30-14:00");
			listView.getItems().add("19:00-20:20");
		}
		if(origin.equals("Cork(ORK)") && destination.equals("Jersey(JER)")){
			listView.getItems().add("14:00-16:00");
			listView2.getItems().add("10:00-12:00");
		}
		if(destination.equals("Cork(ORK)") && origin.equals("Jersey(JER)")){
			listView2.getItems().add("14:00-16:00");
			listView.getItems().add("10:00-12:00");
		}
		if(origin.equals("Cork(ORK)") && destination.equals("Paris(CDG)")){
			listView.getItems().addAll("09:00-12:15","16:20-21:05");
			listView2.getItems().addAll("13:30-15:00","22:00-23:50");
		}
		if(destination.equals("Cork(ORK)") && origin.equals("Paris(CDG)")){
			listView2.getItems().addAll("09:00-12:15","16:20-21:05");
			listView.getItems().addAll("13:30-15:00","22:00-23:50");
		}
		if(origin.equals("Cork(ORK)") && destination.equals("Stansted(STN)")){
			listView.getItems().addAll("08:20-09:50","11:20-13:05");
			listView2.getItems().addAll("11:00-12:20","18:00-19:20");
		}
		if(destination.equals("Cork(ORK)") && origin.equals("Stansted(STN)")){
			listView2.getItems().addAll("08:20-09:50","11:20-13:05");
			listView.getItems().addAll("11:00-12:20","18:00-19:20");
		}
		if(origin.equals("Cork(ORK)") && destination.equals("Malaga(AGP)")){
			listView.getItems().addAll("08:00-11:30");
			listView2.getItems().addAll("13:00-14:20");
		}
		if(destination.equals("Cork(ORK)") && origin.equals("Malaga(AGP)")){
			listView2.getItems().addAll("08:00-11:30");
			listView.getItems().addAll("13:00-14:20");
		}
		if(origin.equals("Madrid(MAD)") && destination.equals("St Brieuc(SBK)")){
			listView.getItems().add("12:00-13:30");
			listView2.getItems().add("18:00-20:20");
		}
		if(destination.equals("Madrid(MAD)") && origin.equals("St Brieuc(SBK)")){
			listView2.getItems().add("12:00-13:30");
			listView.getItems().add("18:00-20:20");
		}
		if(origin.equals("Madrid(MAD)") && destination.equals("Jersey(JER)")){
			listView.getItems().add("06:20-08:00");
			listView2.getItems().add("18:00-21:20");
		}
		if(destination.equals("Madrid(MAD)") && origin.equals("Jersey(JER)")){
			listView2.getItems().add("06:20-08:00");
			listView.getItems().add("18:00-21:20");
		}
		if(origin.equals("Madrid(MAD)") && destination.equals("Paris(CDG)")){
			listView.getItems().addAll("08:00-10:00");
			listView2.getItems().addAll("19:20-21:05");
		}
		if(destination.equals("Madrid(MAD)") && origin.equals("Paris(CDG)")){
			listView2.getItems().addAll("08:00-10:00");
			listView.getItems().addAll("19:20-21:05");
		}
		if(origin.equals("Madrid(MAD)") && destination.equals("Stansted(STN)")){
			listView.getItems().addAll("14:00-15:20","19:05-21:20");
			listView2.getItems().addAll("10:20-14:00");
		}
		if(destination.equals("Madrid(MAD)") && origin.equals("Stansted(STN)")){
			listView2.getItems().addAll("14:00-15:20","19:05-21:20");
			listView.getItems().addAll("10:20-14:00");
		}
		if(origin.equals("Madrid(MAD)") && destination.equals("Malaga(AGP)")){
			listView.getItems().addAll("08:00-09:05");
			listView2.getItems().addAll("20:00-21:05");
		}
		if(destination.equals("Madrid(MAD)") && origin.equals("Malaga(AGP)")){
			listView2.getItems().addAll("08:00-09:05");
			listView.getItems().addAll("20:00-21:05");
		}
		if(origin.equals("St Brieuc(SBK)") && destination.equals("Paris(CDG)")){
			listView.getItems().addAll("06:20-07:15");
			listView2.getItems().addAll("19:00-20:05");
		}
		if(destination.equals("St Brieuc(SBK)") && origin.equals("Paris(CDG)"))	{
			listView2.getItems().addAll("06:20-07:15");
			listView.getItems().addAll("19:00-20:05");
		}
		if(origin.equals("St Brieuc(SBK)") && destination.equals("Stansted(STN)")){
			listView.getItems().addAll("08:05-08:30");
			listView2.getItems().addAll("18:00-20:00");
		}
		if(destination.equals("St Brieuc(SBK)") && origin.equals("Stansted(STN)")){
			listView2.getItems().addAll("08:05-08:30");
			listView.getItems().addAll("18:00-20:00");
		}
		if(origin.equals("St Brieuc(SBK)") && destination.equals("Malaga(AGP)")){
			listView.getItems().addAll("12:00-15:30");
			listView2.getItems().addAll("20:00-21:30");
		}
		if(destination.equals("St Brieuc(SBK)") && origin.equals("Malaga(AGP)")){
			listView2.getItems().addAll("12:00-15:30");
			listView.getItems().addAll("20:00-21:30");
		}
		if(origin.equals("Jersey(JER)") && destination.equals("Paris(CDG)")){
			listView.getItems().addAll("08:00-10:15");
			listView2.getItems().addAll("20:00-20:15");
		}
		if(destination.equals("Jersey(JER)") && origin.equals("Paris(CDG)")){
			listView2.getItems().addAll("08:00-10:15");
			listView.getItems().addAll("20:00-20:15");
		}
		if(origin.equals("Jersey(JER)") && destination.equals("Stansted(STN)")){
			listView.getItems().addAll("17:00-18:30");
			listView2.getItems().addAll("09:00-10:30");
		}
		if(destination.equals("Jersey(JER)") && origin.equals("Stansted(STN)")){
			listView2.getItems().addAll("17:00-18:30");
			listView.getItems().addAll("09:00-10:30");
		}
		if(origin.equals("Jersey(JER)") && destination.equals("Malaga(AGP)")){
			listView.getItems().addAll("08:00-11:30");
			listView2.getItems().addAll("18:00-19:30");
		}
		if(destination.equals("Jersey(JER)") && origin.equals("Malaga(AGP)")){
			listView2.getItems().addAll("08:00-11:30");
			listView.getItems().addAll("18:00-19:30");
		}
		if(origin.equals("Paris(CDG)") && destination.equals("Stansted(STN)")){
			listView.getItems().addAll("18:00-18:30");
			listView2.getItems().addAll("09:00-10:30");
		}
		if(destination.equals("Paris(CDG)") && origin.equals("Stansted(STN)")){
			listView2.getItems().addAll("18:00-18:30");
			listView.getItems().addAll("09:00-10:30");
		}
		if(origin.equals("Paris(CDG)") && destination.equals("Malaga(AGP)")){
			listView.getItems().addAll("11:50-13:30");
			listView2.getItems().addAll("18:05-12:30");
		}
		if(destination.equals("Paris(CDG)") && origin.equals("Malaga(AGP)")){
			listView2.getItems().addAll("11:50-13:30");
			listView.getItems().addAll("18:05-12:30");
		}
		if(origin.equals("Stansted(STN)") && destination.equals("Malaga(AGP)")){
			listView.getItems().addAll("11:50-13:30");
			listView2.getItems().addAll("18:05-12:30");
		}
		if(destination.equals("Stansted(STN)") && origin.equals("Malaga(AGP)")){
			listView2.getItems().addAll("08:50-11:00","13:30-16:20");
			listView.getItems().addAll("15:00-16:10","20:35-21:05");
		}
	}
	//Credit card validator
	public void cardCheck()
	{
		int sum=0;
		String card= creditCardInput.getText().toString();

		for (int i=0; i<card.length(); i++)
		{
			int num = Integer.parseInt(creditCardInput.getText().toString().substring(i,i+1),10);

			if (i % 2  == 0) // check if deigit is even
			{
				num *= 2; // if even multiply by 2

				if (num > 9)
				{
					num -= 9;
				}
			}
			sum += num;
		}
		if(sum % 10 != 0 || creditCardInput.getText().isEmpty() || creditCardInput.getText().length()!=16 || card.matches("[a-z]*"))
		{
			alert("Bad credit card number");
		}
		else 
			window.setScene(scene6);
	}
	//DNI validator
	public boolean dniCheck(String number)
	{
		boolean valid = true;
		if(number.toString().length()<9 || number.toString().length()>10)
		{
			valid = false;
		}
		else if(number.toString().length()==10)
		{
			if(number.toString().charAt(8)=='-')
			{
				number=number.substring(0,8)+number.substring(9,10);
			}
			else
			{
				valid = false;
			}
		}
		else if(number.substring(0,8).matches("\\d+"))
		{
			String dni_letters = "TRWAGMYFPDXBNJZSQVHLCKE";
			int dniNo=Integer.parseInt(number.toString().toUpperCase().substring(0,8));
			char letter = dni_letters.toUpperCase().charAt(dniNo%23);

			if(letter != number.toString().toUpperCase().charAt(8))
			{
				alert("bad dni");
				valid=false;
			}	
		}
		else
		{
			valid = false;
		}
		return valid;
	}
	public void alert(String message)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Warning");
		alert.setContentText(message);
		alert.showAndWait();
	}
	//Disables dates after six months from current date and dates before current date
	public void regularDates()
	{
		final Callback<DatePicker, DateCell> dayCellFactory = 
				new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) 
			{
				return new DateCell() 
				{
					@Override
					public void updateItem(LocalDate currentDate, boolean empty) 
					{
						super.updateItem(currentDate, empty);

						if (currentDate.isBefore(LocalDate.now()) || currentDate.isAfter(LocalDate.now().plusMonths(6))) 
						{
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		departureDate.setDayCellFactory(dayCellFactory);

		final Callback<DatePicker, DateCell> dayCellFactory1 = 
				new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) 
			{
				return new DateCell() 
				{
					@Override
					public void updateItem(LocalDate currentDate, boolean empty) 
					{
						super.updateItem(currentDate, empty);

						if (currentDate.isBefore(departureDate.getValue().plusDays(1)) || currentDate.isAfter(returnDate.getValue().plusMonths(6))) 
						{
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		returnDate.setDayCellFactory(dayCellFactory1);
	}
	//Disables certain months according to cities selected
	public void complicatedDates()
	{
		String origin=comboBox1.getSelectionModel().getSelectedItem();
		String destination=comboBox2.getSelectionModel().getSelectedItem();	
		if((origin.equals("Paris(CDG)") && destination.equals("Stansted(STN)"))||(destination.equals("Paris(CDG)") && origin.equals("Stansted(STN)")))
		{
			final Callback<DatePicker, DateCell> dayCellFactory2 = 
					new Callback<DatePicker, DateCell>() {
				@Override
				public DateCell call(final DatePicker datePicker) 
				{
					return new DateCell() 
					{
						@Override
						public void updateItem(LocalDate currentDate, boolean empty) 
						{
							super.updateItem(currentDate, empty);

							if (currentDate.isBefore(LocalDate.now()) || currentDate.isAfter(returnDate.getValue().plusMonths(6)) || currentDate.getMonth()==Month.APRIL )
							{
								setDisable(true);
								setStyle("-fx-background-color: #ffc0cb;");
							}
						}
					};
				}
			};
			departureDate.setDayCellFactory(dayCellFactory2);
		}
		else if((origin.equals("Paris(CDG)") && destination.equals("Stansted(STN)")) || (destination.equals("Paris(CDG)") && origin.equals("Stansted(STN)")))
		{
			final Callback<DatePicker, DateCell> dayCellFactory3 = 
					new Callback<DatePicker, DateCell>() {
				@Override
				public DateCell call(final DatePicker datePicker) 
				{
					return new DateCell() 
					{
						@Override
						public void updateItem(LocalDate currentDate, boolean empty) 
						{
							super.updateItem(currentDate, empty);

							if (currentDate.isBefore(departureDate.getValue()) || currentDate.isAfter(returnDate.getValue().plusMonths(6)) || currentDate.getMonth()==Month.APRIL )
							{
								setDisable(true);
								setStyle("-fx-background-color: #ffc0cb;");
							}
						}
					};
				}
			};
			returnDate.setDayCellFactory(dayCellFactory3);
		}
		else if((origin.equals("St Brieuc(SBK)") && destination.equals("Stansted(STN)"))||(destination.equals("St Brieuc(SBK)") && origin.equals("Stansted(STN)")))
		{
			final Callback<DatePicker, DateCell> dayCellFactory4 = 
					new Callback<DatePicker, DateCell>() {
				@Override
				public DateCell call(final DatePicker datePicker) 
				{
					return new DateCell() 
					{
						@Override
						public void updateItem(LocalDate currentDate, boolean empty) 
						{
							super.updateItem(currentDate, empty);

							if (currentDate.isBefore(LocalDate.now()) || currentDate.isAfter(returnDate.getValue().plusMonths(6)) || (currentDate.getMonth()==Month.APRIL || currentDate.getMonth()==Month.MARCH)) 
							{
								setDisable(true);
								setStyle("-fx-background-color: #ffc0cb;");
							}
						}
					};
				}
			};
			departureDate.setDayCellFactory(dayCellFactory4);
		}
		else if((origin.equals("St Brieuc(SBK)") && destination.equals("Stansted(STN)"))||(destination.equals("St Brieuc(SBK)") && origin.equals("Stansted(STN)")))
		{
			final Callback<DatePicker, DateCell> dayCellFactory5 = 
					new Callback<DatePicker, DateCell>() {
				@Override
				public DateCell call(final DatePicker datePicker) 
				{
					return new DateCell() 
					{
						@Override
						public void updateItem(LocalDate currentDate, boolean empty) 
						{
							super.updateItem(currentDate, empty);

							if (currentDate.isBefore(departureDate.getValue()) || currentDate.isAfter(returnDate.getValue().plusMonths(6)) || (currentDate.getMonth()==Month.APRIL || currentDate.getMonth()==Month.MARCH)) 
							{
								setDisable(true);
								setStyle("-fx-background-color: #ffc0cb;");
							}
						}
					};
				}
			};
			returnDate.setDayCellFactory(dayCellFactory5);
		}
		else
		{
			regularDates();
		}
	}
	//Method for displaying passenger details in final page
	public void displayDetails() {
		String details ="";
		String children="";
		double finalCost=displayBasicPrice();
		finalCost*=comboBox3.getSelectionModel().getSelectedItem();
		for(int i=0; i<name.size(); i++)
		{	
			if(dob.get(i).getValue().isAfter(LocalDate.now().minusYears(18)))
			{
				children += name.get(i).getText() +" (DOB: "+dob.get(i).getValue()+")"+ "\n";	
			}
			if(outBag.get(i).isSelected())
			{
				finalCost+=15;
			}
			if(returnBag.get(i).isSelected())
			{
				finalCost+=15;
			}
			if(outBag.get(i).isSelected() && returnBag.get(i).isSelected()==false)
			{
				details += name.get(i).getText() + "- Outbound Bags-1" +"\n";
			}
			else if(returnBag.get(i).isSelected() && outBag.get(i).isSelected()==false)
			{
				details += name.get(i).getText() + "- Inbound Bags-1" +"\n";
			}
			else if(outBag.get(i).isSelected() && returnBag.get(i).isSelected())
			{
				details += name.get(i).getText() + "- Inbound Bags-1 Outbound Bags-1" +"\n";
			}
			else
			{
				details += name.get(i).getText() +"\n";
			}

			if(spanish.get(i).isSelected() && oneway.isSelected() &&(comboBox1.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox2.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox1.getSelectionModel().getSelectedItem().equals("Malaga(AGP)") || comboBox2.getSelectionModel().getSelectedItem().equals("Malaga(AGP)")))
			{
				if(dniCheck(dni.get(i).getText()))
				{
					finalCost-=5;
				}
			}
			else if(spanish.get(i).isSelected() && oneway.isSelected() &&((comboBox1.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") && comboBox2.getSelectionModel().getSelectedItem().equals("Madrid(MAD)"))|| (comboBox1.getSelectionModel().getSelectedItem().equals("Malaga(AGP)") && comboBox2.getSelectionModel().getSelectedItem().equals("Malaga(AGP)"))))
			{
				if(dniCheck(dni.get(i).getText()))
				{
					finalCost-=10;
				}
			}
			else if(spanish.get(i).isSelected() && oneway.isSelected()==false &&(comboBox1.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox2.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox1.getSelectionModel().getSelectedItem().equals("Malaga(AGP)") || comboBox2.getSelectionModel().getSelectedItem().equals("Malaga(AGP)")))
			{
				if(dniCheck(dni.get(i).getText()))
				{
					finalCost-=10;
				}
			}
			else if(spanish.get(i).isSelected() && oneway.isSelected()==false &&((comboBox1.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") && comboBox2.getSelectionModel().getSelectedItem().equals("Madrid(MAD)"))|| (comboBox1.getSelectionModel().getSelectedItem().equals("Malaga(AGP)") && comboBox2.getSelectionModel().getSelectedItem().equals("Malaga(AGP)"))))
			{
				if(dniCheck(dni.get(i).getText()))
				{
					finalCost-=20;
				}
			}
		}

		if(comboBox4.getSelectionModel().getSelectedItem()!=0)
		{
			for(int j=0; j<name2.size();j++)
			{

				children += name2.get(j).getText() +" (DOB: "+dob2.get(j).getValue()+")"+ "\n";
				if(oneway.isSelected())
				{
					finalCost+=60;
				}
				else
				{
					finalCost+=120;
				}

				if(outBag2.get(j).isSelected())
				{
					finalCost+=15;
				}
				if(returnBag2.get(j).isSelected())
				{
					finalCost+=15;
				}

				if(outBag2.get(j).isSelected() && returnBag2.get(j).isSelected()==false)
				{
					details += name2.get(j).getText() + "- Outbound Bags-1" +"\n";
				}
				else if(returnBag2.get(j).isSelected() && outBag2.get(j).isSelected()==false)
				{
					details += name2.get(j).getText() + "- Inbound Bags-1" +"\n";
				}
				else if(outBag2.get(j).isSelected() && returnBag2.get(j).isSelected())
				{
					details += name2.get(j).getText() + "- Inbound Bags-1 Outbound Bags-1" +"\n";
				}
				else
				{
					details += name2.get(j).getText() +"\n";
				}

				if(spanish2.get(j).isSelected() && oneway.isSelected() &&(comboBox1.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox2.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox1.getSelectionModel().getSelectedItem().equals("Malaga(AGP)") || comboBox2.getSelectionModel().getSelectedItem().equals("Malaga(AGP)")))
				{
					if(dniCheck(dni2.get(j).getText()))
					{
						finalCost-=5;
					}
				}
				else if(spanish2.get(j).isSelected() && oneway.isSelected() &&((comboBox1.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") && comboBox2.getSelectionModel().getSelectedItem().equals("Madrid(MAD)"))|| (comboBox1.getSelectionModel().getSelectedItem().equals("Malaga(AGP)") && comboBox2.getSelectionModel().getSelectedItem().equals("Malaga(AGP)"))))
				{
					if(dniCheck(dni2.get(j).getText()))
					{
						finalCost-=10;
					}
				}
				else if(spanish2.get(j).isSelected() && oneway.isSelected()==false &&(comboBox1.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox2.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") || comboBox1.getSelectionModel().getSelectedItem().equals("Malaga(AGP)") || comboBox2.getSelectionModel().getSelectedItem().equals("Malaga(AGP)")))
				{
					if(dniCheck(dni2.get(j).getText()))
					{
						finalCost-=10;
					}
				}
				else if(spanish2.get(j).isSelected() && oneway.isSelected()==false &&((comboBox1.getSelectionModel().getSelectedItem().equals("Madrid(MAD)") && comboBox2.getSelectionModel().getSelectedItem().equals("Madrid(MAD)"))|| (comboBox1.getSelectionModel().getSelectedItem().equals("Malaga(AGP)") && comboBox2.getSelectionModel().getSelectedItem().equals("Malaga(AGP)"))))
				{
					if(dniCheck(dni2.get(j).getText()))
					{
						finalCost-=20;
					}
				}
			}
		}
		if(comboBox5.getSelectionModel().getSelectedItem()!=0)
		{
			for(int k=0; k<name3.size();k++)
			{	
				children += name3.get(k).getText() +" (DOB: "+dob3.get(k).getValue()+")"+ "\n";
				details += name3.get(k).getText() +"\n";
			}
		}

		names.setText(details);
		names.setFont(Font.font("Cambria", FontWeight.BOLD, 12));
		minors.setText(children);
		minors.setFont(Font.font("Cambria", FontWeight.BOLD, 12));
		totalCost.setText(""+finalCost);
		totalCost.setFont(Font.font("Cambria", FontWeight.BOLD, 20));

		if(oneway.isSelected())
		{
			routes.setText("("+departureDate.getValue()+")    "+comboBox1.getValue()+"\t--------------------------->\t"+comboBox2.getValue() +" Flight Duration: "+listView.getSelectionModel().getSelectedItem());
			routes.setFont(Font.font("Cambria", FontWeight.BOLD, 14));
		}
		else
		{
			routes.setText("("+departureDate.getValue()+")    "+comboBox1.getValue()+"\t--------------------------->\t"+comboBox2.getValue()+" Flight Duration: "+listView.getSelectionModel().getSelectedItem()+"\n"+"("+returnDate.getValue()+")    "+comboBox2.getValue()+"\t--------------------------->\t"+comboBox1.getValue()+" Flight Duration: "+listView2.getSelectionModel().getSelectedItem());
			routes.setFont(Font.font("Cambria", FontWeight.BOLD, 14));
		}
	}
	//Method that calculates basic fare
	public void calculateBasicPrice()
	{		
		fares[0][1]=100; fares[0][2]=100;fares[0][3]=120;fares[0][4]=80;fares[0][5]=40;fares[0][6]=240;
		fares[1][1]=100;fares[1][2]=200;fares[1][3]=200;fares[1][4]=60;fares[1][5]=60;fares[1][6]=60;
		fares[2][0]=100;fares[2][1]=200;fares[2][3]=150;fares[2][4]=80;fares[2][5]=140;
		fares[3][1]=120;fares[3][2]=200;fares[3][3]=0;fares[3][4]=250;fares[3][5]=250;fares[3][6]=280;
		fares[4][1]=80;fares[4][2]=60;fares[4][3]=150;fares[4][4]=0;fares[4][5]=60;fares[4][6]=100;
		fares[5][1]=40;fares[5][2]=60;fares[5][3]=80;fares[5][4]=250;fares[5][5]=0;fares[5][6]=120;
		fares[6][1]=240;fares[6][2]=60;fares[6][3]=140;fares[6][4]=280;fares[6][5]=100;fares[6][6]=120;
	}
	//method for returning basic fare
	public int getBasicPrice()
	{
		int price=fares[comboBox1.getSelectionModel().getSelectedIndex()][comboBox2.getSelectionModel().getSelectedIndex()+1];
		return price;
	}
	//method for that calculates basic fare according to flight dates selected
	private double displayBasicPrice()
	{
		price =getBasicPrice();
		if(oneway.isSelected())
		{
			if(departureDate.getValue().getDayOfWeek().equals(DayOfWeek.FRIDAY) || departureDate.getValue().getDayOfWeek().equals(DayOfWeek.SATURDAY) || departureDate.getValue().getDayOfWeek().equals(DayOfWeek.SUNDAY))
			{
				price+=(price/100)*20;
				basicPrice.setText(""+price);
				basicPrice.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
			}
		}
		else if(oneway.isSelected()==false)
		{
			if((departureDate.getValue().getDayOfWeek().equals(DayOfWeek.FRIDAY) || departureDate.getValue().getDayOfWeek().equals(DayOfWeek.SATURDAY) || departureDate.getValue().getDayOfWeek().equals(DayOfWeek.SUNDAY)) &&!(returnDate.getValue().getDayOfWeek().equals(DayOfWeek.FRIDAY) || returnDate.getValue().getDayOfWeek().equals(DayOfWeek.SATURDAY) || returnDate.getValue().getDayOfWeek().equals(DayOfWeek.SUNDAY)))
			{
				price+=(price/100)*20;
				price+=getBasicPrice();
				basicPrice.setText(""+price);
				basicPrice.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
			}
			else if((returnDate.getValue().getDayOfWeek().equals(DayOfWeek.FRIDAY) || returnDate.getValue().getDayOfWeek().equals(DayOfWeek.SATURDAY) || returnDate.getValue().getDayOfWeek().equals(DayOfWeek.SUNDAY))&&!(departureDate.getValue().getDayOfWeek().equals(DayOfWeek.FRIDAY) || departureDate.getValue().getDayOfWeek().equals(DayOfWeek.SATURDAY) || departureDate.getValue().getDayOfWeek().equals(DayOfWeek.SUNDAY)))
			{
				price+=(price/100)*20;
				price+=getBasicPrice();
				basicPrice.setText(""+price);
				basicPrice.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
			}
			else if((returnDate.getValue().getDayOfWeek().equals(DayOfWeek.FRIDAY) || returnDate.getValue().getDayOfWeek().equals(DayOfWeek.SATURDAY) || returnDate.getValue().getDayOfWeek().equals(DayOfWeek.SUNDAY))&&(departureDate.getValue().getDayOfWeek().equals(DayOfWeek.FRIDAY) || departureDate.getValue().getDayOfWeek().equals(DayOfWeek.SATURDAY) || departureDate.getValue().getDayOfWeek().equals(DayOfWeek.SUNDAY)))
			{
				price*=2;
				price+=(price/100)*20;
				basicPrice.setText(""+price);
				basicPrice.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
			}
		}
		else
		{
			if(oneway.isSelected())
			{
				basicPrice.setText(""+price);
				basicPrice.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
			}
			else
			{
				price*=2;
				basicPrice.setText(""+price);
				basicPrice.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
			}
		}
		return price;
	}
	//Method for disabling some dates of birth
	private void dobDates()
	{
		try{
			for(int i=0; i<dob.size();i++)
			{
				final Callback<DatePicker, DateCell> dayCellFactory = 
						new Callback<DatePicker, DateCell>() {
					@Override
					public DateCell call(final DatePicker datePicker) 
					{
						return new DateCell() 
						{
							@Override
							public void updateItem(LocalDate currentDate, boolean empty) 
							{
								super.updateItem(currentDate, empty);

								if (currentDate.isAfter(LocalDate.now().minusYears(6)) || currentDate.isAfter(LocalDate.now()))
								{
									setDisable(true);
									setStyle("-fx-background-color: #ffc0cb;");
								}
							}
						};
					}
				};
				dob.get(i).setDayCellFactory(dayCellFactory);
				dob.get(i).setValue(LocalDate.now().minusYears(6));
			}
			for(int i=0; i<dob2.size();i++)
			{
				if(comboBox4.getSelectionModel().getSelectedItem()!=0)
				{

					final Callback<DatePicker, DateCell> dayCellFactory = 
							new Callback<DatePicker, DateCell>() {
						@Override
						public DateCell call(final DatePicker datePicker) 
						{
							return new DateCell() 
							{
								@Override
								public void updateItem(LocalDate currentDate, boolean empty) 
								{
									super.updateItem(currentDate, empty);


									if (currentDate.isAfter(LocalDate.now().minusYears(2)) || currentDate.isAfter(LocalDate.now()))
									{
										setDisable(true);
										setStyle("-fx-background-color: #ffc0cb;");
									}


								}
							};
						}
					};

					dob2.get(i).setDayCellFactory(dayCellFactory);
					dob2.get(i).setValue(LocalDate.now().minusYears(2));
				}
			}
			for(int i=0; i<dob3.size();i++)
			{
				if(comboBox5.getSelectionModel().getSelectedItem()!=0)
				{

					final Callback<DatePicker, DateCell> dayCellFactory = 
							new Callback<DatePicker, DateCell>() {
						@Override
						public DateCell call(final DatePicker datePicker) 
						{
							return new DateCell() 
							{
								@Override
								public void updateItem(LocalDate currentDate, boolean empty) 
								{
									super.updateItem(currentDate, empty);

									if (currentDate.isBefore(LocalDate.now().minusYears(2)) || currentDate.isAfter(LocalDate.now()))
									{
										setDisable(true);
										setStyle("-fx-background-color: #ffc0cb;");
									}
								}
							};
						}
					};
					dob3.get(i).setDayCellFactory(dayCellFactory);
					dob3.get(i).setValue(LocalDate.now().minusYears(2));
				}
			}
		}catch(NullPointerException e)
		{
			System.out.println("");
		}
	}
}
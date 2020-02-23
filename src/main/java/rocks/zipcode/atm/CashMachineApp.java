package rocks.zipcode.atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */


public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private TextArea welcomeOutput = new TextArea();
    private TextField atmField = new TextField();
    private TextField errorField = new TextField();
    private TextField emailField = new TextField();
    private TextField idField = new TextField();
    private TextField nameField = new TextField();
    private TextArea newAccountText = new TextArea();
    private CashMachine cashMachine = new CashMachine(new Bank());
    private Stage stage;
    private Bank bank = new Bank();

/////////////////WELCOME SCREEN//////////////////
    private Parent welcomeScreen() {
        VBox vbox  = new VBox(30);
        vbox.setPrefSize(500, 345);
        vbox.setPadding(new Insets(10));
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));


        Text title = new Text();

        title.setEffect(ds);
        title.setCache(true);
        title.setX(150.0f);
        title.setY(270.0f);
        title.setFill(Color.RED);
        title.setText("Welcome to First National PlagueBank!\nPlease enter your I.D. number to log in.");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
        title.setTranslateX(52.0);

        Text t1 = new Text("If you are not currently a member, please click on Create Account.");
        t1.setTranslateX(65.0);
        field.setMaxWidth(250.0);
        field.setTranslateX(125.0);
        Button btnLogin = new Button("Log in");
        btnLogin.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);
            stage.setScene(new Scene(createContent()));
        });



        btnLogin.setStyle("-fx-background-color: #000000; -fx-text-fill: #f7fffc; -fx-font-size: 1.50em;");


        Button btnCreateAccount = new Button("Create Account");
        btnCreateAccount.setOnAction(e -> {
            stage.setScene(new Scene(createAccount()));
        });

        btnCreateAccount.setStyle("-fx-background-color: #000000; -fx-text-fill: #f7fffc; -fx-font-size: 1.50em;");

        welcomeOutput.setMaxWidth(250.0);
        welcomeOutput.setMaxHeight(25.0);
        welcomeOutput.setTranslateX(125.0);

        FlowPane flowpane = new FlowPane();
        flowpane.setHgap(25.0);
        flowpane.setMargin(btnLogin, new Insets(20, 0, 20, 130));
        flowpane.getChildren().add(btnLogin);
        flowpane.getChildren().add(btnCreateAccount);

        vbox.getChildren().addAll(title, t1, field, flowpane, welcomeOutput);



        return vbox;
    }

////////////////ATM WINDOW//////////////////////////////////
    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(400, 250);

        TextArea areaInfo = new TextArea();


        areaInfo.setText(cashMachine.toString());


        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(atmField.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(atmField.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Sign Out");
        btnExit.setOnAction(e -> {
            stage.setScene(new Scene(welcomeScreen()));
        });



        FlowPane flowpane = new FlowPane();
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(atmField, flowpane, areaInfo);
        return vbox;
    }

////////////////////CREATE ACCOUNT/////////////////
    private Parent createAccount() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(400, 200);
        vbox.setPadding(new Insets(10));

        Text t1 = new Text("Please enter your desired 4 Digit I.D. number below:");
        t1.setTranslateX(48.0);
        idField.setMaxWidth(250.0);
        idField.setTranslateX(60.0);

        Text t2 = new Text("Please enter your first and last name below:");
        nameField.setMaxWidth(250.0);
        t2.setTranslateX(67.0);
        nameField.setTranslateX(60.0);

        Text t3 = new Text("Please enter your e-mail address below:");
        emailField.setMaxWidth(250.0);
        t3.setTranslateX(75.0);
        emailField.setTranslateX(60.0);

        Text t4 = new Text("Please choose an account type:");
        t4.setTranslateX(100.0);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Basic Account",
                        "Premium Account"
                );
        final ComboBox comboBox = new ComboBox(options);
        comboBox.setTranslateX(110.0);

        Button btnSubmit = new Button("Create");
        btnSubmit.setOnAction(e -> {
            if(idField.getText().length() != 4) {
                newAccountText.setText("Please enter a a valid 4 digit account number.");
            } else if(nameField.getText().equals("")){
                newAccountText.setText("Please enter a name to create new account.");
            } else if(emailField.getText().equals("")){
                newAccountText.setText("Please enter a valid email address to create new account.");

            } /*else if(
                   ! comboBox.getValue().equals("Basic Account") && !comboBox.getValue().equals("Premium Account"))

            }*/
            else{
                cashMachine.addNewAccount(Integer.parseInt(idField.getText()),nameField.getText(),emailField.getText(),0,comboBox.getValue()+ "");
                stage.setScene(new Scene(createContent()));
            }


            // Clears all content once Scene changes
            /*idField.clear();
            nameField.clear();
            emailField.clear();*/
        });

        Button btnHome = new Button("Home");
        btnHome.setOnAction(e -> {
            stage.setScene(new Scene(welcomeScreen()));
        });

        newAccountText.setMaxWidth(300.0);
        newAccountText.setTranslateX(42.0);


        FlowPane flowpane = new FlowPane();
        flowpane.setHgap(25.0);
        flowpane.setMargin(btnSubmit, new Insets(20, 0, 20, 115));
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnHome);

        vbox.getChildren().addAll(t1, idField, t2, nameField, t3, emailField, t4, comboBox, flowpane, newAccountText);

        return vbox;
    }

    ///////////////////////////////////////////////
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(new Scene(welcomeScreen()));

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
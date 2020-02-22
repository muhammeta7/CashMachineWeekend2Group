package rocks.zipcode.atm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private TextField emailField = new TextField();
    private TextField idField = new TextField();
    private TextField nameField = new TextField();
    private TextField accountTypeField = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    private Stage stage;

/////////////////WELCOME SCREEN//////////////////
    private Parent welcomeScreen() {
        VBox vbox  = new VBox(10);
        vbox.setPrefSize(600, 200);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        TextArea areaInfo = new TextArea();


        Text t = new Text();

        t.setEffect(ds);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(270.0f);
        t.setFill(Color.RED);
        t.setText("Welcome to First National PlagueBank! Please enter your 4 digit I.D. number to log in.");
        t.setFont(Font.font(null, FontWeight.BOLD, 17));

        Text t1 = new Text("If you are not currently a member, please click on Create Account.");

        field.setText("Please enter I.D. number here.");
        field.setMaxWidth(200.0);

        Button btnLogin = new Button("Log in");
        btnLogin.setOnAction(e -> {
            stage.setScene(new Scene(createContent()));
        });


        btnLogin.setStyle("-fx-background-color: #000000; -fx-text-fill: #f7fffc; -fx-font-size: 2em;");


        Button btnCreateAccount = new Button("Create Account");
        btnCreateAccount.setOnAction(e -> {
            stage.setScene(new Scene(createAccount()));
        });

        btnCreateAccount.setStyle("-fx-background-color: #000000; -fx-text-fill: #f7fffc; -fx-font-size: 2em;");


        FlowPane flowpane = new FlowPane();
        flowpane.setHgap(25.0);
        flowpane.setMargin(btnLogin, new Insets(20, 0, 20, 190));
        flowpane.getChildren().add(btnLogin);
        flowpane.getChildren().add(btnCreateAccount);
        vbox.getChildren().addAll(t, t1, field, flowpane);

        return vbox;
    }

////////////////ATM WINDOW//////////////////////////////////
    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 200);

        TextArea areaInfo = new TextArea();

        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnTest = new Button("Back to Welcome");
        btnTest.setOnAction(e -> {
            stage.setScene(new Scene(welcomeScreen()));
        });

        Button btnExit = new Button("Sign Out");
        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText("You have successfully logged out.");
        });


        Button btnAddNewAccount = new Button("Add New Account");
        btnAddNewAccount.setOnAction(e -> {
            Integer id = Integer.parseInt(idField.getText());
            cashMachine.addNewAccount(id,nameField.getText(),emailField.getText(),0,accountTypeField.getText());

            areaInfo.setText(cashMachine.toString());
        });




        FlowPane flowpane = new FlowPane();
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        flowpane.getChildren().add(btnAddNewAccount);
        flowpane.getChildren().add(btnTest);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        return vbox;
    }

////////////////////CREATE ACCOUNT/////////////////
    private Parent createAccount() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 200);
        vbox.setPadding(new Insets(10));

        TextArea areaInfo = new TextArea();

        Button btnSubmit = new Button("Set Account ID");

        vbox.getChildren().addAll(field, areaInfo, btnSubmit,idField,nameField,emailField,accountTypeField);
        return vbox;
    }

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
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
        t.setText("Welcome to First National PlagueBank! Please enter your I.D. number to log in.");
        t.setFont(Font.font(null, FontWeight.BOLD, 17));

        Text t1 = new Text("If you are not currently a member, please click on Create Account.");
        field.setMaxWidth(250.0);
        Button btnLogin = new Button("Log in");
        btnLogin.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);
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

        areaInfo.setText(cashMachine.toString());


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

        Button btnExit = new Button("Sign Out");
        btnExit.setOnAction(e -> {
            stage.setScene(new Scene(welcomeScreen()));
        });



        FlowPane flowpane = new FlowPane();
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        return vbox;
    }

////////////////////CREATE ACCOUNT/////////////////
    private Parent createAccount() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 200);
        vbox.setPadding(new Insets(10));

        Text t1 = new Text("Please enter your desired I.D. number below:");
        idField.setMaxWidth(250.0);

        Text t2 = new Text("Please enter your first and last name below:");
        nameField.setMaxWidth(250.0);

        Text t3 = new Text("Please enter your e-mail address below:");
        emailField.setMaxWidth(250.0);

        Text t4 = new Text("Please choose an account type:");

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Basic Account",
                        "Premium Account"
                );
        final ComboBox comboBox = new ComboBox(options);

        Button btnSubmit = new Button("Create");
        btnSubmit.setOnAction(e -> {
            stage.setScene(new Scene(createContent()));
        });

        Button btnHome = new Button("Home");
        btnHome.setOnAction(e -> {
            stage.setScene(new Scene(welcomeScreen()));
        });


        FlowPane flowpane = new FlowPane();
        flowpane.setHgap(25.0);
        flowpane.setMargin(btnSubmit, new Insets(20, 0, 20, 215));
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnHome);

        vbox.getChildren().addAll(t1, idField, t2, nameField, t3, emailField, t4, comboBox, flowpane);

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
package rocks.zipcode.atm;

import javafx.geometry.Insets;
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


    private Parent welcomeScreen() {
        VBox vbox  = new VBox(10);
        vbox.setPrefSize(600, 200);

        TextArea areaInfo = new TextArea();

        Button btnDeposit = new Button("Go to ATM");
        btnDeposit.setOnAction(e -> {
            stage.setScene(new Scene(createContent()));
        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnDeposit);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        return vbox;
    }


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
        flowpane.getChildren().add(btnTest);
        flowpane.getChildren().add(btnAddNewAccount);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        return vbox;
    }


    private Parent createContent2() {
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
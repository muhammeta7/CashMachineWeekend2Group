package rocks.zipcode.atm;

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


        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        flowpane.getChildren().add(btnTest);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
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
package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;
    private final int balance;
    //private final String pin;


    // TODO: add pin String pin
    AccountData(int id, String name, String email, int balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        //this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    // TODO
    /*public String getPin() {
        return pin;
    }*/

    @Override
    public String toString() {
        return "Account ID: " + id + '\n' +
                "   Name: " + name + '\n' +
                "   Email: " + email + '\n' +
                "   Balance: " + balance;
    }
//    public String toString() {
//        return "Account id: " + id + '\n' +
//                " Name: " + name + '\n' +
//                " Email: " + email + '\n' +
//                " Your new balance: " + balance;
//    }
}

package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;
    // TODO: Need to change balance into Double
    private final int balance;
    private final String pin;

    AccountData(int id, String name, String email, int balance, String pin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.pin = pin;
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

    // TODO need to change into Double type
    public int getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public String toString() {
        String formatBalance = String.format("%1$,.2f", balance);

        return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Balance: $" + formatBalance;
    }
}

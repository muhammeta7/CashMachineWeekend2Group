package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;
    private final int balance;



    // TODO: add pin String pin
    public AccountData(int id, String name, String email, int balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;

    }

    public int getId() {
        return this.id;
    }


    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getBalance() {
        return this.balance;
    }








    @Override
    public String toString() {
        return "Account ID: " + id + '\n' +
                "   Name: " + name + '\n' +
                "   Email: " + email + '\n' +
                "   Balance: " + balance;
    }
}

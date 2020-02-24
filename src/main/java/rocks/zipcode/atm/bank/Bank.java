package rocks.zipcode.atm.bank;

import com.sun.org.apache.xpath.internal.operations.Bool;
import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    // TODO add pin fields to initial users
    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Jeremy M.", "JeremyM@gmail.com", 500
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, " Matthew A. ", "MatthewA@gmail.com", 200
        )));


        // Added new account for Testing purposes
        accounts.put(1025, new BasicAccount(new AccountData(
                1025, "Chip Fody   ", "ChipF@gmail.com          ", 750
        )));

        accounts.put(2025, new BasicAccount(new AccountData(
                2025, "Moe Aydin ", "MoeA@gmail.com            ", 750
        )));

    }


    // Get Account by ID
    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {

            // TODO: update failure message
            return ActionResult.fail("Invalid Account Number");

        }
    }


    // Create New Account

    public ActionResult<AccountData> addNewAccount(int id, String name, String email, int balance, String accountType) {
        Boolean checker = checkAllIds(id);
        if(checker == true) {
            if (accountType.equals("Basic Account")) {
                accounts.put(id, new BasicAccount(new AccountData(id, name, email, 0)));
            }

            if (accountType.equals("Premium Account")) {

                accounts.put(id, new PremiumAccount(new AccountData(id, name, email, 0)));

            }

            Account newAccount = accounts.get(id);
            return ActionResult.success(newAccount.getAccountData());

        }

        return ActionResult.fail("Account is already taken.");

    }

    // CheckPin
   /* public ActionResult<AccountData> checkPin(int id, String pin){
        Account account = accounts.get(id);
        if (account.getAccountData().getPin().equals(pin)){
            return ActionResult.success(account.getAccountData());
        }
        else {
            return  ActionResult.fail("Invalid PIN");
        }
    }*/


    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {

            // format string once we turn amounts into doubles
            // String amountString = String.format("%1$,.2f, amount);
            // String balanceString = String.format("%1f,.2f", account.getBalance());
            return ActionResult.fail("Withdraw failed: $" + amount + ". Account has : $" + account.getBalance());
        }
    }


    public Map<Integer, Account> getAccounts() {
        return accounts;
    }
    



    public Boolean checkAllIds(Integer accountID) {

        for (Integer elements : accounts.keySet()) {
            if (elements.equals(accountID)) {
                return false;

            }
        }
        return true;
    }

    public String getAccountsForAdminPortal() {
        String output = "";
        for(Account elements : accounts.values()){

            output += " Account ID: " + elements.getAccountData().getId()+ " Name: " + elements.getAccountData().getName()
                    + " Email: "+ elements.getAccountData().getEmail() +" Balance: " + elements.getAccountData().getBalance() + "   ";

        }
        return  output;
    }

}


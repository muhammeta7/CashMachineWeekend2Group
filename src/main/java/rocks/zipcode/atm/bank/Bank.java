package rocks.zipcode.atm.bank;

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
                1000, "Example 1", "example1@gmail.com", 500
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", 200
        )));


        // Added new account for Testing purposes
        accounts.put(1025, new BasicAccount(new AccountData(
                1000, "Example 3", "example1@gmail.com", 750
        )));

    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }

    // Create New Account
    // TODO add string pin field (,String pin) also to both types of accounts
    public ActionResult<AccountData> addNewAccount(int id, String name, String email, int balance, String accountType) {
        Map allUserIds = getAccounts();
        for (int i = 0; i <getAccounts().size() ; i++) {
            if (allUserIds.containsKey(i)){



        }
        if (accountType.equals("basic")){
            accounts.put(id, new BasicAccount(new AccountData(id, name, email, 0)));
        }

        if(accountType.equals("premium")){

            accounts.put(id, new PremiumAccount(new AccountData(id,name,email, 0)));

        }

        Account newAccount = accounts.get(id);

        return ActionResult.success(newAccount.getAccountData());
        }
        return ActionResult.fail("Shwoopsie");
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

    public Map<Integer, Account> getAccounts(){
        return this.accounts;
    }

}

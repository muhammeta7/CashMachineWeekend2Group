package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", 500
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", 200
        )));
    }

    // Create New Account
    public ActionResult<AccountData> addNewAccount(int id, String name, String email, int balance, String accountType) {

        if (accountType.equals("basic")){
            accounts.put(id, new BasicAccount(new AccountData(id, name, email, balance)));
        }

        if(accountType.equals("premium")){
            accounts.put(id, new PremiumAccount(new AccountData(id,name,email,balance)));
        }

        Account newAccount = accounts.get(id);

        return ActionResult.success(newAccount.getAccountData());

    }

    // Get Account by ID
    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            // TODO: update failure message
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }


    // TODO: add checkPin method
    // TODO: if we wan to reset a pin

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
            // TODO format string update failure message
            // TODO format amountString
            // TODO format balanceString
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }


}

package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import rocks.zipcode.atm.bank.PremiumAccount;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author ZipCodeWilmington
 */
public class CashMachine {

    private final Bank bank;
    private AccountData accountData = null;
    Boolean withdrawSuccess = false;

    public CashMachine(Bank bank) { this.bank = bank;}


    private Consumer<AccountData> update = data -> {
        accountData = data;
    };

    public void login(int id) {
        tryCall(
                () -> bank.getAccountById(id),
                update
        );
    }

    // TODO method to check pin
    /*public void checkPin(int id, String pin){
        tryCall(
                () -> bank.checkPin(id, pin),
                update
        );
    }*/

    public String deposit(int amount) {
        if (accountData != null) {
            tryCall(
                    () -> bank.deposit(accountData, amount),
                    update);
    }
        //return "Your account balance is now ";
        return null;
    }

    public void withdraw(int amount) {
        /*if (accountData != null) {
            tryCall(
                    () -> bank.withdraw(accountData, amount),
                    update
            );
        }*/
        if (accountData != null) {
            if ((accountData.getBalance() - amount) >= 0){
                withdrawSuccess = true;
            }
            else if (bank.getAccounts().get(accountData.getId()) instanceof PremiumAccount) {
                if ((accountData.getBalance() - amount) >= 100){
                    withdrawSuccess = true;
                }
            }
            tryCall(
                    () -> bank.withdraw(accountData, amount),
                    update
            );
        }
    }

    public void setWithdrawSuccess(Boolean withdrawSuccess) {
        this.withdrawSuccess = withdrawSuccess;
    }

    public Boolean getWithdrawSuccess(){
        return withdrawSuccess;
    }

    // Add new account to update Cash machines  accountData
    public void addNewAccount(int id, String name, String email, int balance, String accountType){
        tryCall(
                () -> bank.addNewAccount(id,name, email, balance, accountType),
                update
        );
    }

    // Add new account to update Cash machines  accountData
//    public void addNewAccount(int id, String name, String email, int balance, String accountType){
//        tryCall(
//                () -> bank.addNewAccount(id,name, email, balance, accountType),
//                update
//        );
//    }

    public void exit() {
        if (accountData != null) {
            accountData = null;
        }
    }

    @Override
    public String toString() {
        return accountData != null ? accountData.toString() : "Thank you for visiting Plague Bank";
    }

    private <T> void tryCall(Supplier<ActionResult<T> > action, Consumer<T> postAction) {
        try {
            ActionResult<T> result = action.get();
            if (result.isSuccess()) {
                T data = result.getData();
                postAction.accept(data);
            } else {
                String errorMessage = result.getErrorMessage();
                throw new RuntimeException(errorMessage);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

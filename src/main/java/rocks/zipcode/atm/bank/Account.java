package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public abstract class Account {

    private AccountData accountData;

    public Account(AccountData accountData) {
        this.accountData = accountData;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void deposit(int amount) {
        if(amount>0) {
            updateBalance(getBalance() + amount);
        }
    }

    public boolean withdraw(int amount) {
        if(amount>0) {
            if (canWithdraw(amount)) {
                updateBalance(getBalance() - amount);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    protected boolean canWithdraw(int amount) {
        return getBalance() >= amount && getBalance() >0;
    }

    public int getBalance() {
        return accountData.getBalance();
    }

    // TODO  add accountData.getPin() as last field;
    private void updateBalance(int newBalance) {
        accountData = new AccountData(accountData.getId(), accountData.getName(), accountData.getEmail(),newBalance);
    }

}

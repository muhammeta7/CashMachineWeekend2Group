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

    // TODO: update amount into double
    public void deposit(int amount) {
        if(amount>0){
        updateBalance(getBalance() + amount);
    }

    }

    // TODO: update amount into double
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

   // TODO: Need to change amount into double
    protected boolean canWithdraw(int amount) {
        return getBalance() >= amount && getBalance() >0;
    }

    public int getBalance() {
        return accountData.getBalance();
    }

    private void updateBalance(int newBalance) {
        accountData = new AccountData(accountData.getId(), accountData.getName(), accountData.getEmail(),
                newBalance, accountData.getPin());
    }

    // Set Account Data Method: Did A stupid pull request to master
    public void setAccountData(AccountData accountdata){
        this.accountData = accountdata;
    }
}

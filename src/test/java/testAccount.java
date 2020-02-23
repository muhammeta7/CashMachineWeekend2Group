import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.Account;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.BasicAccount;
import rocks.zipcode.atm.bank.PremiumAccount;
import sun.util.logging.PlatformLogger;

import java.util.logging.Level;
import java.util.logging.Logger;


public class testAccount {

    private final static Logger logger = Logger.getLogger(testAccount.class.getName());

    @Test
    public void testGetAccountData() {
        Integer id = 345;
        String name = "Bob";
        String email = "gitBob@gmail.com";
        Integer balance = 300;
        AccountData expected = new AccountData(id, name, email, balance);

        Account testAccount = new BasicAccount(expected); // can use b/c BA extends from Account
        AccountData actual = testAccount.getAccountData();

        logger.log(Level.INFO, expected + "" + actual);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDeposit() {
        Integer id = 345;
        String name = "Bob";
        String email = "gitBob@gmail.com";
        Integer balance = 300;
        Integer amount = 200;
        AccountData testAccData = new AccountData(id, name, email, balance);

        Account testAccount = new BasicAccount(testAccData);
        testAccount.deposit(200);
        Integer actual = testAccount.getBalance();
        Integer expected = 500;

        logger.log(Level.INFO, expected + "" + actual);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWithdraw() {
        Integer id = 345;
        String name = "Bob";
        String email = "gitBob@gmail.com";
        Integer balance = 300;
        Integer amount = 200;
        AccountData testAccData = new AccountData(id, name, email, balance);

        Account testAccount = new BasicAccount(testAccData);
        testAccount.withdraw(200);
        Integer actual = testAccount.getBalance();
        Integer expected = 100;

        logger.log(Level.INFO, expected + "" + actual);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWithdraw2() {
        Integer id = 345;
        String name = "Bob";
        String email = "gitBob@gmail.com";
        Integer balance = 300;
        Integer amount = 350;
        AccountData testAccData = new AccountData(id, name, email, balance);

        Account testAccount = new BasicAccount(testAccData);
        testAccount.withdraw(350);
        Integer actual = testAccount.getBalance();
        Integer expected = 300;

        logger.log(Level.INFO, expected + "" + actual);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testWithdraw3() {
        Integer id = 345;
        String name = "Bob";
        String email = "gitBob@gmail.com";
        Integer balance = 300;
        Integer amount = 350;
        AccountData testAccData = new AccountData(id, name, email, balance);

        Account testAccount = new PremiumAccount(testAccData);
        testAccount.withdraw(350);
        Integer actual = testAccount.getBalance();
        Integer expected = -50;

        logger.log(Level.INFO, expected + "" + actual);

        Assert.assertEquals(expected, actual);
    }

}



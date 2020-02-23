import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.ActionResult;
import rocks.zipcode.atm.bank.Account;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import rocks.zipcode.atm.bank.BasicAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testBank {

    private final static Logger logger = Logger.getLogger(testAccount.class.getName());

    @Test
    public void testGetAccountById() {

     //Given
        Bank testBank = new Bank();
     //When
        int expected = 1000;
        ActionResult<AccountData> actual = testBank.getAccountById(1000);

     //Then
        Assert.assertEquals(expected, actual.getData().getId());
    }

    @Test
    public void testGetAccountById2() {

        //Given
        Bank testBank = new Bank();
        //When
        String expected = "Invalid Account Number";
        ActionResult<AccountData> actual = testBank.getAccountById(567);

        //Then
        Assert.assertEquals(expected, actual.getErrorMessage());
    }

    @Test
    public void testAddNewAccount() {
        Bank testBank = new Bank();

        AccountData actual = testBank.addNewAccount(1250, "Rony Ballon", "RonyBallon@gmail.com", 1570, "Basic Account").getData();

        AccountData expected = testBank.getAccountById(1250).getData();

        //logger.log(Level.INFO, "" + actual + expected);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddNewAccount2() {
        Bank testBank = new Bank();

        AccountData actual = testBank.addNewAccount(2050, "Mary Jones", "MJ481@gmail.com", 1125, "Premium Account").getData();

        AccountData expected = testBank.getAccountById(2050).getData();

        //logger.log(Level.INFO, "" + actual + expected);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testAddNewAccount3() {
        Bank testBank = new Bank();

        ActionResult<AccountData> actual = testBank.addNewAccount(1000, "Peter Parker", "akaSpiderman@gmail.com", 25, "Premium Account");
        String expected = "Account is already taken.";

        //logger.log(Level.INFO, "" + actual + expected);
        Assert.assertEquals(expected, actual.getErrorMessage());

    }
    @Test
    public void testBankDeposit() {
        //Given
        Bank testBank = new Bank();
        //When
        int expected = 1250;
        AccountData testPerson = testBank.getAccountById(1000).getData();
        ActionResult<AccountData> actual = testBank.deposit(testPerson,750);

        //Then
        Assert.assertEquals(expected, actual.getData().getBalance());

    }
}

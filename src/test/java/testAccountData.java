import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.Account;
import rocks.zipcode.atm.bank.AccountData;

public class testAccountData {



    @Test
    public void testGetId() {

        Integer expected = 345;
        String name = "Bob";
        String email = "gitBob@gmail.com";
        Integer balance = 300;
        AccountData testData = new AccountData(expected, name, email, balance);

        Integer actual = testData.getId();

        Assert.assertEquals(expected, actual);

    }
    @Test
    public void testGetName() {
        Integer id = 345;
        String expected = "Bob";
        String email = "gitBob@gmail.com";
        Integer balance = 300;
        AccountData testData = new AccountData(id, expected, email, balance);

        String actual = testData.getName();

        Assert.assertEquals(expected, actual);

    }
    @Test
    public void testGetEmail() {
        Integer id = 345;
        String name = "Bob";
        String expected = "gitBob@gmail.com";
        Integer balance = 300;
        AccountData testData = new AccountData(id, name, expected, balance);

        String actual = testData.getEmail();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetBalance() {
        Integer id = 345;
        String name = "Bob";
        String email = "gitBob@gmail.com";
        Integer expected= 300;
        AccountData testData = new AccountData(id, name, email, expected);

        Integer actual = testData.getBalance();

        Assert.assertEquals(expected, actual);
    }

}

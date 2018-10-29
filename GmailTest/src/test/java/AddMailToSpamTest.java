import Message.Message;
import Steps.Steps;
import User.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utils.Screenshoter;


public class AddMailToSpamTest {

    private Steps steps;
    private User user;
    private Message message;

    private final String USER_EMAIL = "epamlabmailtest@mail.ru";
    private final String USERNAME = "epamlabmailtest";
    private final String PASSWORD = "epamlab2018";

    @BeforeClass
    public void setUp()
    {
        user = new User.UserBuilder(USER_EMAIL)
                .withUserName(USERNAME)
                .withPassword(PASSWORD)
                .build();

        message = new Message();
        steps = new Steps();
        steps.initBrowser();
    }

    @Test
    public void loginTest()
    {
        steps.login(user.getUsername(), user.getPassword());
        if(!steps.isLoggedIn(user.getUserEmail()))
            Screenshoter.takeScreenshot();
        Assert.assertTrue(steps.isLoggedIn(user.getUserEmail()));
        steps.logout();
    }

    @Test
    public void sendMailTest()
    {
        steps.login(user.getUsername(), user.getPassword());
        steps.sendMail(message);
        if(!steps.checkSentFolder(message))
            Screenshoter.takeScreenshot();
        Assert.assertTrue(steps.checkSentFolder(message));//check that the e-mail is present in "sent" folder
        steps.logout();
    }

    @Test
    public void addToSpamTest()
    {
        steps.login(user.getUsername(), user.getPassword());
        steps.sendMail(message);
        steps.addMailToSpam(message);
        if(!steps.checkSpamFolder(message))
            Screenshoter.takeScreenshot();
        Assert.assertTrue(steps.checkSpamFolder(message));
        steps.logout();
    }

    @AfterClass
    public void tearDown()
    {
        steps = new Steps();
        steps.closeBrowser();
    }
}

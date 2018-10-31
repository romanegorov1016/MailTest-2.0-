import Message.Message;
import Steps.Steps;
import User.User;
import Utils.Listener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(Listener.class)
public class SendMailTest {

    private Steps steps;
    private Message message;
    private User user;

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
        Assert.assertTrue(steps.isLoggedIn(user.getUserEmail()));
        steps.logout();
    }

    @Test
    public void mailAddToDraftsTest()
    {
        steps.login(user.getUsername(), user.getPassword());
        steps.addMailToDrafts(message);
        Assert.assertTrue(steps.checkDrafts(message));//check that the e-mail is present in "drafts" folder
        steps.logout();
    }

    @Test
    public void mailRemoveFromDraftsTest()
    {
        steps.login(user.getUsername(), user.getPassword());
        steps.addMailToDrafts(message);
        steps.sendMail(message);
        Assert.assertFalse(steps.checkDrafts(message));//check that the e-mail is disappeared from drafts folder
        steps.logout();
    }

    @Test
    public void mailAddToSentFolderTest()
    {
        steps.login(user.getUsername(), user.getPassword());
        steps.addMailToDrafts(message);
        steps.sendMail(message);
        Assert.assertTrue(steps.checkSentFolder(message));//check that the e-mail is present in "sent" folder
        steps.logout();
    }

    @AfterClass
    public void tearDown()
    {
        steps = new Steps();
        steps.closeBrowser();
    }
}



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
public class AddMailToTrashTest {

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
    public void sendMailTest()
    {
        steps.login(user.getUsername(), user.getPassword());
        steps.sendMail(message);
        Assert.assertTrue(steps.checkSentFolder(message));
        steps.logout();
    }

    @Test
    public void addToTrashTest()
    {
        steps.login(user.getUsername(), user.getPassword());
        steps.sendMail(message);
        steps.dragMailToTrash(message);
        Assert.assertTrue(steps.checkTrashFolder(message));
        steps.logout();
    }

    @AfterClass
    public void tearDown()
    {
        steps = new Steps();
        steps.closeBrowser();
    }
}

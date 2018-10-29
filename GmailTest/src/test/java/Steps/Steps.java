package Steps;

import Message.Message;
import Pages.*;
import org.openqa.selenium.WebDriver;
import WebDriver.DriverSingleton;


public class Steps {

    private WebDriver driver;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    public void initBrowser()
    {
        driver = DriverSingleton.getDriver();
    }

    public void login(String username, String password)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public boolean isLoggedIn(String username)
    {
        LoginPage loginPage = new LoginPage(driver);
        String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
        return actualUsername.equals(username);
    }

    public void addMailToDrafts(Message message)
    {
        MessagesPage messagesPage=new MessagesPage(driver);
        messagesPage.fillMailFields(message.getAdressee(),message.getSubject(), message.getBodyText());
        messagesPage.saveMailAsDraft();
    }

    public boolean checkDrafts(Message message)
    {
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.openPage();
        return draftsPage.checkDrafts(message.getAdressee(),message.getSubject(), message.getBodyText());
    }

    public void sendMail(Message message)
    {
        MessagesPage messagesPage=new MessagesPage(driver);
        messagesPage.fillMailFields(message.getAdressee(),message.getSubject(), message.getBodyText());
        messagesPage.sendMail();
    }

    public boolean checkSentFolder(Message message)
    {
        SentPage sentPage= new SentPage(driver);
        sentPage.openPage();
        return sentPage.checkSentFolder(message.getAdressee(), message.getSubject(), message.getBodyText());
    }

    public void logout()
    {
        SentPage sentPage= new SentPage(driver);
        sentPage.logout();
    }

    public void closeBrowser()
    {
        DriverSingleton.closeDriver();
    }

    public void addMailToSpam(Message message)
    {
        SentPage sentPage= new SentPage(driver);
        sentPage.checkSentFolder(message.getAdressee(), message.getSubject(), message.getBodyText());
        sentPage.addToSpam();
    }

    public boolean checkSpamFolder(Message message)
    {
        SpamPage spamPage= new SpamPage(driver);
        spamPage.openPage();
        return spamPage.checkSpamFolder(message.getAdressee(), message.getSubject(), message.getBodyText());
    }

    public void dragMailToTrash(Message message)
    {
        SentPage sentPage= new SentPage(driver);
        sentPage.openPage();
        sentPage.dragMailToTrash(message.getAdressee(), message.getSubject(), message.getBodyText());
    }

    public boolean checkTrashFolder(Message message)
    {
        TrashPage trashPage= new TrashPage(driver);
        trashPage.openPage();
        return trashPage.checkTrashFolder(message.getSenderName(), message.getSubject(), message.getBodyText());
    }
}

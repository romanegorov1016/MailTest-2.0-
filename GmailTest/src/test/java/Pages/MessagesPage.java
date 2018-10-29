package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MessagesPage extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/";
    WebDriverWait wait= new WebDriverWait(driver,30);

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public MessagesPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//div[@class='b-sticky']//a[@data-bem='b-toolbar__btn']")
    private WebElement writeLetterButton;

    @FindBy(xpath = "//textarea[@tabindex='4']")
    private WebElement addressee;

    @FindBy(xpath ="//input[@class='b-input']")
    private WebElement subject;

    @FindBy(tagName="iframe")
    private WebElement bodyFrame;

    @FindBy(xpath ="//*[@id='tinymce']")
    private WebElement body;

    @FindBy(xpath ="//div[@class='b-sticky']//div[@data-name='saveDraft']")
    private WebElement saveDraftButton;

    @FindBy(xpath ="//i[@class='ico ico_folder ico ico_folder_drafts']")
    private WebElement draftsFolderButton;

    @FindBy(xpath ="//div[@class='b-sticky']//div[@data-mnemo='saveStatus']")
    private WebElement seveStatus;

    @FindBy(xpath ="//*[@id='b-toolbar__right']//div[@class='b-toolbar__btn b-toolbar__btn_ b-toolbar__btn_false js-shortcut']")
    private WebElement sendMailButton;

    public void saveMailAsDraft()
    {
        wait.until(ExpectedConditions.elementToBeClickable(saveDraftButton));
        highlightElement(saveDraftButton);
        saveDraftButton.click();
        wait.until(ExpectedConditions.visibilityOf(seveStatus));
    }

    public void sendMail()
    {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        new Actions(driver).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER)).build().perform();
        try {
            Thread.sleep(100);
        } catch (Exception e) {

        }
    }

    public void fillMailFields(String addresseeText, String subjectText, String bodyText)
    {
        highlightElement(writeLetterButton);
        writeLetterButton.click();
        addressee.sendKeys(addresseeText);
        subject.sendKeys(subjectText);
        driver.switchTo().frame(bodyFrame);
        body.clear();
        highlightElement(body);
        body.sendKeys(bodyText);
        driver.switchTo().defaultContent();
    }
}

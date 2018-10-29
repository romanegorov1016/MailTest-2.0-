package Message;

import Utils.Utils;

public class Message {

    private String subject = Utils.getRandomString(10);
    private String bodyText = Utils.getRandomString(30);
    private final String ADRESSEE = "romanegorov1016@gmail.com";
    private final String SENDER_NAME = "Raman Yahoray";

    public String getSubject() {
        return subject;
    }

    public String getBodyText() {
        return bodyText;
    }

    public String getAdressee() {
        return ADRESSEE;
    }

    public String getSenderName() {
        return SENDER_NAME;
    }
}

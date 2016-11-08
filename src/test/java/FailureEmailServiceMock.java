import java.util.Map;

/**
 * Created by eduard on 7/11/16.
 */
public class FailureEmailServiceMock implements EmailService {
    public void sendMailTo(int userId, Map<String, Integer> shoppingBasketContent) throws MailException {
        throw new MailException();
    }
}

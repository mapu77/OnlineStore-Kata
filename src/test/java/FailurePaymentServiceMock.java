/**
 * Created by eduard on 7/11/16.
 */
public class FailurePaymentServiceMock implements PaymentService {
    public void commitPayment(int amount) throws PaymentException {
        throw new PaymentException();
    }
}

/**
 * Created by eduard on 7/11/16.
 */
public interface PaymentService {
    void commitPayment(int amount) throws PaymentException;
}

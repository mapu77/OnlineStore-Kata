import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by eduard on 7/11/16.
 */
public class PaymentTest {

    @Test
    public void checkNoItemsInStockByStatus() {
        ShoppingBasket shoppingBasket = new ShoppingBasketMock();

        Payload payload = new Payload(new NoItemsStockServiceMock());
        payload.submitsPayment(shoppingBasket, new SuccessPaymentServiceMock(), new SuccessEmailServiceMock());

        assertEquals(Payload.States.NoItemsInStock, payload.getState());
    }

    @Test
    public void checkNoStockItemsInformed(){
        ShoppingBasket shoppingBasket = new ShoppingBasketMock();

        Payload payload = new Payload(new NoItemsStockServiceMock());
        payload.submitsPayment(shoppingBasket, new SuccessPaymentServiceMock(), new SuccessEmailServiceMock());

        assertEquals(3, payload.getNoStockItems().size());
        assertTrue(payload.getNoStockItems().contains("Jeans"));
        assertTrue(payload.getNoStockItems().contains("Jacket"));
        assertTrue(payload.getNoStockItems().contains("Socks"));
    }

    @Test
    public void checkPaymentFails() {
        ShoppingBasket shoppingBasket = new ShoppingBasketMock();

        Payload payload = new Payload(new OkStockServiceMock());
        payload.submitsPayment(shoppingBasket, new FailurePaymentServiceMock(), new SuccessEmailServiceMock());

        assertEquals(Payload.States.PaymentFailure, payload.getState());
    }

    @Test
    public void checkPaymentSuccess() {
        ShoppingBasket shoppingBasket = new ShoppingBasketMock();

        Payload payload = new Payload(new OkStockServiceMock());
        payload.submitsPayment(shoppingBasket, new SuccessPaymentServiceMock(), new SuccessEmailServiceMock());

        assertEquals(Payload.States.Ok, payload.getState());
    }

    @Test
    public void checkMailFailure() {
        ShoppingBasket shoppingBasket = new ShoppingBasketMock();
        shoppingBasket.setUserId(1);

        Payload payload = new Payload(new OkStockServiceMock());
        payload.submitsPayment(shoppingBasket, new SuccessPaymentServiceMock(), new FailureEmailServiceMock());

        assertEquals(Payload.States.MailFailure, payload.getState());
    }

}

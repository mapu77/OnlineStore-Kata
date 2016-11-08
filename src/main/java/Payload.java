import sun.security.util.PendingException;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by eduard on 7/11/16.
 */
public class Payload {

    private final StockService stockService;
    private ShoppingBasket shoppingBasket;
    private List<String> noStockItemsList;



    public enum States {Ok, NoItemsInStock, PaymentFailure, MailFailure;};
    private States state;
    public Payload(StockService stockService) {
        this.stockService = stockService;
        this.noStockItemsList = new ArrayList<String>();
    }

    public States getState() {
        return state;
    }

    public List<String> getNoStockItems() {
        return this.noStockItemsList;
    }

    public void setState(States state) {
        this.state = state;
    }

    public void submitsPayment(ShoppingBasket shoppingBasket, PaymentService paymentService, EmailService emailService) {
        this.shoppingBasket = shoppingBasket;
        this.state = States.Ok;

        int total = 0;
        for (Map.Entry<String,Integer> lineItem : shoppingBasket.getContent().entrySet()) {
            if (stockService.hasInStock(lineItem.getKey(), lineItem.getValue())) {
                total += lineItem.getValue();
                try {
                    paymentService.commitPayment(total);
                    emailService.sendMailTo(shoppingBasket.getUserId(), shoppingBasket.getContent());

                } catch (PaymentException e) {
                    this.state = States.PaymentFailure;
                } catch (MailException e) {
                    this.state = States.MailFailure;
                }
            }
            else {
                this.state = States.NoItemsInStock;
                noStockItemsList.add(lineItem.getKey());
            }
        }
    }
}

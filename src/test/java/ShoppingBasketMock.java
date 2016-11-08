import java.util.HashMap;
import java.util.Map;

/**
 * Created by eduard on 7/11/16.
 */
public class ShoppingBasketMock implements ShoppingBasket {

    private int userId;

    public Map<String, Integer> getContent() {
        Map<String, Integer> basket = new HashMap<String, Integer>();
        basket.put("Jacket", 4);
        basket.put("Jeans", 1);
        basket.put("Socks", 2);

        return basket;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

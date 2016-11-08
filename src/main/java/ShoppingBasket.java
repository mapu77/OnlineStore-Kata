import java.util.Map;

/**
 * Created by eduard on 7/11/16.
 */
public interface ShoppingBasket {

    Map<String, Integer> getContent();

    int getUserId();

    void setUserId(int id);


}

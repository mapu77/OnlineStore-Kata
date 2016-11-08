/**
 * Created by eduard on 7/11/16.
 */
public class OkStockServiceMock implements StockService {


    public boolean hasInStock(String product, Integer quantity) {
        return true;
    }
}

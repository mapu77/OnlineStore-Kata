/**
 * Created by eduard on 7/11/16.
 */
public class NoItemsStockServiceMock implements StockService {
    public boolean hasInStock(String product, Integer quantity) {
        return false;
    }
}

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;

public class Application {
    private static final Long IPHONE_ID = 1L;
    private static final Long XIAOMI_ID = 2L;
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        productService.create(new Product("Iphone", 800.50));
        productService.create(new Product("Xiaomi Redmi Note 6", 400.0));
        productService.create(new Product("Nokia 1100", 10.50));
        System.out.println("All products:");
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
        System.out.println("Xiaomi Redmi Note 6 update:");
        Product greenTeaProduct = productService.get(XIAOMI_ID);
        greenTeaProduct.setPrice(350.00);
        productService.update(greenTeaProduct);
        System.out.println(productService.get(XIAOMI_ID));
        System.out.println("Deleting iphone:");
        productService.delete(IPHONE_ID);
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
    }
}

package productsShop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import productsShop.services.ProductService;
import productsShop.services.SeedService;

import java.math.BigDecimal;

@Component
public class ProductsShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;

    public ProductsShopRunner(SeedService seedService, ProductService productService) {
        this.seedService = seedService;
        this.productService = productService;
    }


    @Override
    public void run(String... args) throws Exception {

        this.productService.getProductsInPriceRangeForSell(500, 1000)
                .forEach(productWithoutBuyerDTO -> System.out.println(productWithoutBuyerDTO.getSeller()));
        //this.seedService.seedAll();
    }
}

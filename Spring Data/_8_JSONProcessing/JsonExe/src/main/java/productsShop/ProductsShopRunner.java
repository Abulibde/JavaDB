package productsShop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import productsShop.services.SeedService;
@Component
public class ProductsShopRunner implements CommandLineRunner {

    private final SeedService seedService;

    public ProductsShopRunner(SeedService seedService) {
        this.seedService = seedService;
    }


    @Override
    public void run(String... args) throws Exception {

        //this.seedService.seedAll();
    }
}

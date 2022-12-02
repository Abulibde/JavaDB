package productsShop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import productsShop.entities.categories.CategoryStatsDTO;
import productsShop.services.ProductService;
import productsShop.services.SeedService;

import java.util.List;

@Component
public class ProductsShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;

    private Gson gson;

    public ProductsShopRunner(SeedService seedService, ProductService productService) {
        this.seedService = seedService;
        this.productService = productService;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }


    @Override
    public void run(String... args) throws Exception {

        List<CategoryStatsDTO> categoryStatistics = this.productService.getCategoryStatistics();

        String json = this.gson.toJson(categoryStatistics);

        System.out.println(json);


        //        this.productService.getProductsInPriceRangeForSell(500, 1000)
//                .forEach(productWithoutBuyerDTO -> System.out.println(productWithoutBuyerDTO.getSeller()));


//        this.seedService.seedAll();
    }
}

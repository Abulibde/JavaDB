package productsShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productsShop.entities.products.Product;
import productsShop.entities.products.ProductWithoutBuyerDTO;
import productsShop.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(float from, float to) {

        BigDecimal rangeStart = BigDecimal.valueOf(from);
        BigDecimal rangeEnd = BigDecimal.valueOf(to);

        List<ProductWithoutBuyerDTO> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(rangeStart, rangeEnd);

        return products;
    }
}

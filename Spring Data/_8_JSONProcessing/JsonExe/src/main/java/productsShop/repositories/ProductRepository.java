package productsShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productsShop.entities.products.Product;
import productsShop.entities.products.ProductWithoutBuyerDTO;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<ProductWithoutBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal rangeStart, BigDecimal rangeEnd);
}

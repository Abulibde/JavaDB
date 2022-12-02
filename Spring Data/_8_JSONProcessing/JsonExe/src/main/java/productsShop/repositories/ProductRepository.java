package productsShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productsShop.entities.categories.CategoryStatsDTO;
import productsShop.entities.products.Product;
import productsShop.entities.products.ProductWithoutBuyerDTO;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<ProductWithoutBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal rangeStart, BigDecimal rangeEnd);


    @Query("SELECT new productsShop.entities.categories.CategoryStatsDTO(" +
            "c.name, COUNT(p), AVG(p.price), SUM(p.price))" +
            " FROM Product p" +
            " JOIN p.categories AS c" +
            " GROUP BY c")
    List<CategoryStatsDTO> getCategoryStats();
}

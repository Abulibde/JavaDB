package productsShop.entities.categories;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CategoryStatsDTO {

    private String category;
    private long count;
    private double avgPrice;
    private BigDecimal totalRevenue;

    public CategoryStatsDTO(String category, long count, double avgPrice, BigDecimal totalRevenue) {
        this.category = category;
        this.count = count;
        this.avgPrice = avgPrice;
        this.totalRevenue = totalRevenue;
    }
}

package productsShop.entities.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import javax.swing.plaf.basic.BasicIconFactory;
import java.math.BigDecimal;

public interface ProductWithoutBuyerDTO {

    String getName();
    BigDecimal getPrice();

    @Value("#{target.seller.firstName + ' ' + target.seller.lastName}")
    String getSeller();

}

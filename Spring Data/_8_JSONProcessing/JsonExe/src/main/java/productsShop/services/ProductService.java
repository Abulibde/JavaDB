package productsShop.services;

import productsShop.entities.products.ProductWithoutBuyerDTO;

import java.util.List;

public interface ProductService {

    List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(float from, float to);

}

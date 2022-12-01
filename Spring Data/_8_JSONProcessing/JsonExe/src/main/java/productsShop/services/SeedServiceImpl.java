package productsShop.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import productsShop.entities.products.Product;
import productsShop.entities.categories.Category;
import productsShop.entities.categories.CategoryImportDTO;
import productsShop.entities.products.ProductImportDTO;
import productsShop.entities.users.User;
import productsShop.entities.users.UserImportDTO;
import productsShop.repositories.CategoryRepository;
import productsShop.repositories.ProductRepository;
import productsShop.repositories.UserRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SeedServiceImpl implements SeedService {

    private static final String USERS_JSON_PATH = "src/main/resources/productsShop/users.json";
    private static final String CATEGORIES_JSON_PATH = "src/main/resources/productsShop/categories.json";
    private static final String PRODUCTS_JSON_PATH = "src/main/resources/productsShop/products.json";
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ProductRepository productsRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           ProductRepository productsRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productsRepository = productsRepository;

        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {

        FileReader fileReader = new FileReader(USERS_JSON_PATH);

        UserImportDTO[] userImportDTOS = this.gson.fromJson(fileReader, UserImportDTO[].class);

        List<User> users = Arrays.stream(userImportDTOS)
                .map(importDTO -> this.modelMapper.map(importDTO, User.class))
                .collect(Collectors.toList());

        this.userRepository.saveAll(users);

    }

    @Override
    public void seedCategories() throws FileNotFoundException {

        FileReader fileReader = new FileReader(CATEGORIES_JSON_PATH);
        CategoryImportDTO[] categoriesImportDTOS = this.gson.fromJson(fileReader, CategoryImportDTO[].class);

        List<Category> categories = Arrays.stream(categoriesImportDTOS)
                .map(importDTO -> this.modelMapper.map(importDTO, Category.class))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(categories);

    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(PRODUCTS_JSON_PATH);
        ProductImportDTO[] productImportDTOS = this.gson.fromJson(fileReader, ProductImportDTO[].class);

        List<Product> products = Arrays.stream(productImportDTOS)
                .map(importDTO -> this.modelMapper.map(importDTO, Product.class))
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategories)
                .toList();

        this.productsRepository.saveAll(products);

    }

    private Product setRandomCategories(Product product) {

        Random random = new Random();
        long categoriesCount = this.categoryRepository.count();

        int count = random.nextInt((int) categoriesCount);

        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomId = random.nextInt((int) categoriesCount) + 1;

            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);
            categories.add(randomCategory.get());
        }
        product.setCategories(categories);

        return product;
    }

    private Product setRandomBuyer(Product product) {

        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0) {
            return product;
        }

        Optional<User> buyer = getRandomUser();

        product.setBuyer(buyer.get());

        return product;
    }

    private Product setRandomSeller(Product product) {
        Optional<User> seller = getRandomUser();

        product.setSeller(seller.get());

        return product;

    }

    private Optional<User> getRandomUser() {
        long usersCount = userRepository.count();//1...5
        //0...4
        int randomUserId = new Random().nextInt((int) usersCount) + 1;

        return this.userRepository.findById(randomUserId);
    }
}

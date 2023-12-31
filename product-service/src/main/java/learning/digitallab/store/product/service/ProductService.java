package learning.digitallab.store.product.service;

import learning.digitallab.store.product.entity.Category;
import learning.digitallab.store.product.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();
    Product getProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);
    List<Product> findByCategory(Category category);
    Product updateStock(Long id, Double quantity);

}

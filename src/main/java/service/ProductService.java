package service;

import dao.ProductDao;
import entity.Product;

import java.util.List;

public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll(){
        return productDao.findAll();
    }

    public void add(Product productFromRequest) {
        productDao.save(productFromRequest);
    }
}

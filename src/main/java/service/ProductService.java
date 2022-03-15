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

    public void add(Product product) {
        productDao.save(product);
    }

    public void edit(Product product) {
        productDao.update(product);
    }

    public Product getById(int id) {
        return productDao.findById(id);
    }

    public void delete(int id) {
        productDao.deleteById(id);
    }
}

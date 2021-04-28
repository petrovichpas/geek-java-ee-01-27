//package ru.geekbrains.service;
//
//import ru.geekbrains.persist.Category;
//import ru.geekbrains.persist.CategoryRepository;
//import ru.geekbrains.persist.Product;
//import ru.geekbrains.persist.ProductRepository;
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Stateless
//public class ProductServiceImpl implements ProductService {
//
//    @EJB
//    private ProductRepository productRepository;
//
//    @EJB
//    private CategoryRepository categoryRepository;
//
//    @Override
//    public List<ProductRepr> findAll() {
//        return productRepository.findAll().stream().map(ProductRepr::new).collect(Collectors.toList());
//    }
//
//    @Override
//    public ProductRepr findById(Long id) {
//        Product product = productRepository.findById(id);
//        if (product != null) {
//            return new ProductRepr(product);
//        }
//        return null;
//    }
//
//    @TransactionAttribute
//    @Override
//    public void saveOrUpdate(ProductRepr product) {
//        Category category = null;
//        if (product.getCategoryId() != null) {
//            category = categoryRepository.getReference(product.getCategoryId());
//        }
//        productRepository.saveOrUpdate(new Product(product, category));
//    }
//
//    @TransactionAttribute
//    @Override
//    public void deleteById(Long id) {
//        productRepository.deleteById(id);
//    }
//
//    @Override
//    public Long countAll() {
//        return productRepository.countAll();
//    }
//}

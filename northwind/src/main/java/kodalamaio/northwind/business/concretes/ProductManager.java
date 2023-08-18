package kodalamaio.northwind.business.concretes;

import kodalamaio.northwind.business.abstracts.ProductService;
import kodalamaio.northwind.core.utilities.results.*;
import kodalamaio.northwind.dataAccess.abstracts.ProductDao;
import kodalamaio.northwind.entities.concretes.Product;
import kodalamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        super();
        this.productDao = productDao;
    }
    @Override
    public DataResult<List<Product>> getAll() {
        List<Product> products = this.productDao.findAll();
        if(products.isEmpty()){
            return new ErrorDataResult<List<Product>>(null, "There are no products on database.");
        }else{
            return new SuccessDataResult<List<Product>>(products, "Products are listed.");
        }

    }
    @Override
    public DataResult<List<Product>> getAllSortedAscending() {
        List<Product> products = this.productDao.findAll();
        if(products.isEmpty()){
            return new ErrorDataResult<List<Product>>(null, "There are no products on database to be sorted.");
        }else{
            Sort sort = Sort.by(Sort.Direction.ASC, "productName");
            return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort), "Products are listed by their names.");
        }


    }

    @Override
    public DataResult<List<Product>> getAllSortedDescending() {
        List<Product> products = this.productDao.findAll();
        if(products.isEmpty()){
            return new ErrorDataResult<List<Product>>(null, "There are no products on database to be sorted.");
        }else{
            Sort sort = Sort.by(Sort.Direction.DESC, "productName");
            return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort), "Products are listed by their names.");
        }
    }

    @Override
    public DataResult<List<Product>> getPage(int pageNo, int pageSize) {
        List<Product> products = this.productDao.findAll();
        if(products.isEmpty()){
            return new ErrorDataResult<List<Product>>(null, "There are no products on database to fetch.");
        }else{
            Pageable pageable = PageRequest.of(pageNo-1, pageSize);
            return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent(), "Products are listed.");
        }
    }

    @Override
    public DataResult<Product> getByProductId(int productId) {
        Optional<Product> product = this.productDao.findById(productId);
        if(product.isPresent()){
            return new SuccessDataResult<Product>(product.get(), "Product is fetched.");
        }
        else{
            return new ErrorDataResult<Product>(null, "Product is not found on database.");
        }
    }

    @Override
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessResult("Product is added.");
    }

    @Override
    public Result delete(int productId) {
        Optional<Product> product = this.productDao.findById(productId);
        if(product.isPresent()){
            this.productDao.deleteById(productId);
            return new SuccessResult("Product is deleted.");
        }
        else{
            return new ErrorResult("Product is not found on database to be deleted.");
        }

    }

    @Override
    public DataResult<Product> update(int productId, Product product) {
        Optional<Product> oldProduct = this.productDao.findById(productId);
        if(oldProduct.isPresent()){
            oldProduct.get().setProductName(product.getProductName());
            oldProduct.get().setUnitPrice(product.getUnitPrice());
            oldProduct.get().setUnitsInStock(product.getUnitsInStock());
            oldProduct.get().setQuantityPerUnit(product.getQuantityPerUnit());
            oldProduct.get().setCategory(product.getCategory());
            this.productDao.save(oldProduct.get());
            return new SuccessDataResult<Product>(oldProduct.get(), "Product is updated.");
        }
        else{
            return new ErrorDataResult<Product>(null, "Product is not found on database to update.");
        }
    }

    @Override
    public DataResult<Product> getByProductName(String productName) {
        Product product = this.productDao.getByProductName(productName);
        if (product == null) {
            return new ErrorDataResult<Product>(null, "Product is not found on database.");
        }
        else{
            return new SuccessDataResult<Product>(product, "Product is listed.");
        }

    }

    @Override
    public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
        Product product = this.productDao.getByProductNameAndCategory_CategoryId(productName, categoryId);
        if(product == null){
            return new ErrorDataResult<Product>(null, "Product is not found on database.");
        }
        else{
            return new SuccessDataResult<Product>(product, "Product is listed.");
        }
    }

    @Override
    public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
        List<Product> products = this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId);
        if(products.isEmpty()){
            return new ErrorDataResult<List<Product>>(null, "Products are not found on database.");
        }else{
            return new SuccessDataResult<List<Product>>(products, "Products are listed.");
        }

    }

    @Override
    public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
        List<Product> products =this.productDao.getByCategory_CategoryIdIn(categories);
        if(products.isEmpty()){
            return new ErrorDataResult<List<Product>>(null, "Products are not found on database.");
        }else{
            return new SuccessDataResult<List<Product>>(products, "Products are listed.");
        }
    }

    @Override
    public DataResult<List<Product>> getByProductNameContains(String productName) {
        List<Product> products = this.productDao.getByProductNameContains(productName);
        if(products.isEmpty()){
            return new ErrorDataResult<List<Product>>(null, "Products are not found on database.");
        }else{
            return new SuccessDataResult<List<Product>>(products, "Products are listed.");
        }
    }

    @Override
    public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
        List<Product> products = this.productDao.getByProductNameStartsWith(productName);
        if(products.isEmpty()){
            return new ErrorDataResult<List<Product>>(null, "Products are not found on database.");
        }else{
            return new SuccessDataResult<List<Product>>(products, "Products are listed.");
        }
    }

    @Override
    public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
        List<Product> products = this.productDao.getByNameAndCategory(productName, categoryId);
        if(products.isEmpty()){
            return new ErrorDataResult<List<Product>>(null, "Products are not found on database.");
        }else{
            return new SuccessDataResult<List<Product>>(products, "Products are listed.");
        }
    }

    @Override
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
        List<ProductWithCategoryDto> productsWithCategories = this.productDao.getProductWithCategoryDetails();
        if(productsWithCategories.isEmpty()){
            return new ErrorDataResult<List<ProductWithCategoryDto>>(null, "Products are not found on database.");
        }else{
            return new SuccessDataResult<List<ProductWithCategoryDto>>(productsWithCategories, "Products are listed.");
        }

    }
}

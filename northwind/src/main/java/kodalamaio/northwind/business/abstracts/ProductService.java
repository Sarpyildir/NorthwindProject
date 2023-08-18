package kodalamaio.northwind.business.abstracts;

import kodalamaio.northwind.core.utilities.results.DataResult;
import kodalamaio.northwind.core.utilities.results.Result;
import kodalamaio.northwind.entities.concretes.Product;
import kodalamaio.northwind.entities.dtos.ProductWithCategoryDto;

import java.util.List;

public interface ProductService {
    DataResult<List<Product>> getAll();
    DataResult<List<Product>> getAllSortedAscending();
    DataResult<List<Product>> getAllSortedDescending();
    DataResult<List<Product>> getPage(int pageNo, int pageSize);
    DataResult<Product> getByProductId(int productId);
    Result add(Product product);
    Result delete(int productId);
    DataResult<Product> update(int productId, Product product);
    DataResult<Product> getByProductName(String productName);
    DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId);
    DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId);
    DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories);
    DataResult<List<Product>> getByProductNameContains(String productName);
    DataResult<List<Product>> getByProductNameStartsWith(String productName);
    DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId);
    DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails();

}

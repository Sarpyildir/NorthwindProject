package kodalamaio.northwind.dataAccess.abstracts;

import kodalamaio.northwind.entities.concretes.Product;
import kodalamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    Product getByProductName(String productName);

    Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);

    List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);

    List<Product> getByCategory_CategoryIdIn(List<Integer> category);

    List<Product> getByProductNameContains(String productName);

    List<Product> getByProductNameStartsWith(String productName);
/*
    @Query("from Product where productName=:productName and category.categoryId=:category_id")
    List<Product> getByNameAndCategory(String productName, int categoryId);*/
    @Query("SELECT p FROM Product p WHERE p.productName = ?1 AND p.category.categoryId = ?2")
    List<Product> getByNameAndCategory(String productName, int categoryId);

    @Query("SELECT new kodalamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) FROM Category c INNER JOIN c.products p")
    List<ProductWithCategoryDto> getProductWithCategoryDetails();

}

package kodalamaio.northwind.api.controllers;

import kodalamaio.northwind.business.abstracts.ProductService;
import kodalamaio.northwind.core.utilities.results.DataResult;
import kodalamaio.northwind.core.utilities.results.Result;
import kodalamaio.northwind.entities.concretes.Product;
import kodalamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Product>> getAll(){
        return this.productService.getAll();
    }

    @GetMapping("/getAllSortedAscending")
    public DataResult<List<Product>> getAllSortedAscending(){
        return this.productService.getAllSortedAscending();
    }
    @GetMapping("/getAllSortedDescending")
    public DataResult<List<Product>> getAllSortedDescending(){
        return this.productService.getAllSortedDescending();
    }

    @GetMapping("/getPage")
    public DataResult<List<Product>> getPage(@RequestParam int pageNo, @RequestParam int pageSize){
        return this.productService.getPage(pageNo, pageSize);
    }

    @GetMapping("/get/{productId}")
    public DataResult<Product> getByProductId(@PathVariable("productId") int productId){ return this.productService.getByProductId(productId);}

    @GetMapping("/getByProductName")
    public DataResult<Product> getByProductName(@RequestParam String productName){ return this.productService.getByProductName(productName);}

    @GetMapping("/getByNameAndCategory")
    public DataResult<List<Product>> getByNameAndCategory(@RequestParam String productName, int categoryId){
        return this.productService.getByNameAndCategory(productName, categoryId);
    }
    @GetMapping("/getByProductNameAndCategoryId")
    public DataResult<Product> getByProductNameAndCategoryId(@RequestParam String productName, @RequestParam int categoryId){
        return this.productService.getByProductNameAndCategoryId(productName, categoryId);
    }
    @GetMapping("/getByProductNameContains")
    public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName){
        return this.productService.getByProductNameContains(productName);
    }
    @GetMapping("/getByProductNameOrCategoryId")
    public DataResult<List<Product>> getByProductNameOrCategoryId(@RequestParam String productName, @RequestParam int categoryId){
        return this.productService.getByProductNameOrCategoryId(productName, categoryId);
    }
    @GetMapping("/getByCategoryIdIn")
    public DataResult<List<Product>> getByCategoryIdIn(@RequestParam List<Integer> categories){
        return this.productService.getByCategoryIdIn(categories);
    }
    @GetMapping("/getByProductNameStartsWith")
    public DataResult<List<Product>> getByProductNameStartsWith(@RequestParam String productName){
        return this.productService.getByProductNameStartsWith(productName);
    }
    @GetMapping("/getProductWithCategoryDetails")
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails(){
        return this.productService.getProductWithCategoryDetails();
    }
    @PostMapping("/add")
    public Result add(@RequestBody Product product){
        return this.productService.add(product);
    }

    @PutMapping("/update/{productId}")
    public DataResult<Product> update(@PathVariable("productId") int productId, @RequestBody Product product){
        return this.productService.update(productId, product);
    }

    @DeleteMapping("/delete/{productId}")
    public Result delete(@PathVariable("productId") int productId){
        return this.productService.delete(productId);
    }
}

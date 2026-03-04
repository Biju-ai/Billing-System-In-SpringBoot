package inventroy.Billing.system.Billing.System.services.impl;

import inventroy.Billing.system.Billing.System.dto.request.Products.DeleteProductById;
import inventroy.Billing.system.Billing.System.dto.request.Products.ModifyProductById;
import inventroy.Billing.system.Billing.System.dto.request.Products.ProductRequest;
import inventroy.Billing.system.Billing.System.dto.Responce.products.FindByName;
import inventroy.Billing.system.Billing.System.dto.Responce.products.FindByNameSize;
import inventroy.Billing.system.Billing.System.dto.Responce.products.RequestQuery;
import inventroy.Billing.system.Billing.System.entity.Users;
import inventroy.Billing.system.Billing.System.entity.products.Products;
import inventroy.Billing.system.Billing.System.repository.ProductRepo;
import inventroy.Billing.system.Billing.System.repository.UserRepo;
import inventroy.Billing.system.Billing.System.util.RequestApi;
import inventroy.Billing.system.Billing.System.util.ResponceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    public ProductService(ProductRepo productRepo, UserRepo userRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    public RequestApi<?> insertProduct(ProductRequest productRequest) {
        Products product = new Products();
        product.setProductname(productRequest.getProductname());
        product.setProductprice(productRequest.getProductprice());
        product.setProductcategory(productRequest.getProductcategory());
        product.setProductquantity(productRequest.getProductquantity());
        product.setExpirydate(productRequest.getExpirydate());
        product.setStocklevels(productRequest.getStocklevels());
        product.setSize(productRequest.getSize());
        product.setExpiryalertdays(productRequest.getExpiryalertdays());
        product.setLowstockthreshold(productRequest.getLowstockthreshold());
        product.setDeleted(false);
        productRepo.save(product);
        return ResponceApi.success(1, "Product successfully inserted");
    }

    public RequestApi<?> showAllTheProducts() {
        List<Products> products = productRepo.findAllByDeletedFalse();
        return ResponceApi.success(1, "the listed products are", products);
    }

    public RequestApi<?> deleteById(DeleteProductById deleteProductById) {

        Optional<Products> product = productRepo.findById(deleteProductById.getProductid());
        if (!product.isPresent()) {
            return ResponceApi.error(0, "Product not found");
        } else {
            Products products = product.get();
            products.setDeleted(true);
            productRepo.save(products);
            return ResponceApi.success(1, "Product successfully deleted");
        }
    }

    public RequestApi<?> modifyProduct(ModifyProductById modifyProductById) {
        Long id = modifyProductById.getProductid();
        if (id == null) {
            return ResponceApi.error(0, "Product id not found");
        }
        Optional<Products> optional = productRepo.findById(id);
        if (optional.isPresent()) {
            Products product = optional.get();

            //  to modify the product

            product.setProductname(modifyProductById.getProductname());
            product.setProductcategory(modifyProductById.getProductcategory());
            product.setProductprice(modifyProductById.getProductprice());
            product.setStocklevels(modifyProductById.getStocklevels());
            product.setExpirydate(modifyProductById.getExpirydate());
            product.setProductquantity(modifyProductById.getProductquantity());
            productRepo.save(product);
            return ResponceApi.success(1, "Product successfully modified");
        }
        return ResponceApi.error(0, "Product not found");
    }

    public RequestApi<?> findByName(FindByName findByName) {
        String productname = findByName.getProductname();

        List<Products> products = productRepo.findByProductnameAndDeletedFalse(productname);
        if (products == null) {
            return ResponceApi.error(0, "Product not found");
        }
        return ResponceApi.success(1, "Product successfully found", products);
    }

    public RequestApi<?> findBYNameSize(FindByNameSize findByNameSize, Principal principal) {
        Users user = userRepo.findByEmail(principal.getName());
        if (user == null) {
            return ResponceApi.error(0, "User not found");
        }

        String Productname = findByNameSize.getProductname();
        Character size = findByNameSize.getSize();
        List<Products> products = productRepo.findByProductnameAndSizeAndDeletedFalse(Productname, size);
        if (products == null) {
            return ResponceApi.error(0, "Product not found");
        }
        return ResponceApi.success(1, "Product successfully found", products);
    }

    public RequestApi<?> findByQuery(RequestQuery requestQuery, Principal principal) {
        Users user = userRepo.findByEmail(principal.getName());
        if (user == null) {
            return ResponceApi.error(0, "User not found");
        }
        List<Products> products = productRepo.findByProductnameContainingIgnoreCaseAndDeletedFalse(requestQuery.getQuery());
        return ResponceApi.success(1, "Product successfully found", products);
    }

    public RequestApi<?> checkExpiringDate(Principal principal) {
        Users user = userRepo.findByEmail(principal.getName());
        if (user == null) {
            return ResponceApi.error(0, "User not found");
        }
        List<Products> products = productRepo.findAll();
        List<Products> expireSoonProduct = new ArrayList<>();
        LocalDate today = LocalDate.now();


        for (Products product : products) {
            if (product.getExpirydate() != null && product.getExpiryalertdays() > 0) {
                LocalDate expiredDate = product.getExpirydate();
                LocalDate alertDate = today;
                LocalDate expiryDate = expiredDate.plusDays(product.getExpiryalertdays());
                if (!expiryDate.isBefore(alertDate) && !expiryDate.isAfter(alertDate)) ;
                expireSoonProduct.add(product);
            }
        }
        if (expireSoonProduct.isEmpty()) {
            return ResponceApi.error(0, "Product not found");
        }
        return ResponceApi.success(1, "Product successfully expired", expireSoonProduct);
    }

    public RequestApi<?> lowQuantityProduct(Principal principal) {
        Users user = userRepo.findByEmail(principal.getName());
        if (user == null) {
            return ResponceApi.error(0, "User not found");
        }
        List<Products> products = productRepo.findAll();
        List<Products> lowQuantity = new ArrayList<>();
        for (Products product : products) {
            if (product.getProductquantity() < product.getLowstockthreshold()) {
                lowQuantity.add(product);
            }
        }
        if (lowQuantity.isEmpty()) {
            return ResponceApi.error(0, "Product not found");
        }
        return ResponceApi.success(1, "Product successfully fount the low quantity", lowQuantity);
    }
    public void saveAll(List<Products> products) {
        productRepo.saveAll(products);
    }
}


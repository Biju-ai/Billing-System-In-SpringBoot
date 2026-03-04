package inventroy.Billing.system.Billing.System.repository;

import inventroy.Billing.system.Billing.System.entity.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Products,Long> {
    Optional<Products> findById(Long id);


//    Optional<Products> findByproductname(String productname);
//    List<Products> findByProductnameAndDeletedFalse(String productname);

    Optional<Products> findByproductnameAndDeletedFalse(String productname);


//    List<Products> findByProductnameAndSizeAndDeletedFalse(String productname, Character size);
    List<Products> findByProductnameAndSizeAndDeletedFalse(String productname, Character size);


    List<Products> findByProductnameAndDeletedFalse(String productname);


    List<Products> findByProductnameContainingIgnoreCaseAndDeletedFalse(String query);



    List<Products> findAllByDeletedFalse();

}

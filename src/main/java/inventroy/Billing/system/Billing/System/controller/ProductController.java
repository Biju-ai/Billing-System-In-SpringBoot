package inventroy.Billing.system.Billing.System.controller;

import inventroy.Billing.system.Billing.System.dto.request.Products.*;
import inventroy.Billing.system.Billing.System.dto.Responce.products.FindByName;
import inventroy.Billing.system.Billing.System.dto.Responce.products.FindByNameSize;
import inventroy.Billing.system.Billing.System.dto.Responce.products.RequestQuery;
import inventroy.Billing.system.Billing.System.services.impl.CsvService;
import inventroy.Billing.system.Billing.System.services.impl.ProductService;
import inventroy.Billing.system.Billing.System.util.RequestApi;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/billing/product")
public class ProductController {
    private final ProductService productService;
    private final CsvService csvService;


    public ProductController(ProductService productService, CsvService csvService) {
        this.productService = productService;
        this.csvService = csvService;
    }

    @PostMapping("/insertProduct")
    public RequestApi<?> insert(@RequestBody ProductRequest productRequest){
        return productService.insertProduct(productRequest);
    }

    @PostMapping("/findall")
    public RequestApi<?> findAll(){
        return productService.showAllTheProducts();
    }


    @PostMapping("/deleteById")
    public RequestApi<?> deleteById(@RequestBody DeleteProductById deleteProductById){
        return productService.deleteById(deleteProductById);
    }


    @PostMapping("/modifyProduct")
    public RequestApi<?> modifyProduct(@RequestBody ModifyProductById modifyProductById){
        return productService.modifyProduct(modifyProductById);
    }


    @PostMapping("/findbyname")
    public RequestApi<?> findbyname(@RequestBody FindByName findByName){
        return productService.findByName(findByName);
    }

    @PostMapping("/findbynamesize")
    public RequestApi<?> findbynamesize(@RequestBody FindByNameSize findByNameSize,Principal principal){
        return productService.findBYNameSize(findByNameSize, principal);
    }

    @PostMapping("/query")
    public RequestApi<?> query(@RequestBody RequestQuery requestquery, Principal principal){
        return productService.findByQuery(requestquery,principal);
    }

    @PostMapping("/expirealert")
    public RequestApi<?>expirealert( Principal principal){
        return productService.checkExpiringDate(principal);
    }

    @PostMapping("/lowquantity")
    public RequestApi<?> lowquantity(Principal principal){
        return productService.lowQuantityProduct(principal);
    }

    @PostMapping("/csvFile")
    public RequestApi<?> csvFile(@ModelAttribute FileRequest fileRequest) throws IOException {
        return csvService.saveCSVData(fileRequest);
    }

}

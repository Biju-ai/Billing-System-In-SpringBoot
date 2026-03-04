package inventroy.Billing.system.Billing.System.services.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import inventroy.Billing.system.Billing.System.dto.request.Products.FileRequest;
import inventroy.Billing.system.Billing.System.entity.products.Products;
import inventroy.Billing.system.Billing.System.repository.ProductRepo;
import inventroy.Billing.system.Billing.System.util.RequestApi;
import inventroy.Billing.system.Billing.System.util.ResponceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CsvService {

    private final ProductService productService;
    private final ProductRepo productRepo;

    public CsvService(ProductService productService, ProductRepo productRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
    }

    public RequestApi<?> saveCSVData(FileRequest  fileRequest) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(fileRequest.getCsvFile().getInputStream()))) {

            CSVReader csvReader = new CSVReader(reader);
            List<String[]> rows = csvReader.readAll();
            List<Products> products = new ArrayList<>();

            for (int i = 1; i < rows.size(); i++) {
                String[] data = rows.get(i);
                Products p = new Products();
//                p.setProductid(Long.parseLong(data[0]));
                p.setProductname(data[1]);
                p.setProductprice(Integer.parseInt(data[2]));
                p.setProductquantity(Integer.parseInt(data[3]));
                p.setProductcategory(data[4]);
                p.setStocklevels(Integer.parseInt(data[5]));

                // adjust if needed
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                p.setExpirydate(LocalDate.parse(data[6], formatter));

                // Convert size from string to Character
                p.setSize(data[7].charAt(0));
                p.setLowstockthreshold(Integer.parseInt(data[8]));
                p.setExpiryalertdays(Integer.parseInt(data[9]));
                p.setDeleted(false);
                products.add(p);

                log.info("problem problem");
            }
               productRepo.saveAll(products);
            log.info("save all products");
            return ResponceApi.success(1, "CSV has been saved successfully");
        } catch (CsvException e) {
            return  ResponceApi.error(1, "CSV has been saved successfully");
        } catch (Exception e) {
            return  ResponceApi.error(1, "Unexpected error occured");

        }
    }

}

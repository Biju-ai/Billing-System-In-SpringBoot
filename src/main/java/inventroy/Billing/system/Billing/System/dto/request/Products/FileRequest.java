package inventroy.Billing.system.Billing.System.dto.request.Products;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileRequest {
    private MultipartFile csvFile;

}

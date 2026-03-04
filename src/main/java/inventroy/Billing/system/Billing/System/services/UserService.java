package inventroy.Billing.system.Billing.System.services;

import inventroy.Billing.system.Billing.System.dto.request.Products.DeleteProductById;
import inventroy.Billing.system.Billing.System.dto.request.users.UserRequest;
import inventroy.Billing.system.Billing.System.util.RequestApi;

public interface UserService {
     RequestApi<?> inserUser(UserRequest userRequest);
    RequestApi<?> deleteById(DeleteProductById deleteProductById);
}

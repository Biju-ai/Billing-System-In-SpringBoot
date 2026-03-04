package inventroy.Billing.system.Billing.System.util;

import org.springframework.http.HttpStatus;

public class ResponceApi {
    public static RequestApi error(int code, String message){
        return RequestApi.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(code)
                .message(message)
                .build();
    }

    public static RequestApi error(int code, String message,Object data){
        return RequestApi.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
    public static RequestApi success(int code,String message){
        return RequestApi.builder()
                .status(HttpStatus.OK)
                .code(code)
                .message(message)
                .build();

    }
    public static RequestApi success(int code,String message,Object data){
        return RequestApi.builder()
                .status(HttpStatus.OK)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}

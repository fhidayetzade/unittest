package springdata.technest22.exception;
public class ProductNotFoundException extends NotFoundException {


    public ProductNotFoundException(String code, String message, String details) {
        super(code,message,details);

    }
}

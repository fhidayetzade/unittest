package springdata.technest22.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException{

    private String code;
    private String message;
    private String details;

    public NotFoundException(String code, String message, String details) {
        super(details);
        this.code=code;
        this.message=message;
        this.details=details;
    }
}

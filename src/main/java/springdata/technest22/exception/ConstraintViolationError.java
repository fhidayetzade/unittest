package springdata.technest22.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConstraintViolationError {

    private String property;
    private String message;
}

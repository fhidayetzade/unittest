package springdata.technest22.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice // request gelir tomcatin icine, tomcatda thread`lar var ve hemin
//thread`ler filterlerden kecir, gelir dushur Dispatcher Servelete, DS gelir controller`e
//controller`de gonderir servise ve servis de gonderir db-e. eyni yolla geri qayidir.
public class ProductExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> errorResponseDto (
            ProductNotFoundException ex, HttpServletRequest request
    ){
          ErrorResponseDto responseDto = ErrorResponseDto.builder()
                .status(404)
                .code(ex.getCode())
                .message(ex.getMessage())
                .details(ex.getDetails())
                .path(request.getServletPath())
                .traceId(UUID.randomUUID().toString())
                .build();

          return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handle(
            MethodArgumentNotValidException ex, HttpServletRequest request
    ){
        List<ConstraintViolationError> violationErrors  = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ConstraintViolationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        ErrorResponseDto responseDto = ErrorResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code("Argument excp")
                .message(ex.getMessage())
                .details("excpetion")
                .path(request.getServletPath())
                .build();
                violationErrors.forEach(
                        validation -> responseDto.getData().put(validation.getProperty(), validation.getProperty())
                );
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);


    }


}

package springdata.technest22.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class ProductRequestDto {

    @NotBlank
    @Size(min = 3, max = 50, message = "min 3, max 50")
    private String name;
    @Min(18)
    @Max(50)
    @NotNull
    private String age;


}

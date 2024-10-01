package springdata.technest22.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ErrorResponseDto {

    private int status;
    private String code;
    private String message;
    private String details;
    private OffsetDateTime timestamp;
    private String path;
    @JsonProperty("request_lang")
    private String requestLang;
    @JsonProperty("trace_id")
    private String traceId;
    @Builder.Default
    private Map<String, Object> data = new HashMap<>();

}

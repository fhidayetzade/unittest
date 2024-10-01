package springdata.technest22.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessResponse {
    private String status;
    private ResponseData data;

    @Data
    @Builder
    public static class ResponseData {
        private int code;
        private String message;
    }
}

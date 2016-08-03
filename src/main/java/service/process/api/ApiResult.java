package service.process.api;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName="of")
public class ApiResult {

    @NonNull private Integer code;
    @NonNull private String message;

}

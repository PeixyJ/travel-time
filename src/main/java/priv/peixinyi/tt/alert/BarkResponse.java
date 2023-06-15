package priv.peixinyi.tt.alert;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author peixinyi
 */
@NoArgsConstructor
@Data
public class BarkResponse {


    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("timestamp")
    private Integer timestamp;
}

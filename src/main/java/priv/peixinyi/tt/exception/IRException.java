package priv.peixinyi.tt.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import priv.peixinyi.tt.pojo.Result;

/**
 * 异常处理
 *
 * @author peixinyi
 */
@Getter
@NoArgsConstructor
public class IRException extends RuntimeException {

    private Result<String> result;

    public IRException(String message) {
        super(message);
        this.result = Result.error(message);
    }

    public IRException(Result<String> result) {
        super(result.getMessage());
        this.result = result;
    }

}

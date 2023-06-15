package priv.peixinyi.tt.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import priv.peixinyi.tt.pojo.Result;

/**
 * 全局异常处理
 *
 * @author peixinyi
 */
@Slf4j
@Order(-1)
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        log.error("Exception:{}", e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(IRException.class)
    public Result<String> iExceptionHandler(IRException e) {
        log.error("IRException:{}", e.getMessage());
        return e.getResult();
    }
}

package priv.peixinyi.tt.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author peixinyi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {


    public static final long serialVersionUID = 4328741;

    /**
     * 状态码
     */
    public static final Integer SUCCESS_CODE = 1;

    /**
     * 状态码
     */
    public static final Integer FAIL_CODE = -1;


    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    /**
     * 状态
     */
    private Boolean flag;

    /**
     * 消息ID
     */
    private String messageId;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.date = new Date();
        this.flag = code >= 0;
    }

    /**
     * 返回成功结果
     *
     * @return com.hengyun.ms.common.vo.Result<java.lang.String>
     * @author PeiXy_J
     * @since 09:25 2022-4-29
     */
    public static Result<String> ok() {
        return new Result<>(0, "success", "");
    }

    /**
     * 返回成功结果
     *
     * @return com.hengyun.ms.common.vo.Result<java.lang.String>
     * @author PeiXy_J
     * @since 09:25 2022-4-29
     */
    public static Result<String> success() {
        return new Result<>(0, "success", "");
    }

    /**
     * 返回成功结果并包含参数
     *
     * @param t 返回内容
     * @return com.hengyun.ms.common.vo.Result<T>
     * @author PeiXy_J
     * @since 09:25 2022-4-29
     */
    public static <T> Result<T> ok(T t) {
        return new Result<>(0, "success", t);
    }

    /**
     * 返回成功结果并包含参数
     *
     * @param t 返回内容
     * @return com.hengyun.ms.common.vo.Result<T>
     * @author PeiXy_J
     * @since 09:25 2022-4-29
     */
    public static <T> Result<T> success(T t) {
        return new Result<>(0, "success", t);
    }


    /**
     * 返回失败结果并包含描述
     *
     * @param message 描述
     * @return com.hengyun.ms.common.vo.Result<java.lang.String>
     * @author PeiXy_J
     * @since 09:26 2022-4-29
     */
    public static Result<String> error(String message) {
        return new Result<>(-1, message, null);
    }

    /**
     * 返回失败结果并包含描述
     *
     * @param message 描述
     * @return com.hengyun.ms.common.vo.Result<java.lang.String>
     * @author PeiXy_J
     * @since 09:26 2022-4-29
     */
    public static Result<String> fail(String message) {
        return new Result<>(-1, message, null);
    }

    /**
     * 返回失败结果并包含错误码和错误信息
     *
     * @param code    错误码
     * @param message 描述
     * @return com.hengyun.ms.common.vo.Result<java.lang.String>
     * @author PeiXy_J
     * @since 09:26 2022-4-29
     */
    public static Result<String> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static Result<String> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    /**
     * 根据条件返回对应状态
     *
     * @param condition 条件
     * @param message   描述
     * @return com.hengyun.ms.common.vo.Result<?>
     * @author PeiXy_J
     * @since 09:27 2022-4-29
     */
    public static Result<String> condition(Boolean condition, String message) {
        if (Boolean.TRUE.equals(condition)) {
            return Result.ok();
        } else {
            return Result.error(message);
        }
    }

    /**
     * 根据Boolean返回结果
     *
     * @param condition 条件
     * @return com.hengyun.ms.common.vo.Result<?>
     * @author PeiXy_J
     * @since 13:47 2022-6-15
     */
    public static Result<String> condition(Boolean condition) {
        if (Boolean.TRUE.equals(condition)) {
            return Result.ok();
        } else {
            return Result.error("fault");
        }
    }

}

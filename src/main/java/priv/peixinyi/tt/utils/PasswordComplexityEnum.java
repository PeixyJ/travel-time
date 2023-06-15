package priv.peixinyi.tt.utils;

import lombok.Getter;

/**
 * 密码复杂度枚举
 *
 * @author peixinyi
 */
@Getter
public enum PasswordComplexityEnum {

    LEVEL_0(0, "^(?=.{6,64})(?!\\d+$)[\\u4E00-\\u9FA5a-zA-Z0-9.\\-+!]*$", "密码长度至少6位,至多64位,不能全为数字,特殊符号允许.、-、+、!"),
    LEVEL_1(1, "^(?=.{6,64})(?=.*[a-z])(?=.*[A-Z])(?!\\\\d+$)[\\u4E00-\\u9FA5a-zA-Z0-9.\\-+!]*$", "密码长度至少6位,至多64位,必须包含大小写字母,不能全为数字,特殊符号允许.、-、+、!"),
    LEVEL_2(2, "^(?=.{6,64})(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s])(?!\\\\d+$)[\\u4E00-\\u9FA5a-zA-Z0-9.\\-+!]*$", "密码长度至少6位,至多64位,必须包含大小写字母和特殊字符,不能全为数字,特殊符号允许.、-、+、!");

    private final int code;
    private final String regular;
    private final String message;

    PasswordComplexityEnum(int code, String regular, String message) {
        this.code = code;
        this.regular = regular;
        this.message = message;
    }

    /**
     * 根据code获取枚举
     *
     * @param code
     * @return com.hengyun.common.utils.password.PasswordComplexityEnum
     * @author peixinyi
     * @since 08:47 2023/5/15
     */
    public static PasswordComplexityEnum getByCode(int code) {
        for (PasswordComplexityEnum value : PasswordComplexityEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    /**
     * 检查密码复杂度
     *
     * @param password
     * @return boolean
     * @author peixinyi
     * @since 13:42 2023/5/15
     */
    public boolean checkPasswordComplexity(String password) {
        return password.matches(regular);
    }
}

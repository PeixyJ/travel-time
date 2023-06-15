package priv.peixinyi.tt.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 密码和盐值
 *
 * @author peixinyi
 */
@Data
@NoArgsConstructor
public class PasswordWithSalt {

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

}

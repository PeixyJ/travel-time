package priv.peixinyi.tt.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.UUID;

/**
 * 密码工具类
 *
 * @author peixinyi
 */
public class PasswordUtils {

    public static String generateSalt() {
        return UUID.randomUUID().toString();
    }

    /**
     * 密码加密
     *
     * @param password
     * @param salt
     * @return java.lang.String
     * @author PeiXy_J
     * @since 09:11 2023/3/7
     */
    public static String encrypt(String password, String salt) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        StringBuilder encodePassword = new StringBuilder(password);
        int passwordLength = password.length();
        for (int i = 0; i < passwordLength; i++) {
            if (i % 2 == 0) {
                encodePassword.append(salt);
            }
            encodePassword = new StringBuilder(md5.digestHex(encodePassword.toString()));
        }
        return encodePassword.toString();
    }

    /**
     * 密码加密
     *
     * @param password 密码
     * @return com.hengyun.common.utils.password.PasswordWithSalt
     * @author peixinyi
     * @since 16:34 2023/4/7
     */
    public static PasswordWithSalt encrypt(String password) {
        PasswordWithSalt passwordWithSalt = new PasswordWithSalt();
        String salt = generateSalt();
        passwordWithSalt.setSalt(salt);
        passwordWithSalt.setPassword(encrypt(password, salt));
        return passwordWithSalt;
    }

    /**
     * 密码比较
     *
     * @param inputPassword 输入密码
     * @param salt          盐
     * @param password      加密后的密码
     * @return boolean
     * @author PeiXy_J
     * @since 09:11 2023/31/7
     */
    public static boolean validatePassword(String inputPassword, String salt, String password) {
        return encrypt(inputPassword, salt).equals(password);
    }


}

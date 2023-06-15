package priv.peixinyi.tt.context;

/**
 * 行驶时间内容
 *
 * @author peixinyi
 */
public class TTContext {

    private static ThreadLocal<Integer> userId = new ThreadLocal<>();
    private static ThreadLocal<String> openId = new ThreadLocal<>();

    public static Integer getUserId() {
        return userId.get();
    }

    public static void setUserId(Integer userId) {
        TTContext.userId.set(userId);
    }

    public static String getOpenId() {
        return openId.get();
    }

    public static void setOpenId(String openId) {
        TTContext.openId.set(openId);
    }

}

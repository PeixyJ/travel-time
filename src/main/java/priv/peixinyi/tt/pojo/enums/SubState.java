package priv.peixinyi.tt.pojo.enums;

/**
 * @author peixinyi
 */
public enum SubState {
    APPLY_FOR(0, "申请中"),
    AGREE(1, "同意"),
    REFUSE(-1, "拒绝"),
    ;

    private Integer code;

    private String desc;

    SubState(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Integer status) {
        for (SubState value : SubState.values()) {
            if (value.getCode().equals(status)) {
                return value.getDesc();
            }
        }
        return null;
    }


    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}


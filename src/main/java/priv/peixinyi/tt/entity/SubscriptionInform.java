package priv.peixinyi.tt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${description}
 * 
 * @author peixinyi 
 */
/**
    * 行驶记录表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "subscription_inform")
public class SubscriptionInform implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 订阅ID
     */
    @TableField(value = "sub_id")
    private Integer subId;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_SUB_ID = "sub_id";

    public static final String COL_STATUS = "status";
}
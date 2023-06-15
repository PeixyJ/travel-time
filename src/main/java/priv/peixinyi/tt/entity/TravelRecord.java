package priv.peixinyi.tt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
@TableName(value = "travel_record")
public class TravelRecord implements Serializable {
    /**
     * 行驶记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 行驶编号
     */
    @TableField(value = "travel_code")
    private String travelCode;

    /**
     * 行驶发生时间
     */
    @TableField(value = "travel_time")
    private Date travelTime;

    /**
     * 驾驶时间
     */
    @TableField(value = "driving_time")
    private Long drivingTime;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private String longitude;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private String latitude;

    /**
     * 行驶距离
     */
    @TableField(value = "travel_distance")
    private Integer travelDistance;

    /**
     * 驾驶类型
     */
    @TableField(value = "travel_type")
    private String travelType;

    /**
     * 地点
     */
    @TableField(value = "site")
    private String site;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_TRAVEL_CODE = "travel_code";

    public static final String COL_TRAVEL_TIME = "travel_time";

    public static final String COL_DRIVING_TIME = "driving_time";

    public static final String COL_LONGITUDE = "longitude";

    public static final String COL_LATITUDE = "latitude";

    public static final String COL_TRAVEL_DISTANCE = "travel_distance";

    public static final String COL_TRAVEL_TYPE = "travel_type";

    public static final String COL_SITE = "site";
}
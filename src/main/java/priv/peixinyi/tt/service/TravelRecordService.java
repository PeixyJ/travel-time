package priv.peixinyi.tt.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import priv.peixinyi.tt.mapper.TravelRecordMapper;
import priv.peixinyi.tt.entity.TravelRecord;

/**
 * ${description}
 *
 * @author peixinyi
 */
@Service
public class TravelRecordService extends ServiceImpl<TravelRecordMapper, TravelRecord> {


    /**
     * 根据用户ID删除行程
     *
     * @param loginIdAsInt
     * @return void
     * @author peixinyi
     * @since 09:06 2023/6/15
     */
    public void removeByUserId(int loginIdAsInt) {
        LambdaQueryWrapper<TravelRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TravelRecord::getUserId, loginIdAsInt);
        this.remove(queryWrapper);
    }

    /**
     * 根据用户ID和行程类型获取行程
     *
     * @param userId
     * @param name
     * @return priv.peixinyi.tt.entity.TravelRecord
     * @author peixinyi
     * @since 10:48 2023/6/15
     */
    public TravelRecord getTravelRecordByUserIdAndTravelType(Integer userId, String name) {
        LambdaQueryWrapper<TravelRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TravelRecord::getUserId, userId);
        queryWrapper.eq(TravelRecord::getTravelType, name);
        queryWrapper.last("limit 1");
        queryWrapper.orderByDesc(TravelRecord::getTravelTime);
        return this.getOne(queryWrapper);
    }

    /**
     * 根据用户ID获取最后一条行程
     *
     * @return priv.peixinyi.tt.entity.TravelRecord
     * @author peixinyi
     * @since 11:06 2023/6/15
     */
    public TravelRecord getTravelRecordLastByUserId(Integer userId) {
        LambdaQueryWrapper<TravelRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TravelRecord::getUserId, userId);
        queryWrapper.orderByDesc(TravelRecord::getTravelTime);
        queryWrapper.last("limit 1");
        return this.getOne(queryWrapper);
    }

}

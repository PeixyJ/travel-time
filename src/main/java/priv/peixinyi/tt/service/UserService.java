package priv.peixinyi.tt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import priv.peixinyi.tt.exception.IRException;
import priv.peixinyi.tt.mapper.UserMapper;
import priv.peixinyi.tt.entity.User;
import priv.peixinyi.tt.pojo.Result;
import priv.peixinyi.tt.utils.CheckData;
import priv.peixinyi.tt.utils.PasswordComplexityEnum;
import priv.peixinyi.tt.utils.PasswordUtils;
import priv.peixinyi.tt.utils.PasswordWithSalt;

import java.util.List;
import java.util.UUID;

/**
 * ${description}
 *
 * @author peixinyi
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements CheckData<User> {


    @Override
    public long iCount(QueryWrapper<User> queryWrapper) {
        return count(queryWrapper);
    }

    @Override
    public List<User> iList(QueryWrapper<User> queryWrapper) {
        return list(queryWrapper);
    }

    @Override
    public User getOne(QueryWrapper<User> queryWrapper) {
        return super.getOne(queryWrapper);
    }


    /**
     * 新增用户
     *
     * @param user
     * @return void
     * @author peixinyi
     * @since 08:45 2023/6/15
     */
    public void addUser(User user) {
        checkDataRepeat(User.COL_NICKNAME, user.getNickname(), "昵称重复");
        //检查密码
        if (!PasswordComplexityEnum.LEVEL_0.checkPasswordComplexity(user.getPassword())) {
            throw new IRException("密码复杂度不够");
        }
        //密码加盐加密
        PasswordWithSalt encrypt = PasswordUtils.encrypt(user.getPassword());
        user.setPassword(encrypt.getPassword());
        user.setSalt(encrypt.getSalt());
        user.setOpenId(UUID.randomUUID().toString());
        save(user);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return priv.peixinyi.tt.entity.User
     * @author peixinyi
     * @since 09:01 2023/6/15
     */
    public User updateUser(User user) {
        updateById(user);
        return getById(user.getId());
    }

    /**
     * 检查密码是否正确 正确返回True
     *
     * @param loginIdAsInt
     * @param password
     * @return boolean
     * @author peixinyi
     * @since 09:05 2023/6/15
     */
    public boolean checkPassword(int loginIdAsInt, String password) {
        User byId = getById(loginIdAsInt);
        String encrypt = PasswordUtils.encrypt(password, byId.getSalt());
        return encrypt.equals(byId.getPassword());
    }

    /**
     * 刷新用户的OpenId
     *
     * @return void
     * @author peixinyi
     * @since 09:08 2023/6/15
     */
    public void refreshOpenId(Integer userId) {
        User one = getById(userId);
        one.setOpenId(UUID.randomUUID().toString());
        updateById(one);
    }

    /**
     * 根据OpenId获取用户
     *
     * @param openId
     * @return priv.peixinyi.tt.entity.User
     * @author peixinyi
     * @since 09:22 2023/6/15
     */
    public User getUserByOpenId(String openId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(User.COL_OPEN_ID, openId);
        return getOne(queryWrapper);
    }


    /**
     * 根据用户名获取用户
     *
     * @param name
     * @return priv.peixinyi.tt.entity.User
     * @author peixinyi
     * @since 09:30 2023/6/15
     */
    public User getUserByUsername(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(User.COL_NICKNAME, name);
        queryWrapper.last("limit 1");
        return getOne(queryWrapper);
    }
}

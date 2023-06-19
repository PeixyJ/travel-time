package priv.peixinyi.tt.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.peixinyi.tt.alert.BarkHandler;
import priv.peixinyi.tt.context.TTContext;
import priv.peixinyi.tt.entity.TravelRecord;
import priv.peixinyi.tt.entity.User;
import priv.peixinyi.tt.pojo.enums.TravelType;
import priv.peixinyi.tt.service.TravelRecordService;
import priv.peixinyi.tt.service.UserService;

import java.util.Date;
import java.util.UUID;

/**
 * 行驶记录控制层
 *
 * @author peixinyi
 */
@RestController
@RequestMapping("/travelRecord")
@AllArgsConstructor
public class TravelRecordController {

    UserService userService;

    TravelRecordService travelRecordService;

    /**
     * 驾驶
     *
     * @param longitude
     * @param latitude
     * @param site
     * @return void
     * @author peixinyi
     * @since 11:10 2023/6/15
     */
    @PostMapping("drive")
    public void drive(@RequestParam String longitude,
                      @RequestParam String latitude,
                      @RequestParam String site) {
        TravelRecord travelRecordLastByUserId = travelRecordService.getTravelRecordLastByUserId(TTContext.getUserId(), 180);
        if (travelRecordLastByUserId == null) {
            connect(longitude, latitude, site);
            return;
        }
        if (travelRecordLastByUserId.getTravelType().equals(TravelType.START.name())) {
            disconnect(longitude, latitude, site);
        } else {
            connect(longitude, latitude, site);
        }
    }

    /**
     * 开始行驶
     *
     * @param longitude
     * @param latitude
     * @return void
     * @author peixinyi
     * @since 10:34 2023/6/15
     */
    public void connect(@RequestParam String longitude,
                        @RequestParam String latitude,
                        @RequestParam String site) {
        TravelRecord tr = new TravelRecord();
        tr.setUserId(TTContext.getUserId());
        tr.setTravelCode(UUID.randomUUID().toString());
        tr.setTravelTime(new Date());
        tr.setDrivingTime(0L);
        tr.setLongitude(longitude);
        tr.setLatitude(latitude);
        tr.setTravelDistance(0);
        tr.setTravelType(TravelType.START.name());
        tr.setSite(site);
        travelRecordService.save(tr);
    }

    /**
     * 结束行驶
     *
     * @param longitude
     * @param latitude
     * @return void
     * @author peixinyi
     * @since 10:34 2023/6/15
     */
    public void disconnect(@RequestParam String longitude,
                           @RequestParam String latitude,
                           @RequestParam String site) {

        //获取开始行驶的记录
        TravelRecord str = travelRecordService.getTravelRecordByUserIdAndTravelType(TTContext.getUserId(), TravelType.START.name());
        if (str == null) {
            return;
        }
        TravelRecord etr = new TravelRecord();
        etr.setUserId(TTContext.getUserId());
        etr.setTravelCode(str.getTravelCode());
        etr.setTravelTime(new Date());
        etr.setDrivingTime((new Date().getTime() - str.getTravelTime().getTime()) / 1000);
        etr.setLongitude(longitude);
        etr.setLatitude(latitude);
        Double dis = getDis(Double.parseDouble(str.getLatitude()), Double.parseDouble(str.getLongitude()), Double.parseDouble(latitude), Double.parseDouble(longitude));
        etr.setTravelDistance(dis.intValue());
        etr.setTravelType(TravelType.END.name());
        etr.setSite(site);
        travelRecordService.save(etr);

        User user = userService.getUserByOpenId(TTContext.getOpenId());
        String alert = "您的小伙伴" + user.getNickname() + "刚刚在" + site + "结束了一次行程,行驶了" + dis.intValue() + "米,用时" + second2MinuteAndSecond(etr.getDrivingTime()) + "!";
        BarkHandler.sendBark(user.getBarkId(), alert);
    }

    public static String second2MinuteAndSecond(Long drivingTime) {
        long minute = drivingTime / 60;
        long second = drivingTime % 60;
        if (minute == 0) {
            return second + "秒";
        }
        return minute + "分" + second + "秒";
    }

    /**
     * 获取两个经纬度之间的距离
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return java.lang.Double
     * @author peixinyi
     * @since 10:38 2023/6/15
     */
    public static Double getDis(Double lat1, Double lon1, Double lat2, Double lon2) {
        double radLat1 = (lat1 * Math.PI) / 180;
        double radLat2 = (lat2 * Math.PI) / 180;
        double a = radLat1 - radLat2;
        double b = (lon1 * Math.PI) / 180 - (lon2 * Math.PI) / 180;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(
                Math.sin(b / 2), 2)));
        s = s * 6378137.0;
        return s;
    }
}

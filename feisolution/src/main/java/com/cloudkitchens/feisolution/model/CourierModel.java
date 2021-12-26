package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.util.Constants;
import com.cloudkitchens.feisolution.util.DateUtil;
import com.cloudkitchens.feisolution.util.MathUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode
public class CourierModel {

    private String id;
    private Date dispatchTime;//order received time
    private Date estArriveTime;//estimated time of arriving kitchen, following uniform distribution
    private Date pickUpTime;//order picked up time
    private CourierState state;

    public CourierModel(){
        this.id = UUID.randomUUID().toString();
    }

    /**
     * @return 返还单位为秒,无estArriveTime和无arrive的返回null
     */
    public Long calWaitingTime(){
        if (this.estArriveTime == null || this.pickUpTime == null){
            return null;
        }

        Long diff = this.pickUpTime.getTime() - this.estArriveTime.getTime();
        return diff;
    }

    /**
     * 更新状态和相关时间
     */
    public void setState(CourierState state, Date time){
        this.state = state;
        switch (state){
            case DISPATCHED_TO_KITCHEN:
                this.setDispatchTime(time);
                this.setEstArriveTime(calMockArriveTime(time));
                System.out.println(String.format("%s: Courier %s is dispatched to kitchen.", DateUtil.HHmmssSSS.format(time), this.getId()));
                break;
            case ARRIVED_KITCHEN:
                System.out.println(String.format("%s: Courier %s arrived kitchen.", DateUtil.HHmmssSSS.format(time), this.getId()));
                break;
            case PICKED_UP_ORDER:
                this.setPickUpTime(time);
                System.out.println(String.format("%s: Courier %s picked up order.", DateUtil.HHmmssSSS.format(time), this.getId()));
                break;
            default:
                break;
        }
    }

    /** must use setState(CourierState state, Date time) */
    private void setState(CourierState state){}

    /**
     * TODO 拆分到UTIL
     * 随机分配预期时间
     * @param orderReceiveTime order receive time
     * @return arrive time
     */
    private Date calMockArriveTime(Date orderReceiveTime) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(orderReceiveTime);
        cl.add(Calendar.SECOND, MathUtil.getRandomWithinRange(Constants.COURIER_TRIP_TIME_MIN_SEC,
                Constants.COURIER_TRIP_TIME_MAX_SEC));
        return cl.getTime();
    }

}

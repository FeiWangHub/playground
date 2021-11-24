package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.util.Constants;
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
    private Date arriveTime;
    private Date dispatchTime;
    private Date estReturnTime;//estimated return time, following uniform distribution
    private CourierState state;

    public CourierModel(){
        this.id = UUID.randomUUID().toString();
    }

    /**
     * @return 返还单位为秒,无dispatch和无arrive的返回0
     */
    public Float calWaitingTime(){
        if (this.arriveTime == null || this.dispatchTime == null){
            return (float) 0;
        }

        Float diff = (float) ((this.dispatchTime.getTime() - this.arriveTime.getTime()) / 1000);
        return diff;
    }

    /**
     * 更新状态和相关时间
     */
    public void setState(CourierState state, Date time){
        this.state = state;
        switch (state){
            case ARRIVED:
                this.setArriveTime(time);
                this.setDispatchTime(null);
                break;
            case DISPATCHED:
                //TODO 这里需要记录waiting time
                this.setDispatchTime(time);
                //随机分配预期时间
                Calendar cl = Calendar.getInstance();
                cl.setTime(time);
                cl.add(Calendar.SECOND, MathUtil.getRandomWithinRange(Constants.COURIER_TRIP_TIME_MIN_SEC,
                        Constants.COURIER_TRIP_TIME_MAX_SEC));
                this.setEstReturnTime(cl.getTime());
                break;
            default:
                break;
        }
    }

}

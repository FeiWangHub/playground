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
     * @return in milliseconds, return null if estArriveTime or pickUpTime is null
     */
    public Long calWaitingTime(){
        if (this.estArriveTime == null || this.pickUpTime == null){
            return null;
        }

        Long diff = this.pickUpTime.getTime() - this.estArriveTime.getTime();
        return diff;
    }

    /**
     * Update state of Courier and corresponding time
     * @param state new state
     * @param time event time
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
                this.setEstArriveTime(time);
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
     * 随机分配预期时间
     * @param orderReceiveTime order receive time
     * @return arrive time
     */
    public Date calMockArriveTime(Date orderReceiveTime) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(orderReceiveTime);
        cl.add(Calendar.SECOND, MathUtil.getRandomWithinRange(Constants.COURIER_TRIP_TIME_MIN_SEC,
                Constants.COURIER_TRIP_TIME_MAX_SEC));
        return cl.getTime();
    }

}

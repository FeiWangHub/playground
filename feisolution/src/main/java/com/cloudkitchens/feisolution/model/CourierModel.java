package com.cloudkitchens.feisolution.model;

import lombok.Data;

import java.util.Date;

@Data
public class CourierModel {

    private String id;
    private Date arriveTime;
    private Date dispatchTime;
    private CourierState state;

    /**
     * @return 返还单位为秒,未dispatch的返回null
     */
    public Float calWaitingTime(){
        if (this.arriveTime == null || this.dispatchTime == null){
            return null;
        }

        Float diff = (float) ((this.dispatchTime.getTime() - this.arriveTime.getTime()) / 1000);
        return diff;
    }

    /**
     * 更新状态和相关时间
     */
    public void setState(CourierState state){
        this.state = state;
        switch (state){
            case ARRIVED:
                this.setArriveTime(new Date());
                this.setDispatchTime(null);
                break;
            case DISPATCHED:
                //TODO 这里需要记录waiting time
                this.setDispatchTime(new Date());
                break;
            default:
                break;
        }
    }

}

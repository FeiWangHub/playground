package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class OrderModel {

    private String id;
    private String name;
    private int prepTime;
    private Date receiveTime;
    private Date readyTime;//Ready for pickup
    private Date pickedUpTime;
    private OrderState state;
    private CourierModel courier;

    private CourierModel courierDispatchedByThisOrder;//for interview simulation purpose
    private Date estReadyTime;//for interview purpose

    /**
     * @return 返还单位为毫秒, 未完成的订单返回null
     */
    public Long calWaitingTime() {
        if (this.readyTime == null || this.pickedUpTime == null) {
            return null;
        }

        Long diff = this.pickedUpTime.getTime() - this.readyTime.getTime();
        return diff;
    }

    /**
     * 更新状态和相关时间
     */
    public void setState(OrderState state, Date time) {
        this.state = state;
        switch (state) {
            case RECEIVED:
                this.setReceiveTime(time);
                this.setEstReadyTime(calEstReadyTime());
                System.out.println(String.format("%s: Order %s received.", DateUtil.HHmmssSSS.format(time), this.getId()));
                break;
            case READY:
                this.setReadyTime(time);
                System.out.println(String.format("%s: Order %s is ready for pickup.", DateUtil.HHmmssSSS.format(time), this.getId()));
                break;
            case PICKED_UP:
                this.setPickedUpTime(time);
                System.out.println(String.format("%s: Order %s is picked up by courier %s.", DateUtil.HHmmssSSS.format(time), this.getId(), this.getCourier().getId()));
                break;
            default:
                break;
        }
    }

    /**
     * must use setState(OrderState state, Date time){
     */
    private void setState(OrderState state) {
    }

    //for interview mock purpose
    private Date calEstReadyTime() {
        return DateUtil.addSecond(this.getReceiveTime(), this.getPrepTime());
    }

}


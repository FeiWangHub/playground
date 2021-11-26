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
   private String courierId;
   private Date receiveTime;
   private Date readyTime;//Ready for pickup
   private Date pickedUpTime;
   private OrderState state;

   /**
    * @return 返还单位为秒,未完成的订单返回null
    */
   public Float calWaitingTime(){
      if (this.readyTime == null || this.pickedUpTime == null){
         return null;
      }

      Float diff = (float) ((this.pickedUpTime.getTime() - this.receiveTime.getTime()) / 1000);
      return diff;
   }

   /**
    * 更新状态和相关时间
    */
   public void setState(OrderState state, Date time){
      this.state = state;
      switch (state) {
         case RECEIVED:
            this.setReceiveTime(time);
            System.out.println(String.format("%s: Order %s received.", DateUtil.HHmmssSSS.format(time), this.getId()));
            break;
         case READY:
            this.setReadyTime(time);
            System.out.println(String.format("%s: Order %s is ready for pickup.", DateUtil.HHmmssSSS.format(time), this.getId()));
            break;
         case PICKED_UP:
            this.setPickedUpTime(time);
            System.out.println(String.format("%s: Order %s is picked up by %s.", DateUtil.HHmmssSSS.format(time), this.getId(), this.getCourierId()));
            break;
         default:
            break;
      }
   }

   /** must use setState(OrderState state, Date time){ */
   private void setState(OrderState state){}

}


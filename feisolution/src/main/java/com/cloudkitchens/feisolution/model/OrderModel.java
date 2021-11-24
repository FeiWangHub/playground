package com.cloudkitchens.feisolution.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Data
@EqualsAndHashCode
public class OrderModel {

   private String id;
   private String name;
   private int prepTime;
   private String courierId;
   private Date receiveTime;
   private Date dispatchTime;
   private OrderState state;

   public OrderModel(){
      this.id = UUID.randomUUID().toString();
   }

   /**
    * @return 返还单位为秒,未完成的订单返回null
    */
   public Float calWaitingTime(){
      if (this.receiveTime == null || this.dispatchTime == null){
         return null;
      }

      Float diff = (float) ((this.dispatchTime.getTime() - this.receiveTime.getTime()) / 1000);
      return diff;
   }

   /**
    * 更新状态和相关时间
    */
   public void setState(OrderState state){
      this.state = state;
      switch (state) {
         case RECEIVED:
            this.setReceiveTime(new Date());
            break;
         case DISPATCHED:
            this.setDispatchTime(new Date());
            break;
         default:
            break;
      }
   }

}


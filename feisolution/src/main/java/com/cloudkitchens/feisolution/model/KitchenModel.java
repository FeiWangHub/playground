package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.service.dispatchService.KitchenStrategy;
import lombok.Data;

import java.util.Queue;

@Data
public class KitchenModel {

//    private Queue<OrderModel> ordersDB; TODO 不一定需要
    private Queue<OrderModel> dispatchedOrders;
    private Queue<CourierModel> couriersDB;
    private KitchenStrategy kitchenStrategy;

    /**
     * Kitchen received an order
     * @param order OrderModel
     */
    public void receiveOrder(OrderModel order){
        this.kitchenStrategy.onReceiveOrder(order);
    }

    /**
     * An courier arrives to kitchen
     * @param courier CourierModel
     */
    public void receiveCourier(CourierModel courier){
        this.kitchenStrategy.onReceiveCourier(courier);
    }

    /**
     * switch strategy in runtime
     * TODO actually this should also copy the queue data stored in strategy
     * omitted for interview
     * @param k KitchenStrategy
     */
    public void setKitchenStrategy(KitchenStrategy k){
        this.kitchenStrategy = k;
    }

}

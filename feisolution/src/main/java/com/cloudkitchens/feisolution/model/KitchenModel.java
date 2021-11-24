package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.service.dispatchService.KitchenStrategy;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class KitchenModel {

    private Queue<OrderModel> dispatchedOrders;
    private Queue<CourierModel> couriersDB;
    private KitchenStrategy kitchenStrategy;

    public KitchenModel(KitchenStrategy strategy){
        this.kitchenStrategy = strategy;
        this.dispatchedOrders = new LinkedList<>();
    }

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
     * scan and dispatch ready orders
     * different strategy defines 'ready' differently
     */
    public void scanAndDispatchReadyOrder(){
        this.kitchenStrategy.scanAndDispatchReadyOrder();
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

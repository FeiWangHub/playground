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
     * scan and dispatch ready orders
     * different strategy defines 'ready' differently
     */
    public void scanAndPickupReadyOrders(){
        this.kitchenStrategy.scanAndPickupReadyOrders();
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

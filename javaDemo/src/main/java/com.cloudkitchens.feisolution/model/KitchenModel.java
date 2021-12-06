package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.service.dispatchService.KitchenStrategy;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class KitchenModel {

    private Queue<OrderModel> dispatchedOrders;
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
     * check and Update courier's arrive state
     * (For interview mock purpose, in real world onCourierArrive is an event)
     */
    public void updateCouriersArriveState(){
        this.kitchenStrategy.updateCouriersArriveState();
        this.scanAndPickupReadyOrders();
    }

    /**
     * check and update order's ready state
     * (For interview mock purpose, in real world onOrderReady is an event)
     */
    public void updateOrderReadyState(){
        this.kitchenStrategy.updateOrderReadyState();
        this.scanAndPickupReadyOrders();
    }

    /**
     * scan and dispatch ready orders
     * different strategy defines 'ready' differently
     */
    public void scanAndPickupReadyOrders(){
        this.dispatchedOrders.addAll(
            this.kitchenStrategy.scanAndPickupReadyOrders()
        );
    }

    /**
     * switch strategy in runtime(not supported yet)
     * TODO actually this should also copy the queue data stored in strategy
     * omitted for interview
     * @param k KitchenStrategy
     */
    private void setKitchenStrategy(KitchenStrategy k){
        this.kitchenStrategy = k;
    }

}

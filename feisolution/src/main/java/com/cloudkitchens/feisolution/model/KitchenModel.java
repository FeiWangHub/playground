package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.service.dispatchService.KitchenStrategy;
import com.cloudkitchens.feisolution.util.DateUtil;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.cloudkitchens.feisolution.model.CourierState.ARRIVED_KITCHEN;
import static com.cloudkitchens.feisolution.model.CourierState.DISPATCHED_TO_KITCHEN;

@Data
public class KitchenModel {

    /** 已经收到的，尚未pickup的订单 */
    protected ConcurrentLinkedQueue<OrderModel> ordersQueue;
    /** 已收到order，尚未pickup的couriers */
    protected ConcurrentLinkedQueue<CourierModel> couriersQueue;

    private Queue<OrderModel> dispatchedOrders;
    private KitchenStrategy kitchenStrategy;

    public KitchenModel(KitchenStrategy strategy){
        this.kitchenStrategy = strategy;
        this.ordersQueue = new ConcurrentLinkedQueue<>();
        this.couriersQueue = new ConcurrentLinkedQueue<>();
        this.dispatchedOrders = new LinkedList<>();
    }

    /**
     * 接受到新order时 update order state, and dispatch courier
     * @param order OrderModel
     */
    public synchronized void receiveOrder(OrderModel order){
        Date now = new Date();
        order.setState(OrderState.RECEIVED, now);
        this.ordersQueue.add(order);

        //DISPATCH AN COURIER
        CourierModel c = new CourierModel();
        c.setState(DISPATCHED_TO_KITCHEN, now);
        this.couriersQueue.add(c);

        this.kitchenStrategy.afterReceiveOrder(order, c);
    };

    /**
     * check and Update courier's arrive state
     */
    public synchronized void updateCouriersArriveState(){
        Iterator<CourierModel> it = this.couriersQueue.iterator();
        Date now = new Date();
        while(it.hasNext()){
            CourierModel c = it.next();
            if (c.getState() == DISPATCHED_TO_KITCHEN && now.after(c.getEstArriveTime())){
                c.setState(ARRIVED_KITCHEN, c.getEstArriveTime());
            }
        }
    }

    /**
     * check and update order's ready state
     */
    public synchronized void updateOrderReadyState(){
        Iterator<OrderModel> it = this.ordersQueue.iterator();
        Date now = new Date();
        while(it.hasNext()){
            OrderModel o = it.next();
            Date readyTime = DateUtil.addSecond(o.getReceiveTime(), o.getPrepTime());
            if (o.getState() == OrderState.RECEIVED && now.after(readyTime)){
                o.setState(OrderState.READY, readyTime);
            }
        }
    }

    /**
     * scan and dispatch ready orders
     * different strategy defines 'ready' differently
     * @return orders picked up
     */
    public synchronized List<OrderModel> scanAndPickupReadyOrders(){
        List<OrderModel> ordersPickedUp = this.kitchenStrategy.scanAndPickupReadyOrders(this.ordersQueue, this.couriersQueue);
        this.dispatchedOrders.addAll(ordersPickedUp);
        return ordersPickedUp;
    }

    /**
     * switch strategy in runtime(not supported yet)
     * TODO actually this should also update data state stored in queue, omitted for interview
     * omitted for interview
     * @param k KitchenStrategy
     */
    private void setKitchenStrategy(KitchenStrategy k){
        this.kitchenStrategy = k;
    }

}

package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.OrderModel;
import com.cloudkitchens.feisolution.model.OrderState;
import com.cloudkitchens.feisolution.util.DateUtil;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.cloudkitchens.feisolution.model.CourierState.ARRIVED_KITCHEN;
import static com.cloudkitchens.feisolution.model.CourierState.DISPATCHED_TO_KITCHEN;

public abstract class KitchenStrategy {

    /** 已经收到的，尚未pickup的订单 */
    protected ConcurrentLinkedQueue<OrderModel> ordersQueue;

    /** 已收到order，尚未pickup的couriers */
    protected ConcurrentLinkedQueue<CourierModel> couriersQueue;

    public KitchenStrategy(){
        this.ordersQueue = new ConcurrentLinkedQueue<>();
        this.couriersQueue = new ConcurrentLinkedQueue<>();
    }

    /**
     * 接受到新order时 update order state, and dispatch courier
     * @param order OrderModel
     */
    public void onReceiveOrder(OrderModel order){
        Date now = new Date();
        order.setState(OrderState.RECEIVED, now);
        this.ordersQueue.add(order);

        //DISPATCH AN COURIER
        CourierModel c = new CourierModel();
        c.setState(DISPATCHED_TO_KITCHEN, now);
        this.couriersQueue.add(c);

        this.afterReceiveOrder(order, c);
    };

    /**
     * Hook after receive order and dispatched courier
     * @param order OrderModel
     */
    public void afterReceiveOrder(OrderModel order, CourierModel courier){};

    /**
     * check and Update courier's arrive state
     * (For interview mock purpose, in real world onCourierArrive is an event)
     */
    public void updateCouriersArriveState(){
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
     * (For interview mock purpose, in real world onOrderReady is an event)
     */
    public void updateOrderReadyState(){
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
     * scan and pick-up all ready orders
     * different strategy defines 'ready' differently
     * @return list of orders picked up
     */
    public abstract List<OrderModel> scanAndPickupReadyOrders();

    /**
     * Assign order to a courier
     * @param order OrderModel
     * @param courier_id String
     */
    public void assignCourier(OrderModel order, String courier_id){
        order.setCourierId(courier_id);
    };

}

package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.service.dispatchService.DispatchStrategy;
import lombok.Data;

import java.util.*;

import static com.cloudkitchens.feisolution.model.CourierState.*;
import static com.cloudkitchens.feisolution.model.OrderState.PICKED_UP;

@Data
public class KitchenDispatcher {

    protected LinkedList<OrderModel> ordersQueue;/** orders received, but not picked up yet */
    protected LinkedList<CourierModel> couriersQueue;/** couriers dispatched|arrived, but haven't picked up order yet*/

    private Queue<OrderModel> finishedOrders;
    private DispatchStrategy dispatchStrategy;

    public KitchenDispatcher(DispatchStrategy strategy){
        this.dispatchStrategy = strategy;
        this.ordersQueue = new LinkedList<>();
        this.couriersQueue = new LinkedList<>();
        this.finishedOrders = new LinkedList<>();
    }

    /**
     * Receive new order, update order state, and dispatch courier
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
        order.setCourierDispatchedByThisOrder(c);//for interview simulation purpose

        this.dispatchStrategy.afterReceiveOrder(order, c);
    };

    /**
     * when an order is prepared
     * @param order OrderModel, state should be READY
     */
    public synchronized void onOrderReady(OrderModel order){
        this.dispatchStrategy.onOrderReady(order, this);
    }

    /**
     * when a courier arrived kitchen
     * @param courier CourierModel, state should be ARRIVED_KITCHEN
     */
    public synchronized void onCourierArrived(CourierModel courier){
        this.dispatchStrategy.onCourierArrived(courier, this);
    }

    /**
     * When a order is picked up by a courier
     * @param order OrderModel
     * @param courier CourierModel
     */
    public synchronized void onOrderPickedUp(OrderModel order, CourierModel courier){
        Date pickupTime = order.getReadyTime().after(courier.getEstArriveTime())
                ? order.getReadyTime()
                : courier.getEstArriveTime();
        courier.setState(PICKED_UP_ORDER, pickupTime);
        order.setState(PICKED_UP, pickupTime);

        this.getFinishedOrders().add(order);

        this.getOrdersQueue().remove(order);
        this.getCouriersQueue().remove(courier);
    }

    /**
     * The length of ordersQueue and couriersQueue should always be same
     * Used for UT
     */
    public synchronized boolean isOrderAndCourierQueueEqual(){
        return this.ordersQueue.size() == this.couriersQueue.size();
    }

    /**
     * switch strategy in runtime(not supported yet)
     * @param k KitchenStrategy
     */
    private void setDispatchStrategy(DispatchStrategy k){}

}

package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.OrderModel;
import com.cloudkitchens.feisolution.model.OrderState;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.cloudkitchens.feisolution.model.CourierState.DISPATCHED_TO_KITCHEN;

public abstract class KitchenStrategy {

    /** 已经收到的，尚未pickup的订单 */
    private ConcurrentLinkedQueue<OrderModel> ordersQueue;

    /** 已收到order，尚未pickup的couriers */
    private ConcurrentLinkedQueue<CourierModel> couriersQueue;

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

        this.assignCourierToOrder(order, c);
    };

    /**
     * assign a courier for order
     * @param order OrderModel
     */
    public abstract void assignCourierToOrder(OrderModel order, CourierModel courier);

    /**
     * scan and pick-up all ready orders
     * different strategy defines 'ready' differently
     */
    public abstract void scanAndPickupReadyOrders();

    /**
     * Assign order to a courier
     * @param order OrderModel
     * @param courier_id String
     */
    public void assignCourier(OrderModel order, String courier_id){
        order.setCourierId(courier_id);
    };

}

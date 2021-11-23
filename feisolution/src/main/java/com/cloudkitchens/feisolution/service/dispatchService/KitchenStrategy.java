package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.CourierState;
import com.cloudkitchens.feisolution.model.OrderModel;
import com.cloudkitchens.feisolution.model.OrderState;

import java.util.Queue;

public abstract class KitchenStrategy {

    /** 已经收到的，尚未dispatch的订单 */
    private Queue<OrderModel> ordersQueue;

    /** 已经到达kitchen，尚未dispatch的couriers */
    private Queue<CourierModel> couriersQueue;

    /**
     * 接受到新order时 更新状态并相应操作
     * @param order OrderModel
     */
    public void onReceiveOrder(OrderModel order){
        order.setState(OrderState.RECEIVED);
        this.ordersQueue.add(order);
    };

    /**
     * Couriers到店时 更新状态并相应操作
     * @param courier CourierModel
     */
    public void onReceiveCourier(CourierModel courier){
        courier.setState(CourierState.ARRIVED);
        this.couriersQueue.add(courier);
    };

    /**
     * Dispatch order out
     * @param order Model
     * @param courier Model
     */
    public void dispatchOrder(OrderModel order, CourierModel courier){
        order.setState(OrderState.DISPATCHED);
        courier.setState(CourierState.DISPATCHED);
        this.ordersQueue.remove(order);
        this.couriersQueue.remove(courier);
    };

    /**
     * Assign order to a courier
     * @param order OrderModel
     * @param courier_id String
     */
    public void assignCourier(OrderModel order, String courier_id){
        order.setCourierId(courier_id);
    };

}

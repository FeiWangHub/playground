package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.OrderModel;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface KitchenStrategy {

    /**
     * Hook after receive order and dispatched courier
     *
     * @param order OrderModel
     */
    void afterReceiveOrder(OrderModel order, CourierModel courier);

    /**
     * scan and pick-up all ready orders
     * different strategy defines 'ready' differently
     *
     * @param ordersQueue   current orders pool
     * @param couriersQueue current couriers queue
     * @return list of orders picked up
     */
    List<OrderModel> scanAndPickupReadyOrders(
            ConcurrentLinkedQueue<OrderModel> ordersQueue,
            ConcurrentLinkedQueue<CourierModel> couriersQueue
    );

}

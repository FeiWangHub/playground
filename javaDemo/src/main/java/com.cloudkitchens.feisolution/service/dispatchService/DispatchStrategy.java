package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.KitchenDispatcher;
import com.cloudkitchens.feisolution.model.OrderModel;

public interface DispatchStrategy {

    /**
     * Hook after receive order and dispatched courier
     *
     * @param order OrderModel
     */
    void afterReceiveOrder(OrderModel order, CourierModel courier);

    /**
     * Event of an order become ready
     * @param order an order just become ready
     * @param kitchenDis current kitchen dispatcher
     */
    void onOrderReady(OrderModel order, KitchenDispatcher kitchenDis);

    /**
     * Event of a courier arrived kitchen
     * @param courier the courier arrived kitchen
     * @param kitchenDis current kitchen dispatcher
     */
    void onCourierArrived(CourierModel courier, KitchenDispatcher kitchenDis);

}

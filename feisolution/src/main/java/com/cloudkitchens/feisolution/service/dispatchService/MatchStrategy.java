package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.OrderModel;

import java.util.List;

/**
 * Matched dispatch strategy
 * A courier is dispatched for a specific order and may only pick up that order
 */
public class MatchStrategy extends KitchenStrategy {

    /**
     * Assign order to specific Courier
     */
    @Override
    public void afterReceiveOrder(OrderModel order, CourierModel courier) {
        order.setCourierId(courier.getId());
    }

    @Override
    public synchronized List<OrderModel> scanAndPickupReadyOrders() {
        return null;
    }
}

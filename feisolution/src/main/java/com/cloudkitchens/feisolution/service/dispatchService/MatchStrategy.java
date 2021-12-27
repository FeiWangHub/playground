package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.KitchenDispatcher;
import com.cloudkitchens.feisolution.model.OrderModel;

import java.util.Iterator;

import static com.cloudkitchens.feisolution.model.CourierState.ARRIVED_KITCHEN;
import static com.cloudkitchens.feisolution.model.OrderState.READY;

/**
 * Matched dispatch strategy
 * A courier is dispatched for a specific order and may only pick up that order
 */
public class MatchStrategy implements DispatchStrategy {

    /**
     * Assign order to specific Courier
     */
    @Override
    public void afterReceiveOrder(OrderModel order, CourierModel courier) {
        order.setCourier(courier);
    }

    @Override
    public void onOrderReady(OrderModel order, KitchenDispatcher kitchenDis) {
        if (READY != order.getState()) return;

        Iterator<CourierModel> courier_it = kitchenDis.getCouriersQueue().iterator();
        while (courier_it.hasNext()) {
            CourierModel courier = courier_it.next();
            if (ARRIVED_KITCHEN == courier.getState() && order.getCourier().getId().equals(courier.getId())) {
                kitchenDis.onOrderPickedUp(order, courier);
                break;
            }
        }
    }

    @Override
    public void onCourierArrived(CourierModel courier, KitchenDispatcher kitchenDis) {
        if (ARRIVED_KITCHEN != courier.getState()) return;

        Iterator<OrderModel> order_it = kitchenDis.getOrdersQueue().iterator();
        while (order_it.hasNext()) {
            OrderModel order = order_it.next();
            if (READY == order.getState() && order.getCourier().getId().equals(courier.getId())) {
                kitchenDis.onOrderPickedUp(order, courier);
                break;
            }
        }
    }
}

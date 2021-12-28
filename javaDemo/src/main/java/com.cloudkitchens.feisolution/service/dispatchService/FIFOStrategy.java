package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.KitchenDispatcher;
import com.cloudkitchens.feisolution.model.OrderModel;

import java.util.Optional;

import static com.cloudkitchens.feisolution.model.CourierState.ARRIVED_KITCHEN;
import static com.cloudkitchens.feisolution.model.OrderState.READY;

/**
 * FIFO strategy
 * a courier picks up the next available order upon arrival. If there are
 * multiple orders available, pick up an arbitrary order.
 * If there are no available orders, couriers wait for the next available one.
 * When there are multiple couriers waiting, the next available order is assigned to the earliest​ a​rrived courier.
 */
public class FIFOStrategy implements DispatchStrategy {

    @Override
    public void afterReceiveOrder(OrderModel order, CourierModel courier) {
    }

    /**
     * assigned to the earliest​ a​rrived courier.
     */
    @Override
    public void onOrderReady(OrderModel order, KitchenDispatcher kitchenDis) {
        Optional<CourierModel> earliestCourier = kitchenDis.getCouriersQueue().stream()
                .filter(courier -> ARRIVED_KITCHEN == courier.getState())
                .sorted((a, b) -> a.getEstArriveTime().compareTo(b.getEstArriveTime()))
                .findFirst();

        if (earliestCourier.isPresent()) {
            order.setCourier(earliestCourier.get());
            kitchenDis.onOrderPickedUp(order, earliestCourier.get());
        }
    }

    /**
     * pick up an arbitrary order.
     */
    @Override
    public void onCourierArrived(CourierModel courier, KitchenDispatcher kitchenDis) {
        Optional<OrderModel> arbitraryOrder = kitchenDis.getOrdersQueue().stream()
                .filter(order -> READY == order.getState())
                .sorted((a, b) -> a.getEstReadyTime().compareTo(b.getEstReadyTime()))
                .findAny();

        if (arbitraryOrder.isPresent()) {
            arbitraryOrder.get().setCourier(courier);
            kitchenDis.onOrderPickedUp(arbitraryOrder.get(), courier);
        }
    }
}

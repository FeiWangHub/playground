package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.OrderModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.cloudkitchens.feisolution.model.CourierState.ARRIVED_KITCHEN;
import static com.cloudkitchens.feisolution.model.CourierState.PICKED_UP_ORDER;
import static com.cloudkitchens.feisolution.model.OrderState.PICKED_UP;
import static com.cloudkitchens.feisolution.model.OrderState.READY;

/**
 * FIFO strategy
 * a courier picks up the next available order upon arrival. If there are
 * multiple orders available, pick up an arbitrary order.
 * If there are no available orders, couriers wait for the next available one.
 * When there are multiple couriers waiting, the next available order is assigned to the earliest​ a​rrived courier.
 */
public class FIFOStrategy implements KitchenStrategy {

    @Override
    public void afterReceiveOrder(OrderModel order, CourierModel courier) {
    }

    @Override
    public List<OrderModel> scanAndPickupReadyOrders(ConcurrentLinkedQueue<OrderModel> ordersQueue, ConcurrentLinkedQueue<CourierModel> couriersQueue) {
        ArrayList<OrderModel> pickedUpOrders = new ArrayList<>();

        Iterator<CourierModel> courier_it = couriersQueue.iterator();
        Iterator<OrderModel> order_it = ordersQueue.iterator();

        while (courier_it.hasNext() && order_it.hasNext()) {
            CourierModel courier = courier_it.next();
            if (courier.getState() != ARRIVED_KITCHEN) {
                continue;
            }

            //try find a ready order
            while (order_it.hasNext()) {
                OrderModel order = order_it.next();
                if (order.getState() != READY) {
                    continue;
                }

                Date pickupTime = order.getReadyTime().after(courier.getEstArriveTime())
                        ? order.getReadyTime()
                        : courier.getEstArriveTime();
                courier.setState(PICKED_UP_ORDER, pickupTime);
                order.setCourier(courier);
                order.setState(PICKED_UP, pickupTime);

                pickedUpOrders.add(order);

                order_it.remove();
                courier_it.remove();
                break;
            }
        }

        return pickedUpOrders;
    }
}

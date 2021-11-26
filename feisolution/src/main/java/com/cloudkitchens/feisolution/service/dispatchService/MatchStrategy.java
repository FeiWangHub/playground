package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.OrderModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.cloudkitchens.feisolution.model.CourierState.ARRIVED_KITCHEN;
import static com.cloudkitchens.feisolution.model.CourierState.PICKED_UP_ORDER;
import static com.cloudkitchens.feisolution.model.OrderState.PICKED_UP;
import static com.cloudkitchens.feisolution.model.OrderState.READY;

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
        ArrayList<OrderModel> pickedUpOrders = new ArrayList<>();
        Date now = new Date();

        Iterator<CourierModel> courier_it = this.couriersQueue.iterator();


        while(courier_it.hasNext() && !this.ordersQueue.isEmpty()){
            CourierModel courier = courier_it.next();
            if(courier.getState() != ARRIVED_KITCHEN){
                continue;
            }

            //try find the matched and ready order
            Iterator<OrderModel> order_it = this.ordersQueue.iterator();
            while (order_it.hasNext()){
                OrderModel order = order_it.next();
                if(order.getState() != READY || !order.getCourierId().equals(courier.getId())){
                    continue;
                }

                courier.setState(PICKED_UP_ORDER, now);
                order.setState(PICKED_UP, now);
                order.setCourierWaitTime(courier.calWaitingTime());

                pickedUpOrders.add(order);

                this.ordersQueue.remove(order);
                courier_it.remove();
                break;
            }

        }

        return pickedUpOrders;
    }
}

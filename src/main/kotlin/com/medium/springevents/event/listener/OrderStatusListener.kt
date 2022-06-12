package com.medium.springevents.event.listener

import com.medium.springevents.entity.Order
import com.medium.springevents.service.OrderService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class OrderStatusListener(
    val orderService: OrderService,
) {
    @EventListener
    fun updateOrderStatus(order: Order) {
        val newStatus = orderService.updateStatus(order)
        orderService.save(order.copy(status = newStatus))
    }
}

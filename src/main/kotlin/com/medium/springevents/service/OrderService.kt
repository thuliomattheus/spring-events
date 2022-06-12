package com.medium.springevents.service

import com.medium.springevents.entity.Order
import com.medium.springevents.entity.OrderStatus
import com.medium.springevents.repository.OrderRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class OrderService(
    val orderRepository: OrderRepository,
) {
    fun save(order: Order) = order.apply {
        System.err.println("Order: $order")
        if(order.status != OrderStatus.DELIVERED)
            registerEvent()

        orderRepository.save(this)
    }

    fun updateStatus(order: Order) : OrderStatus =
        when(order.status) {
            OrderStatus.CREATED -> OrderStatus.VALIDATING
            OrderStatus.VALIDATING ->
                if(order.price > BigDecimal.ZERO) OrderStatus.AWAITING_PAYMENT
                else throw RuntimeException("Invalid value for parameter price")
            OrderStatus.AWAITING_PAYMENT -> OrderStatus.PAID
            OrderStatus.PAID -> OrderStatus.IN_ROUTE
            OrderStatus.IN_ROUTE -> OrderStatus.DELIVERED
            else -> throw RuntimeException("Invalid parameter status")
        }
}

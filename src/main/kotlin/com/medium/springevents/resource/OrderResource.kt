package com.medium.springevents.resource

import com.medium.springevents.entity.Order
import com.medium.springevents.service.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderResource(
    val orderService: OrderService,
) {

    @PostMapping
    fun createOrder(@RequestBody order: Order) = orderService.save(order)
}

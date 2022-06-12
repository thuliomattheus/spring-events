package com.medium.springevents.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.domain.AbstractAggregateRoot
import java.math.BigDecimal
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "Pedido")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @CreatedDate val createdAt: Instant = Instant.now(),
    @Enumerated(EnumType.STRING) val status: OrderStatus = OrderStatus.CREATED,
    val price: BigDecimal,
): AbstractAggregateRoot<Order>() {
    fun registerEvent() = super.registerEvent(this)
}

enum class OrderStatus {
    CREATED,
    VALIDATING,
    AWAITING_PAYMENT,
    PAID,
    IN_ROUTE,
    DELIVERED,
}

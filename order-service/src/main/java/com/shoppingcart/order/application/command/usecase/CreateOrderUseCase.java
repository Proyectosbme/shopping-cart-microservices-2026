package com.shoppingcart.order.application.command.usecase;

import java.math.BigDecimal;
import java.util.List;

import com.shoppingcart.order.application.command.dto.CreateOrderCommand;
import com.shoppingcart.order.application.command.port.output.OrderCommandRepository;
import com.shoppingcart.order.application.command.port.output.ProductValidationPort;
import com.shoppingcart.order.domain.entity.Customer;
import com.shoppingcart.order.domain.entity.Order;
import com.shoppingcart.order.domain.entity.OrderDetail;
import com.shoppingcart.order.domain.exceptions.PriceMismatchException;
import com.shoppingcart.order.domain.vo.Quantity;

public class CreateOrderUseCase {

    private final OrderCommandRepository orderCommandRepository;
    private final ProductValidationPort productValidationPort;

    public CreateOrderUseCase(OrderCommandRepository orderCommandRepository,
            ProductValidationPort productValidationPort) {
        this.orderCommandRepository = orderCommandRepository;
        this.productValidationPort = productValidationPort;
    }

    public Order execute(CreateOrderCommand command) {
        Customer customer = Customer.reconstitute(
                command.customerId(),
                command.customerName(),
                command.customerEmail());

        List<OrderDetail> details = command.details().stream()
                .map(d -> {
                    Double actualPrice = productValidationPort.getProductPrice(d.productId());
                    if (Double.compare(actualPrice, d.unitPrice()) != 0)
                        throw new PriceMismatchException(d.productId(), d.unitPrice(), actualPrice);
                    return OrderDetail.create(
                            d.productId(),
                            d.productName(),
                            new Quantity(d.quantity()),
                            BigDecimal.valueOf(d.unitPrice()));
                })
                .toList();

        return orderCommandRepository.save(Order.create(customer, details));
    }

}

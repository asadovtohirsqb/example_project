package uz.sqb.example_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sqb.example_project.entity.Order;
import uz.sqb.example_project.payload.OrderCreator;
import uz.sqb.example_project.payload.OrderResponse;
import uz.sqb.example_project.repository.OrderRepository;
import uz.sqb.example_project.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderResponse create(OrderCreator creator) {
        Order save = orderRepository.save(Order.builder()
                .name(creator.name())
                .amount(creator.amount())
                .build());
        return new OrderResponse(save.getId(),save.getName(), save.getAmount());
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getName(),
                        order.getAmount()
                ))
                .toList();
    }
}

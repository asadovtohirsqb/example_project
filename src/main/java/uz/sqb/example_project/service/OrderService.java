package uz.sqb.example_project.service;

import uz.sqb.example_project.payload.OrderCreator;
import uz.sqb.example_project.payload.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse create(OrderCreator creator);

    List<OrderResponse> findAll();
}

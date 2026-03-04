package uz.sqb.example_project.service;

import uz.sqb.example_project.payload.SherdorOrderCreator;
import uz.sqb.example_project.payload.SherdorOrderResponse;

import java.util.List;

public interface SherdorOrderService {
    SherdorOrderResponse create(SherdorOrderCreator creator);

    List<SherdorOrderResponse> findAll();
}

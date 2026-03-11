package uz.sqb.example_project.service;

import uz.sqb.example_project.payload.ReissueOrderCreator;
import uz.sqb.example_project.payload.ReissueOrderResponse;

import java.util.List;

public interface ReissueOrderService {
    ReissueOrderResponse create(ReissueOrderCreator creator);

    ReissueOrderResponse findById(Integer id);

    List<ReissueOrderResponse> findAll();

}

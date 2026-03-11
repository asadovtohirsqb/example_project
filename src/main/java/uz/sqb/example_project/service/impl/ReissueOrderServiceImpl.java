package uz.sqb.example_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sqb.example_project.entity.Order;
import uz.sqb.example_project.entity.ReissueOrder;
import uz.sqb.example_project.payload.ReissueOrderCreator;
import uz.sqb.example_project.payload.ReissueOrderResponse;
import uz.sqb.example_project.repository.OrderRepository;
import uz.sqb.example_project.repository.ReissueOrderRepository;
import uz.sqb.example_project.service.ReissueOrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReissueOrderServiceImpl implements ReissueOrderService {
    private final ReissueOrderRepository reissueOrderRepository;
    private final OrderRepository orderRepository;


    @Override
    public ReissueOrderResponse create(ReissueOrderCreator creator) {
        Order order = orderRepository.save(Order.builder()
                .name(creator.name())
                .amount(creator.amount())
                .build());
        ReissueOrder reissueOrder = new ReissueOrder();
        reissueOrder.setOrder(order);
        reissueOrder.setCardNumber(creator.cardNumber());
        reissueOrder.setCardCode(creator.cardCode());

        ReissueOrder saved = reissueOrderRepository.save(reissueOrder);
        return new ReissueOrderResponse(saved.getId(), order.getName(), order.getAmount(), saved.getCardNumber(), saved.getCardCode());
    }

    @Override
    public ReissueOrderResponse findById(Integer id) {
        return reissueOrderRepository.findById(id)
                .map(reissueOrder -> new ReissueOrderResponse(
                        reissueOrder.getId(),
                        reissueOrder.getOrder().getName(),
                        reissueOrder.getOrder().getAmount(),
                        reissueOrder.getCardNumber(),
                        reissueOrder.getCardCode()
                ))
                .orElse(null);
    }

    @Override
    public List<ReissueOrderResponse> findAll() {
        return reissueOrderRepository.findAll().stream()
                .map(reissueOrder -> new ReissueOrderResponse(
                                reissueOrder.getId(),
                                reissueOrder.getOrder().getName(),
                                reissueOrder.getOrder().getAmount(),
                                reissueOrder.getCardNumber(),
                                reissueOrder.getCardCode()
                        )
                ).toList();
    }
}

package uz.sqb.example_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sqb.example_project.entity.SherdorOrder;
import uz.sqb.example_project.payload.SherdorOrderCreator;
import uz.sqb.example_project.payload.SherdorOrderResponse;
import uz.sqb.example_project.repository.SherdorOrderRepository;
import uz.sqb.example_project.service.SherdorOrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SherdorOrderServiceImpl implements SherdorOrderService {
    private final SherdorOrderRepository sherdorOrderRepository;

    @Override
    public SherdorOrderResponse create(SherdorOrderCreator creator) {
        SherdorOrder entity = SherdorOrder.builder()
                .name(creator.name())
                .amount(creator.amount())
                .tariffType(creator.tariffType())
                .cardNumber(creator.cardNumber())
                .build();

        SherdorOrder saved = sherdorOrderRepository.save(entity);

        return new SherdorOrderResponse(
                saved.getId(),
                saved.getName(),
                saved.getAmount(),
                saved.getTariffType(),
                saved.getCardNumber()
        );
    }

    @Override
    public List<SherdorOrderResponse> findAll() {
        return sherdorOrderRepository.findAll()
                .stream().map(sherdorOrder ->
                        new SherdorOrderResponse(
                                sherdorOrder.getId(),
                                sherdorOrder.getName(),
                                sherdorOrder.getAmount(),
                                sherdorOrder.getTariffType(),
                                sherdorOrder.getCardNumber())
                ).toList();
    }
}

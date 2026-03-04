package uz.sqb.example_project.payload;

public record SherdorOrderResponse(
        Integer id,
        String name,
        Long amount,
        String tariffType,
        String cardNumber
) {
}

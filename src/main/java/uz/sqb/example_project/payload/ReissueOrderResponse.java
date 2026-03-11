package uz.sqb.example_project.payload;

public record ReissueOrderResponse(
        Integer id,
        String name,
        Long amount,
        String cardNumber,
        Integer cardCode
) {
}

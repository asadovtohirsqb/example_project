package uz.sqb.example_project.payload;

public record ReissueOrderCreator(
        String name,
        Long amount,
        String cardNumber,
        Integer cardCode
) {
}

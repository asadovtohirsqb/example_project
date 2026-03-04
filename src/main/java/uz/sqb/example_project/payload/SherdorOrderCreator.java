package uz.sqb.example_project.payload;

public record SherdorOrderCreator(
        String name,
        Long amount,
        String tariffType,
        String cardNumber
) {
}

package uz.sqb.example_project.payload;

public record OrderResponse(
        Integer id,
        String name,
        Long amount
) {
}

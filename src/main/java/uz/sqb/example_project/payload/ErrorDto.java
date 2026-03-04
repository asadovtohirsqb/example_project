package uz.sqb.example_project.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Xato holatlarida batafsil ma'lumotlar beruvchi ob'ekt.
 * BaseResponse ichidagi "error" maydonida ishlatiladi.
 */
@Getter
@Setter
@ToString
@Builder
@Schema(
        name = "ErrorDto",
        title = "Error Details",
        description = "Xato holatida qaytariladigan batafsil ma'lumotlar. " +
                "Production muhitida odatda minimal yoki bo'sh bo'ladi."
)
public class ErrorDto {

    @Schema(
            description = "Xato kodi (raqamli identifikator)",
            example = "4001",
            nullable = true
    )
    @JsonProperty("error_code")
    private Integer errorCode;

    @Schema(
            description = "Xato yuz bergan endpoint yoki yo'l (masalan: /api/orders/create)",
            example = "/sherdor-orders/create",
            nullable = true
    )
    @JsonProperty("error_path")
    private String errorPath;

    @Schema(
            description = "Xato haqida qo'shimcha ma'lumot (masalan validatsiya xatolari, rejected qiymatlar yoki ob'ekt)",
            example = "{ \"field\": \"cardNumber\", \"rejectedValue\": \"123\", \"message\": \"Invalid format\" }",
            nullable = true,
            implementation = Object.class  // har qanday ob'ekt bo'lishi mumkin
    )
    @JsonProperty("error_body")
    private Object errorBody;

    @Schema(
            description = "Xato yuz bergan vaqt (ISO formatida)",
            example = "2026-03-04T16:45:23.123",
            nullable = false
    )
    @Builder.Default
    @JsonProperty("timestamp")
    private LocalDateTime timestamp = LocalDateTime.now();
}
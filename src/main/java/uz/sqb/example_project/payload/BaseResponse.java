package uz.sqb.example_project.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Umumiy API javob strukturasini ta'minlovchi generic class.
 * Har qanday API dan qaytariladigan javob shu formatda bo'ladi.
 *
 * @param <T> Qaytariladigan ma'lumot turi (masalan: OrderResponse, List<OrderResponse>, etc.)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standart API javob obyekti. Har qanday API dan qaytariladigan ma'lumot shu shaklda bo'ladi.")
public class BaseResponse<T> {

    @Schema(description = "Operatsiya muvaffaqiyatli bo'lganligini bildiradi", example = "true")
    private boolean success;

    @Schema(description = "Muvaffaqiyatli bo'lsa qaytariladigan asosiy ma'lumot. Xato bo'lsa null bo'ladi",
            nullable = true,
            oneOf = {Object.class}) // generic bo'lgani uchun oneOf ishlatiladi
    private T data;

    @Schema(description = "Foydalanuvchiga ko'rsatiladigan xabar (muvaffaqiyat yoki xato haqida)",
            example = "Buyurtma muvaffaqiyatli yaratildi")
    private String message;

    @Schema(description = "Xato holatida qo'shimcha ma'lumot (masalan error code yoki qisqa tafsilot). " +
            "Production muhitda odatda bo'sh yoki minimal bo'ladi",
            example = "Validation failed: name may not be blank",
            nullable = true)
    private String errorDetails;

    // Statik factory metodlar (Swagger uchun o'zgartirish shart emas, lekin saqlaymiz)

    public static <T> BaseResponse<T> success(T data, String message) {
        return BaseResponse.<T>builder()
                .success(true)
                .data(data)
                .message(message != null ? message : "Muvaffaqiyatli bajarildi")
                .build();
    }

    public static <T> BaseResponse<T> success(T data) {
        return success(data, null);
    }

    public static <T> BaseResponse<T> error(String message) {
        return BaseResponse.<T>builder()
                .success(false)
                .data(null)
                .message(message)
                .build();
    }

    public static <T> BaseResponse<T> error(String message, String errorDetails) {
        return BaseResponse.<T>builder()
                .success(false)
                .data(null)
                .message(message)
                .errorDetails(errorDetails)
                .build();
    }
}
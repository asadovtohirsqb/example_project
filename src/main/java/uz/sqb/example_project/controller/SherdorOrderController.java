package uz.sqb.example_project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sqb.example_project.payload.BaseResponse;
import uz.sqb.example_project.payload.SherdorOrderCreator;
import uz.sqb.example_project.payload.SherdorOrderResponse;
import uz.sqb.example_project.service.SherdorOrderService;
import uz.sqb.example_project.utils.BaseUrls;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(SherdorOrderController.BASE_URL)
@Tag(
        name = "Sherdor Order API",
        description = "Sherdor maxsus buyurtmalari bilan ishlash uchun CRUD operatsiyalari. " +
                "Har bir API dan qaytadigan javob BaseResponse formatida bo'ladi."
)
public class SherdorOrderController {

    public static final String BASE_URL = BaseUrls.BASE_URL + "/sherdor-orders";

    private final SherdorOrderService sherdorOrderService;

    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Yangi Sherdor maxsus buyurtma yaratish",
            description = "SherdorOrder turidagi yangi buyurtma yaratadi. " +
                    "Bu API oddiy Order dan farqli o'laroq maxsus maydonlarni (tariffType, cardNumber) qabul qiladi."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Sherdor buyurtma muvaffaqiyatli yaratildi",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BaseResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Noto'g'ri so'rov (masalan, majburiy maydonlar to'ldirilmagan, validatsiya xatosi yoki cardNumber noto'g'ri formatda)",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server ichki xatosi",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))
            )
    })
    public ResponseEntity<BaseResponse<SherdorOrderResponse>> create(
            @RequestBody SherdorOrderCreator creator
    ) {
        SherdorOrderResponse response = sherdorOrderService.create(creator);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(response, "Sherdor buyurtma muvaffaqiyatli yaratildi"));
    }

    @GetMapping(
            path = "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Barcha Sherdor buyurtmalarini olish",
            description = "Tizimdagi barcha SherdorOrder turidagi buyurtmalarni ro'yxat sifatida qaytaradi. " +
                    "Bu API faqat Sherdor maxsus buyurtmalarini qaytaradi (oddiy Order'larni emas)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sherdor buyurtmalar ro'yxati muvaffaqiyatli qaytarildi",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BaseResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server ichki xatosi",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))
            )
    })
    public ResponseEntity<BaseResponse<List<SherdorOrderResponse>>> findAll() {
        List<SherdorOrderResponse> list = sherdorOrderService.findAll();
        return ResponseEntity.ok(
                BaseResponse.success(list, "Barcha Sherdor buyurtmalari muvaffaqiyatli olindi")
        );
    }
}
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
import uz.sqb.example_project.payload.OrderCreator;
import uz.sqb.example_project.payload.OrderResponse;
import uz.sqb.example_project.service.OrderService;
import uz.sqb.example_project.utils.BaseUrls;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(OrderController.BASE_URL)
@Tag(
        name = "Order API",
        description = "Buyurtmalar (Orders) bilan ishlash uchun asosiy CRUD operatsiyalari. " +
                "Har bir API dan qaytadigan javob ApiResponse formatida bo'ladi."
)
public class OrderController {

    public static final String BASE_URL = BaseUrls.BASE_URL + "/orders";

    private final OrderService orderService;

    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Yangi buyurtma yaratish",
            description = "Yangi oddiy buyurtma (Order) yaratadi. Sherdor maxsus buyurtma uchun alohida API mavjud."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Buyurtma muvaffaqiyatli yaratildi",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Noto'g'ri so'rov (masalan, majburiy maydonlar to'ldirilmagan yoki validatsiya xatosi)",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server ichki xatosi",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    public ResponseEntity<BaseResponse<OrderResponse>> create(
            @RequestBody OrderCreator creator
    ) {
        OrderResponse orderResponse = orderService.create(creator);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success(orderResponse, "Buyurtma muvaffaqiyatli yaratildi"));
    }

    @GetMapping(
            path = "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Barcha buyurtmalarni olish",
            description = "Tizimdagi barcha buyurtmalarni (Order va uning vorislari) ro'yxat sifatida qaytaradi"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Buyurtmalar ro'yxati muvaffaqiyatli qaytarildi",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server ichki xatosi",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    public ResponseEntity<BaseResponse<List<OrderResponse>>> findAll() {
        List<OrderResponse> list = orderService.findAll();
        return ResponseEntity.ok(
                BaseResponse.success(list, "Barcha buyurtmalar muvaffaqiyatli olindi")
        );
    }
}
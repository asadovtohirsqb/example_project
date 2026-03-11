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
import uz.sqb.example_project.payload.ReissueOrderCreator;
import uz.sqb.example_project.payload.ReissueOrderResponse;
import uz.sqb.example_project.service.ReissueOrderService;
import uz.sqb.example_project.utils.BaseUrls;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ReissueOrderController.BASE_URL)
@Tag(
        name = "Reissue Order API",
        description = "Reissue order lar bilan ishlash uchun CRUD API lar"
)
public class ReissueOrderController {

    public static final String BASE_URL = BaseUrls.BASE_URL + "/reissue-orders";

    private final ReissueOrderService reissueOrderService;

    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Yangi Reissue order yaratish",
            description = "Reissue turidagi yangi buyurtma yaratadi"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Reissue order muvaffaqiyatli yaratildi",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BaseResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Noto'g'ri so'rov",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server xatosi",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))
            )
    })
    public ResponseEntity<BaseResponse<ReissueOrderResponse>> create(
            @RequestBody ReissueOrderCreator creator
    ) {
        ReissueOrderResponse response = reissueOrderService.create(creator);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(response, "Reissue order muvaffaqiyatli yaratildi"));
    }


    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "ID orqali Reissue order olish"
    )
    public ResponseEntity<BaseResponse<ReissueOrderResponse>> findById(
            @PathVariable Integer id
    ) {
        ReissueOrderResponse response = reissueOrderService.findById(id);

        return ResponseEntity.ok(
                BaseResponse.success(response, "Reissue order muvaffaqiyatli topildi")
        );
    }


    @GetMapping(
            path = "/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Barcha Reissue order larni olish"
    )
    public ResponseEntity<BaseResponse<List<ReissueOrderResponse>>> findAll() {

        List<ReissueOrderResponse> list = reissueOrderService.findAll();

        return ResponseEntity.ok(
                BaseResponse.success(list, "Barcha Reissue order lar muvaffaqiyatli olindi")
        );
    }
}

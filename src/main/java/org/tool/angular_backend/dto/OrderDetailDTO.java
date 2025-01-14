package org.tool.angular_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1, message = "Order's ID must be > 0")
    private Long orderId;
    @JsonProperty("product_id")
    @Min(value = 1, message = "Product's ID must be > 0")
    private Long productId;
    @JsonProperty("price")
    @Min(value = 0, message = "Pirce must be > 0")
    private Float price;
    @JsonProperty("number_of_products")
    @Min(value = 0, message = "Number Of Products must be >= 1")
    private Integer numberOfProducts;
    @JsonProperty("total_money")
    @Min(value = 0, message = "Total Money must be >= 1")
    private Integer totalMoney;
    @JsonProperty("color")
    private String color;

}

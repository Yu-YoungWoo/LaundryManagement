package project.laundry.dto.post_dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import project.laundry.entity.status.ClothStatus;

import java.time.LocalDateTime;

@Getter
public class PostAddDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String phone;

    @NotEmpty @PositiveOrZero
    @Size(min = 0, max = 100000)
    private String clothCount;

    @NotEmpty @PositiveOrZero
    private int price;

    @NotNull
    @Size(min = 0, max = 999)
    private String content;
}

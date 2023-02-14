package project.laundry.dto.post_dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class postDto {


    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$" ,message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
    private String phone;

    @NotNull @PositiveOrZero
    private int clothCount;

    @NotNull @PositiveOrZero
    private int price;

    @NotNull
    @Size(min = 0, max = 999)
    private String content;

    private String createTime;
}

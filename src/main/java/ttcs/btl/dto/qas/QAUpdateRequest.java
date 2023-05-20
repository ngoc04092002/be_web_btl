package ttcs.btl.dto.qas;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QAUpdateRequest {

    @NotNull private Long id;
    @NotNull private Boolean report;
}

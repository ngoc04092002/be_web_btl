package ttcs.btl.dto.clients;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateClientRequest {

    private String username;
    @NotNull
    private String email;

    private String address;
    private String sdt;
    private String avatar;
    private String gender;
}

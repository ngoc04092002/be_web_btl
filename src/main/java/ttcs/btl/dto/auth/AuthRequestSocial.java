package ttcs.btl.dto.auth;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequestSocial {
    @NotNull String email;
    @NotNull String username;
    String sdt;
    String avatar;
}

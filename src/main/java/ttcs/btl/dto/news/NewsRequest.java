package ttcs.btl.dto.news;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequest {

    @NotNull
    String img;
    @NotNull
    String title;
    @NotNull
    String des;
    @NotNull
    String posterName;
    @NotNull
    List<String> likes;
}

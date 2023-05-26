package ttcs.btl.model.paths;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "paths")
public class PathsModal {
    @Id
    private String id;
    private String path;
}


package ttcs.btl.model.client;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "adminPath")
public class AccessAdminPaths {
    @Id
    private String id;
    private String path;
}

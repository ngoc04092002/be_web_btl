package ttcs.btl.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TestMongo {

    @Id
    private String id;
    private String name;
}

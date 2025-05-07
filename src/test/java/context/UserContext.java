package context;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserContext {
    private String userName;
    private String password;
    private String userId;
    private String token;
}

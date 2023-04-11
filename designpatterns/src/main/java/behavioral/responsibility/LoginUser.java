package behavioral.responsibility;

import lombok.Data;

@Data
public class LoginUser {

    private String loginName;
    private String password;
    private String roleName;
    private String permission;

    public LoginUser(String loginName, String password, String roleName, String permission) {
        this.loginName = loginName;
        this.password = password;
        this.roleName = roleName;
        this.permission = permission;
    }
}

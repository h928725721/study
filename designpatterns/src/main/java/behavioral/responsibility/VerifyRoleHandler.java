package behavioral.responsibility;

import org.apache.commons.lang3.StringUtils;

public class VerifyRoleHandler extends BuilderHandler<LoginUser>{

    @Override
    public void doHandler(LoginUser user) {
        if (!StringUtils.equals("admin", user.getRoleName())) {
            System.out.println("角色信息有误！");
            return;
        }
        System.out.println("角色校验通过");
        next.doHandler(user);
    }
}

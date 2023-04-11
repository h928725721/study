package behavioral.responsibility;

import org.apache.commons.lang3.StringUtils;

public class VerifyPermissionHandler extends BuilderHandler<LoginUser>{



    @Override
    public void doHandler(LoginUser user) {
        if (!StringUtils.equals("admin", user.getLoginName())) {
            System.out.println("暂无权限");
            return;
        }
        System.out.println("权限校验通过,登陆成功");
    }
}

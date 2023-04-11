package behavioral.responsibility;

import org.apache.commons.lang3.StringUtils;

public class VerifyAccountHandler extends BuilderHandler<LoginUser>{



    @Override
    public void doHandler(LoginUser user) {
        if (StringUtils.isBlank(user.getLoginName())){
            System.out.println("用户名不能为空");
            return;
        }
        if (StringUtils.isBlank(user.getPassword())){
            System.out.println("密码不能为空");
            return;
        }
        if (!StringUtils.equals(user.getPassword(),"123456")){
            System.out.println("密码不正确");
            return;
        }
        System.out.println("密码校验通过");
        //用来调用链路中下一个节点
        next.doHandler(user);
    }
}

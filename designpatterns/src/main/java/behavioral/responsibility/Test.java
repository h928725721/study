package behavioral.responsibility;

import lombok.extern.java.Log;

public class Test {

    public static void main(String[] args) {

        LoginUser loginUser = new LoginUser("admin","123456","admin","admin");
        BuilderHandler.Builder<LoginUser> builder = new BuilderHandler.Builder<LoginUser>();
        BuilderHandler.Builder<LoginUser> handler = builder.addHandler(new VerifyAccountHandler())
                .addHandler(new VerifyRoleHandler())
                .addHandler(new VerifyPermissionHandler());
        BuilderHandler<LoginUser> build = handler.build();
        build.doHandler(loginUser);

    }



}

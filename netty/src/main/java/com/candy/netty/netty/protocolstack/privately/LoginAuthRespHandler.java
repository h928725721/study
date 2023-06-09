package com.candy.netty.netty.protocolstack.privately;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    /**
     * 重复登录保护
     */
    private final Map<String, Boolean> nodeCheck = new ConcurrentHashMap<>();

    /**
     * 创建白名单，默认释放本机的
     */
    private final List<String> whiteList = Arrays.asList("127.0.0.1", "192.168.8.106");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyProtocolMessage message = (NettyProtocolMessage) msg;
        Header header = message.getHeader();
        if (header != null && header.getType() == MessageType.LOGIN_REQ.value()) {
            NettyProtocolMessage loginResp = null;
            String nodeIndex = ctx.channel().remoteAddress().toString();
            //判断是否已经重复登录了节点，如果登录了直接构建一个拒绝的请求返回
            if (nodeCheck.containsKey(nodeIndex)) {
                //构建一个响应
                loginResp = buildResponse((byte) -1);
            } else {
                //获取到远程地址
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                //如果存在白名单中，构建一个返回为0的响应对象
                boolean isOk = whiteList.stream().anyMatch(wip -> wip.equals(ip));
                loginResp = isOk ? buildResponse((byte) 0) : buildResponse((byte) -1);
                if (isOk) {
                    nodeCheck.put(nodeIndex, true);
                }
            }
            System.out.println("The login response is :" + loginResp + "body [" + loginResp.getBody() + "]");
            ctx.writeAndFlush(loginResp);
        } else {
            //释放后续进行处理
            ctx.fireChannelRead(msg);
        }
    }

    /**
     * 服务断开连接或者异常时断开连接
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String nodeIndex = ctx.channel().remoteAddress().toString();
        this.nodeCheck.remove(nodeIndex);
        ctx.fireExceptionCaught(cause);
    }

    /**
     * 构建一个响应对象
     *
     * @param status
     * @return
     */
    private NettyProtocolMessage buildResponse(byte status) {
        Header header = Header.builder().type(MessageType.LOGIN_RESP.value()).build();
        return NettyProtocolMessage.builder().header(header).body(status).build();
    }
}

package behavioral.responsibility;

import java.util.Objects;

public abstract class BuilderHandler<T> {

    protected BuilderHandler<T> next;

    public void next(BuilderHandler<T> next) {
        this.next = next;
    }

    public abstract void doHandler(LoginUser user);

    /**
     * 匿名内部类，构造者模式的应用
     * @param <T>
     */
    public static class Builder<T> {
        private BuilderHandler<T> head;
        private BuilderHandler<T> tail;

        public Builder<T> addHandler(BuilderHandler<T> handler) {
            //第一次添加到队列的情况
            if (Objects.isNull(head)) {
                head = this.tail = handler;
                return this;
            }
            //原tail节点指向新添加进来的节点
            this.tail.next(handler);
            //新添加进来的节点设为tail节点
            this.tail = handler;
            return this;
        }

        public BuilderHandler<T> build() {
            return this.head;
        }

    }


}

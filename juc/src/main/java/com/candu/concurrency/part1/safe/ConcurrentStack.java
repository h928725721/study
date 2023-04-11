package com.candu.concurrency.part1.safe;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack<E> {
    AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            //新节点的next指向当前栈顶
            newHead.next = oldHead;
            //使用CAS把新节点放到栈顶
            //如果开始插入节点前，栈顶没有发生变化，CAS就会成功更新栈顶
            //如果栈顶发生变化（被其他线程修改），CAS会失败，并根据新的栈状态来更新节点
        } while (top.compareAndSet(oldHead,newHead));
    }

    private static class Node<E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}

package com.candu.concurrency.part1.safe;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BoundedBufferTest {
    @Test
    void testIsEmptyWhenConstructed() {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(10);
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }

    @Test
    void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread(() -> {
            try {
                int unused = bb.take();
                //如果执行到这里，表明出现了一个错误
                fail();
            } catch (InterruptedException ignored) {
            }
        });
        try {
            taker.start();
            Thread.sleep(10);
            taker.interrupt();
            taker.join(10);
            assertFalse(taker.isAlive());
        } catch (Exception e) {
            fail();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        boolean b = containsDuplicate(nums);
        System.out.println(b);

    }
    public static boolean containsDuplicate(int[] nums) {
        int[] newInts = Arrays.stream(nums)
                .distinct()
                .toArray();
        return nums.length != newInts.length;

    }
}
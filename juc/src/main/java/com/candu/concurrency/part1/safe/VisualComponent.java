package com.candu.concurrency.part1.safe;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class VisualComponent {
    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
    }

    public void addMouseListener(MouseListener mouseListener) {
        mouseListeners.add(mouseListener);
    }

    public void removeKeyListener(KeyListener keyListener) {
        keyListeners.remove(keyListener);
    }

    public void removeMouseListener(MouseListener mouseListener) {
        mouseListeners.remove(mouseListener);
    }
}

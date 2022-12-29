package dev.tahkeer.tadmer.utils.interfaces;

import java.util.EventListener;

public interface ShapesEventListener extends EventListener {
    void collected();
    void bombCaught();
}


package dev.tahkeer.tadmer.model.interfaces;

import java.util.EventListener;

public interface ModelEventListner extends EventListener {
    void createdModel(Model model);
}


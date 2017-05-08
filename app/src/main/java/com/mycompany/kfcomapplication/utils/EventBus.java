package com.mycompany.kfcomapplication.utils;

import com.squareup.otto.Bus;

/**
 * Created by jason_000 on 11/29/2016.
 */

public class EventBus extends Bus {
    private static final EventBus bus = new EventBus();

    public static Bus getInstance() { return bus; }

    private EventBus() {}
}

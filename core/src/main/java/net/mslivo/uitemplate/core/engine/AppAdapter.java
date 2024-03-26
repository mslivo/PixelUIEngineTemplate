package net.mslivo.uitemplate.core.engine;

import net.mslivo.core.engine.tools.engine.AppEngineAdapter;
import net.mslivo.core.engine.tools.engine.Output;
import net.mslivo.uitemplate.core.data.Data;

public class AppAdapter implements AppEngineAdapter<Data> {

    private Data data;
    private Output output;
    @Override
    public void init(Data data, Output output) {
        this.data = data;
        this.output = output;
    }

    @Override
    public void update() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void processInput(int i, Object[] objects) {

    }
}

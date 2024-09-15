package net.mslivo.uitemplate.core.engine;


import net.mslivo.core.engine.tools.appengine.AppEngineAdapter;
import net.mslivo.core.engine.tools.appengine.AppEngineIO;
import net.mslivo.core.engine.tools.appengine.AppEngineOutputQueue;
import net.mslivo.uitemplate.core.data.Data;

public class AppAdapter implements AppEngineAdapter<Data> {

    private Data data;
    private AppEngineOutputQueue output;
    @Override
    public void init(Data data, AppEngineOutputQueue output) {
        this.data = data;
        this.output = output;
    }

    @Override
    public void processInput(AppEngineIO appEngineIO) {

    }

    @Override
    public void update() {

    }

    @Override
    public void shutdown() {

    }

}

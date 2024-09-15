package net.mslivo.uitemplate.core.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import net.mslivo.core.engine.media_manager.MediaManager;
import net.mslivo.core.engine.tools.appengine.AppEngine;
import net.mslivo.core.engine.tools.appengine.AppEngineIO;
import net.mslivo.core.engine.ui_engine.API;
import net.mslivo.core.engine.ui_engine.UIEngineAdapter;
import net.mslivo.core.engine.ui_engine.rendering.PrimitiveRenderer;
import net.mslivo.core.engine.ui_engine.rendering.SpriteRenderer;
import net.mslivo.core.engine.ui_engine.ui.components.viewport.AppViewport;
import net.mslivo.uitemplate.core.data.Data;
import net.mslivo.uitemplate.core.engine.AppAdapter;

public class UIAdapter implements UIEngineAdapter {
    private AppEngine<AppAdapter, Data> appEngine;
    private Data data;
    private API api;
    private MediaManager mediaManager;

    public UIAdapter(AppEngine<AppAdapter, Data> appEngine) {
        this.appEngine = appEngine;
        this.data = appEngine.getData();
    }


    @Override
    public void init(API api, MediaManager mediaManager) {
        this.api = api;
        this.mediaManager = mediaManager;
    }

    @Override
    public void update() {
        // Process Engine output
        processOutput();
        // Create Input
        //appEngine.input();
    }



    private void processOutput() {
        while (appEngine.outputAvailable()) {
            AppEngineIO output = appEngine.processOutput();
        }
    }

    @Override
    public void render(OrthographicCamera orthographicCamera, AppViewport appViewport) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


    }

    @Override
    public void shutdown() {

    }
}

package net.mslivo.uitemplate.core.cfg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import net.mslivo.core.engine.media_manager.MediaManager;
import net.mslivo.core.engine.tools.engine.AppEngine;
import net.mslivo.core.engine.ui_engine.API;
import net.mslivo.core.engine.ui_engine.UIEngineAdapter;
import net.mslivo.core.engine.ui_engine.render.ImmediateRenderer;
import net.mslivo.core.engine.ui_engine.render.SpriteRenderer;
import net.mslivo.core.engine.ui_engine.ui.components.viewport.AppViewPort;
import net.mslivo.uitemplate.core.cfg.data.Data;
import net.mslivo.uitemplate.core.cfg.engine.AppAdapter;

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
        while (appEngine.nextOutput()) {
            int type = appEngine.getOutputType();
            Object[] params = appEngine.getOutputParams();
        }
    }

    @Override
    public void render(SpriteRenderer spriteRenderer, ImmediateRenderer immediateRenderer, AppViewPort appViewPort) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        immediateRenderer.begin();

        float r = 0,g = 0,b = 0;
        for (int ix = 0; ix < api.resolutionWidth(); ix++) {
            for (int iy = 0; iy < api.resolutionHeight(); iy++) {
                r = ix/(float)api.resolutionWidth();
                g = iy/(float)api.resolutionHeight();
                immediateRenderer.setColor(r,g,b);
                immediateRenderer.vertex(ix,iy);
            }
        }
        immediateRenderer.end();


    }

    @Override
    public void shutdown() {

    }
}

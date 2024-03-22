package net.mslivo.uitemplate.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import net.mslivo.core.engine.media_manager.MediaManager;
import net.mslivo.core.engine.tools.Tools;
import net.mslivo.core.engine.tools.engine.AppEngine;
import net.mslivo.core.engine.ui_engine.UIEngine;
import net.mslivo.uitemplate.core.cfg.CFG_MAIN;
import net.mslivo.uitemplate.core.cfg.data.Data;
import net.mslivo.uitemplate.core.cfg.data.DataGenerator;
import net.mslivo.uitemplate.core.cfg.engine.AppAdapter;
import net.mslivo.uitemplate.core.cfg.ui.UIAdapter;
import net.mslivo.uitemplate.core.cfg.ui.media.BaseMedia;

public class UITemplateMain extends ApplicationAdapter {
    private MediaManager mediaManager;
    private Data data;
    private UIEngine<UIAdapter> uiEngine;
    private AppEngine appEngine;
    private long timer_debug_info;

    public UITemplateMain() {

    }

    @Override
    public void resize(int width, int height) {
        if (this.uiEngine != null) this.uiEngine.resize(width, height);
    }

    @Override
    public void create() {
        Tools.App.setTargetUpdates(CFG_MAIN.TARGET_FPS);
        // MediaManager
        Tools.Log.inProgress("Loading Assets");
        this.mediaManager = new MediaManager();
        this.mediaManager.prepareUICMedia();
        this.mediaManager.prepareCMedia(BaseMedia.ALL);
        this.mediaManager.loadAssets();
        Tools.Log.done();
        // App Engine
        Tools.Log.inProgress("Loading Engine");
        this.data = DataGenerator.createData();
        this.appEngine = new AppEngine<>(new AppAdapter(), this.data);
        Tools.Log.done();
        // UI Engine
        Tools.Log.inProgress("Loading UI");
        this.uiEngine = new UIEngine<>(new UIAdapter(this.appEngine), this.mediaManager, CFG_MAIN.RESOLUTION_WIDTH, CFG_MAIN.RESOLUTION_HEIGHT);
        Tools.Log.done();
    }

    @Override
    public void render() {
        if (Tools.App.isRunUpdate()) {
            // Update
            this.uiEngine.update();
            this.appEngine.update();
        }

        // Render
        this.uiEngine.render();

        // Log Benchmark
        if (System.currentTimeMillis() - timer_debug_info > 5000) {
            Tools.Log.benchmark();
            timer_debug_info = System.currentTimeMillis();
        }
    }

    @Override
    public void dispose() {
        Tools.Log.inProgress("Shutting down...");
        this.uiEngine.shutdown();
        this.appEngine.shutdown();
        this.mediaManager.shutdown();
        Tools.Log.done();
        Gdx.app.exit();
        System.exit(0);
    }


}

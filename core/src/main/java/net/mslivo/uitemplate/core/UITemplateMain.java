package net.mslivo.uitemplate.core;

import com.badlogic.gdx.ApplicationAdapter;
import net.mslivo.core.engine.media_manager.MediaManager;
import net.mslivo.core.engine.tools.Tools;
import net.mslivo.core.engine.tools.appengine.AppEngine;
import net.mslivo.core.engine.tools.appengine.AppEngineAdapter;
import net.mslivo.core.engine.ui_engine.UIEngine;
import net.mslivo.uitemplate.core.cfg.CFG_MAIN;
import net.mslivo.uitemplate.core.data.Data;
import net.mslivo.uitemplate.core.data.DataGenerator;
import net.mslivo.uitemplate.core.engine.AppAdapter;
import net.mslivo.uitemplate.core.ui.UIAdapter;
import net.mslivo.uitemplate.core.ui.media.BaseMedia;

public class UITemplateMain extends ApplicationAdapter {
    private MediaManager mediaManager;
    private Data data;
    private UIEngine<UIAdapter> uiEngine;
    private AppEngine<AppAdapter,Data> appEngine;
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
        Tools.App.logInProgress("Loading Assets");
        this.mediaManager = new MediaManager();
        this.mediaManager.prepareUICMedia();
        this.mediaManager.prepareCMedia(BaseMedia.ALL);
        this.mediaManager.loadAssets();
        Tools.App.logDone();
        // App Engine
        Tools.App.logInProgress("Loading Engine");
        this.data = DataGenerator.createData();
        this.appEngine = new AppEngine<>(new AppAdapter(), this.data);
        Tools.App.logDone();
        // UI Engine
        Tools.App.logInProgress("Loading UI");
        this.uiEngine = new UIEngine<>(new UIAdapter(this.appEngine), this.mediaManager, CFG_MAIN.RESOLUTION_WIDTH, CFG_MAIN.RESOLUTION_HEIGHT);
        Tools.App.logDone();
    }

    @Override
    public void render() {
        if (Tools.App.runUpdate()) {
            // Update
            this.uiEngine.update();
            this.appEngine.update();
        }

        // Render
        this.uiEngine.render();

        // Log Benchmark
        if (System.currentTimeMillis() - timer_debug_info > 5000) {
            Tools.App.logBenchmark();
            timer_debug_info = System.currentTimeMillis();
        }
    }

    @Override
    public void dispose() {
        Tools.App.logInProgress("Shutting down...");
        this.uiEngine.shutdown();
        this.appEngine.shutdown();
        this.mediaManager.shutdown();
        Tools.App.logDone();
    }


}

package net.mslivo.uitemplate.desktop;


import net.mslivo.core.engine.tools.Tools;
import net.mslivo.uitemplate.core.UITemplateMain;
import net.mslivo.uitemplate.core.cfg.CFG_MAIN;

public class LauncherMain {
    public static void main(String[] args) {
        Tools.App.launch(new UITemplateMain(),
                CFG_MAIN.APP_TITLE,
                CFG_MAIN.RESOLUTION_WIDTH,
                CFG_MAIN.RESOLUTION_HEIGHT,
                CFG_MAIN.TARGET_FPS);
    }

}

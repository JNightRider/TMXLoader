package io.github.jmecn.tiled.app;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.system.AppSettings;
import io.github.jmecn.tiled.TiledMapAppState;
import io.github.jmecn.tiled.TmxLoader;
import io.github.jmecn.tiled.core.TiledMap;
import io.github.jmecn.tiled.enums.ZoomMode;

/**
 * Test zoom mode
 * @author yanmaoyuan
 *
 */
public class TestZoom extends SimpleApplication {
    private TiledMapAppState tiledMapState;

    public void click() {
        System.out.println("Click! ======");
        System.out.println("cursor tile: " + tiledMapState.getCursorTileCoordinate());
        System.out.println("cursor pixel: " + tiledMapState.getCursorPixelCoordinate());
        System.out.println("cursor screen: " + tiledMapState.getCursorScreenCoordinate());
        System.out.println("center tile: " + tiledMapState.getCameraTileCoordinate());
        System.out.println("center pixel: " + tiledMapState.getCameraPixelCoordinate());
        System.out.println("center screen: " + tiledMapState.getCameraScreenCoordinate());
        System.out.println("map scare: " + tiledMapState.getMapScale());
        System.out.println("map translation: " + tiledMapState.getMapTranslation());
    }

    public void initInput() {
        inputManager.setCursorVisible(true);

        inputManager.addMapping("CLICK", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener((ActionListener) (name, isPressed, tpf) -> {
            if (isPressed) {
                click();
            }
        }, "CLICK");

        inputManager.addMapping("ZOOM_MODE_MAP", new KeyTrigger(KeyInput.KEY_1));
        inputManager.addListener((ActionListener) (name, isPressed, tpf) -> {
            tiledMapState.setZoomMode(ZoomMode.MAP);
        }, "ZOOM_MODE_MAP");

        inputManager.addMapping("ZOOM_MODE_CAMERA", new KeyTrigger(KeyInput.KEY_2));
        inputManager.addListener((ActionListener) (name, isPressed, tpf) -> {
            tiledMapState.setZoomMode(ZoomMode.CAMERA);
        }, "ZOOM_MODE_CAMERA");

    }

    @Override
    public void simpleInitApp() {
        assetManager.registerLoader(TmxLoader.class, "tmx", "tsx");
        assetManager.registerLocator("examples", FileLocator.class);

        TiledMap tiledMap = (TiledMap) assetManager.loadAsset("Isometric/01.tmx");
        assert tiledMap != null;

        tiledMapState = new TiledMapAppState();
        stateManager.attach(tiledMapState);

        tiledMapState.setMap(tiledMap);

        initInput();
    }

    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setSamples(4);

        TestZoom app = new TestZoom();
        app.setSettings(settings);
        app.start();
    }
}
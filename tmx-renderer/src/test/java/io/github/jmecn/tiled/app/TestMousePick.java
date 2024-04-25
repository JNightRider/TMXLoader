package io.github.jmecn.tiled.app;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import com.jme3.system.AppSettings;
import io.github.jmecn.tiled.TiledMapAppState;
import io.github.jmecn.tiled.TmxLoader;
import io.github.jmecn.tiled.core.TileLayer;
import io.github.jmecn.tiled.core.TiledMap;
import io.github.jmecn.tiled.math2d.Point;

/**
 * Test mouse pick
 * @author yanmaoyuan
 *
 */
public class TestMousePick extends SimpleApplication {
    private TileLayer tileLayer;

    private TiledMap tiledMap;

    private int tileId = 0;
    private static final int TILE_MAX = 361;

    public void click() {
        TiledMapAppState state = stateManager.getState(TiledMapAppState.class);
        Vector2f cursor = inputManager.getCursorPosition();
        Point point = state.getCursorTileCoordinate(cursor);
        System.out.println("Click:" + point);
        if (tiledMap.contains(point.getX(), point.getY())) {
            // next tile
            tileId++;
            if (tileId >= TILE_MAX) {
                tileId = 0;
            }
            tiledMap.setTileAtFromTileId(tileLayer, point.getX(), point.getY(), tileId);
        }
    }

    public void initInput() {
        inputManager.setCursorVisible(true);
        inputManager.addMapping("CLICK", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener((ActionListener) (name, isPressed, tpf) -> {
            if (isPressed) {
                click();
            }
        }, "CLICK");
    }

    @Override
    public void simpleInitApp() {
        TmxLoader.registerLoader(assetManager);
        assetManager.registerLocator("examples", FileLocator.class);

        tiledMap = (TiledMap) assetManager.loadAsset("Orthogonal/01.tmx");
        assert tiledMap != null;
        tileLayer = (TileLayer) tiledMap.getLayer("Ground");

        TiledMapAppState tiledMapState = new TiledMapAppState();
        stateManager.attach(tiledMapState);
        tiledMapState.initialize(stateManager, this);

        tiledMapState.setMap(tiledMap);

        initInput();
    }

    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setSamples(4);

        TestMousePick app = new TestMousePick();
        app.setSettings(settings);
        app.start();
    }
}
package io.github.jmecn.tiled.loader;

import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetManager;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * desc:
 *
 * @author yanmaoyuan
 */
public final class Utils {
    private Utils() {
    }

    /**
     * Utilities method to correct the asset path.
     *
     * @param assetManager the asset manager
     * @param key          the asset key
     * @param src          the source path
     * @return the corrected asset path
     */
    public static String toJmeAssetPath(AssetManager assetManager, AssetKey<?> key, String src) {

        /*
         * 1st: try to locate it with assetManager. No need to handle the src
         * path unless assetManager can't locate it.
         */
        if (assetManager.locateAsset(new AssetKey<>(src)) != null) {
            return src;
        }

        /*
         * 2nd: In JME I suppose that all the files needed are in the same
         * folder, that's why I cut the filename and contact it to
         * key.getFolder().
         */
        String dest = src.replace("\\\\", "/");
        int idx = dest.lastIndexOf("/");
        if (idx >= 0) {
            dest = key.getFolder() + src.substring(idx + 1);
        } else {
            dest = key.getFolder() + dest;
        }

        /*
         * 3rd: try to locate it again.
         */
        if (assetManager.locateAsset(new AssetKey<>(dest)) != null) {
            return dest;
        } else {
            throw new IllegalArgumentException("Can't locate asset: " + src);
        }
    }

    public static String getAttributeValue(Node node, String attributeName) {
        final NamedNodeMap attributes = node.getAttributes();
        String value = null;
        if (attributes != null) {
            Node attribute = attributes.getNamedItem(attributeName);
            if (attribute != null) {
                value = attribute.getNodeValue();
            }
        }
        return value;
    }

    public static String getAttribute(Node node, String attributeName, String def) {
        final String attr = getAttributeValue(node, attributeName);
        if (attr != null) {
            return attr;
        } else {
            return def;
        }
    }

    public static int getAttribute(Node node, String attributeName, int def) {
        final String attr = getAttributeValue(node, attributeName);
        if (attr != null) {
            return Integer.parseInt(attr);
        } else {
            return def;
        }
    }

    public static double getDoubleAttribute(Node node, String attributeName, double def) {
        final String attr = getAttributeValue(node, attributeName);
        if (attr != null) {
            return Double.parseDouble(attr);
        } else {
            return def;
        }
    }
}
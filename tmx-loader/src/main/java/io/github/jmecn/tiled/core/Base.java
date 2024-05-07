package io.github.jmecn.tiled.core;

import java.util.Properties;

/**
 * Wraps any number of custom properties. Can be used as a child of the map,
 * tile (when part of a tileset), layer, objectgroup and object elements.
 * 
 * The type of the property. Can be string (default), int, float, bool, color or
 * file (since 0.16, with color and file added in 0.17).
 * 
 * Boolean properties have a value of either "true" or "false".
 * 
 * Color properties are stored in the format #AARRGGBB.
 * 
 * File properties are stored as paths relative from the location of the map
 * file.
 * 
 * @author yanmaoyuan
 * 
 */
public class Base {
    
    protected Properties properties = new Properties();

    public boolean hasProperties() {
        return properties != null && !properties.isEmpty();
    }

    /**
     * <p>
     * Getter for the field <code>properties</code>.
     * </p>
     * 
     * @return the map properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * <p>
     * Setter for the field <code>properties</code>.
     * </p>
     * 
     * @param properties
     *            a {@link java.util.Properties} object.
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * @param key property name
     * @return true if and only if the property exists
     */
    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }


    /** @param key property name
     * @return the value for that property if it exists, otherwise, null */
    public Object getProperty(String key) {
        return properties.get(key);
    }

    /** Returns the object for the given key, casting it to clazz.
     * @param key the key of the object
     * @param clazz the class of the object
     * @return the object or null if the object is not in the map
     * @throws ClassCastException if the object with the given key is not of type clazz */
    public <T> T getProperty(String key, Class<T> clazz) {
        return (T)getProperty(key);
    }

    /**
     * Returns the object for the given key, casting it to clazz.
     * @param key the key of the object
     * @param defaultValue the default value
     * @param clazz the class of the object
     * @return the object or the defaultValue if the object is not in the map
     * @throws ClassCastException if the object with the given key is not of type clazz
     */
    public <T> T getProperty(String key, T defaultValue, Class<T> clazz) {
        Object object = getProperty(key);
        return object == null ? defaultValue : (T)object;
    }

    /**
     * @param key property name
     * @param value value to be inserted or modified (if it already existed)
     */
    public void putProperty(String key, Object value) {
        properties.put(key, value);
    }

    /**
     * @param properties set of properties to be added
     */
    public void putAll(Properties properties) {
        this.properties.putAll(properties);
    }

    /**
     * @param key property name to be removed
     */
    public void removeProperty(String key) {
        properties.remove(key);
    }

    /**
     * Removes all properties
     */
    public void clearProperties() {
        properties.clear();
    }
}

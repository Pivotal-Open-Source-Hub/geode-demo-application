package org.apache.geode.demo.fastfootshoes.gridside.util;

import com.gemstone.gemfire.pdx.PdxInstance;

/**
 * This class is used to cast the value of a PdxInstance coming back from the cluster to
 * the appropriate type
 * @author lshannon
 *
 */
public class ReferenceHelper {
	
	@SuppressWarnings("unchecked")
	/**
	 * Takes a object and class type. If the object is of type PdxInstance, the object
	 * value is retrieved from it and cast to the clazz argument type
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static final <T> T toObject(final Object object, final Class<T> clazz) {
		if (object instanceof PdxInstance) {
			return (T) ((PdxInstance) object).getObject();
		}
		return (T) object;
	}

}

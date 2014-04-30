package com.open.bomberman.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiValueMap<K, V> extends HashMap<K, List<V>> {

	private static final long serialVersionUID = -9157453273928689715L;

	public MultiValueMap() {
		super();
	}

	public void add(K key, V anotherValue) {
		if (!containsKey(key)) {
			put(key, new ArrayList<V>());
		}
		get(key).add(anotherValue);
	}

}

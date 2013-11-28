package org.login.common;

import java.util.Map;

public interface BaseSessionAware {
	public Map<String, Object> getSession();
}

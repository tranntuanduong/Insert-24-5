package com.laptrinhjavaweb.repository;

import java.util.List;

public interface GenericJDBC<T> {
	 List<T> select(String sql, Object...parameters);
	 void update(String sql, Object...parameters);
	 public Long insert(String sql, Object...parameters);
}

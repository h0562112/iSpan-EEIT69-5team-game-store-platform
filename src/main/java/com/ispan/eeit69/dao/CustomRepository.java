package com.ispan.eeit69.dao;


import com.ispan.eeit69.model.User;

public interface CustomRepository {
		void detach(User user);
}

package com.ispan.eeit69.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.News;

public interface NewsRepository extends JpaRepository<News, Integer> {
	List<News> findAllByOrderByCreateTimeDescIdDesc();
}

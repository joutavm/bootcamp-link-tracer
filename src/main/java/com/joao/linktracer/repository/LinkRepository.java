package com.joao.linktracer.repository;

import com.joao.linktracer.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}

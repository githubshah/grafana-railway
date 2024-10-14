package com.railway.booking.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DatabaseSeeder {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void onApplicationEvent() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:data.sql");
        String sql = new String(Files.readAllBytes(Paths.get(resource.getURI())));
        jdbcTemplate.execute(sql);
    }
}



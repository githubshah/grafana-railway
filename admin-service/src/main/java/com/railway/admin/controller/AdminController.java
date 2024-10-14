package com.railway.admin.controller;

import com.railway.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/matrix")
    public Object getMatrix() {
        log.info("get adminService.matrix");
        Object matrix = adminService.getMatrix();
        log.info("adminService.matrix: {}", adminService.getMatrix());
        log.warn("warn adminService.matrix: {}", adminService.getMatrix());
        log.error("error adminService.matrix: {}", adminService.getMatrix());
        return adminService.getMatrix();
    }
}

package com.licon.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Licon
 * @description: TODO
 * @date 2023/10/24 18:45
 */
@RestController
public class ApiController {

    @PostMapping("file")
    public boolean uploadFile(MultipartFile[] files) throws IOException {
        Assert.notNull(files,"not null");
        return true;
    }
}

package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 孙小云
 * @version 1.0
 * @date 2019/6/12 15:42
 **/

@RestController
@Slf4j
@RequestMapping("/upload/")
@Api(value = "/upload/",description = "图片上传")
public class ImageUploadController {


    @Value("${web.uploadPath}")
     private  String path;



    //上传图片
    @PostMapping(value = "fileUpload")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "image", value = "图片", required = true),
        @ApiImplicitParam(name = "type", value = "图片类型，0头像 1，图片", required = true),
    })
    @ApiOperation("上传图片")
    public String uploadImage(MultipartFile image, Integer type) {
        try {
            String name = image.getOriginalFilename();

            System.out.println(name);

            InputStream inputStream = image.getInputStream();
            if(type == 0){
                path=path+"/Avatar";
            }
            Path directory = Paths.get(path);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            long copy = Files.copy(inputStream, directory.resolve(name));

            return "上传成功，大小：" + copy+";url访问路径为："+path+"/"+name;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "上传失败";
        }

    }


    //使用流将图片输出
    @GetMapping("/getImage/{name}")
    public void getImage(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(Files.readAllBytes(Paths.get(path).resolve(name)));
        outputStream.flush();
        outputStream.close();
    }
}

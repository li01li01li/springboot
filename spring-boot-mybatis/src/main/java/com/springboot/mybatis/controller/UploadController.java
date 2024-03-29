package com.springboot.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/*
* 上传文件控制器
* 直接上传到服务器
* 2019.3.25
* */
@Controller
public class UploadController {
    SimpleDateFormat YYY = new SimpleDateFormat("yyyy-MM-dd");
    @GetMapping("/")

    public String index(){
        return "upload";
    }
    @PostMapping("/upload")
    public  String fileUpload(@RequestParam("file")MultipartFile srcFile, RedirectAttributes redirectAttributes){
        //前端没有选择文件，srcFile为空
        if(srcFile.isEmpty()){
            redirectAttributes.addFlashAttribute("message","请选择一个文件");
            return "redirect:upload_status";
        }
        //选择了文件，开始进行上传操作
        try{
            //构建上传目标路径
            File destFile=new File(ResourceUtils.getURL("classpath:").getPath());
            if(!destFile.exists()){
                destFile=new File("");
            }
            //输出目标文件的绝对路径
            System.out.println("file path:"+destFile.getAbsolutePath());
            File upload=new File(destFile.getAbsolutePath(),YYY.format(new Date())+"/");
            //拼接static目录
            String fileName= srcFile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName= UUID.randomUUID() +suffixName;

            //若目标文件夹不存在，则创建一个
            if(!upload.exists()){
                upload.mkdirs();
            }

            System.out.println("完整的上传路径:"+upload.getAbsolutePath()+"/"+srcFile.getOriginalFilename());
            //根据srcFile的大小，准备一个字节数组
            byte[] bytes=srcFile.getBytes();
            //拼接上传路径
            /*Path path= Paths.get(UPLOAD_FOLDER+srcFile.getOriginalFilename());*/
            //通过项目路径，拼接上传路径
            Path path=Paths.get(upload.getAbsolutePath()+"/"+srcFile.getOriginalFilename());
            //最重要的一步，将源文件写入目标地址
            Files.write(path,bytes);
            //将文件上传成功的信息写入messages
            redirectAttributes.addFlashAttribute("message","文件上传成功"+srcFile.getOriginalFilename());

        }catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:upload_status";
    }
    //匹配
    @GetMapping("/upload_status")
    public String uploadStatusPage(){
        return "upload_status";
    }
}

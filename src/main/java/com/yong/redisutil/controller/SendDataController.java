package com.yong.redisutil.controller;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.yong.redisutil.entity.Data;
import com.yong.redisutil.util.ResultData;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SendDataController {

    final RestTemplate restTemplate;
    /**
     * 解析Excel文件获取对应列的值
     * @param file Excel文件
     * @return
     */
    @PostMapping("/excel/upload")
    @SneakyThrows
    public ResultData excelUpload(@RequestParam("file") MultipartFile file){
        //使用InputStream流读取Excel文件
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //保存到集合中
        List<List<Object>> read = reader.read();

        //构建需要发送的JSON数据
        Data data = new Data();
        data.setBusinessTypeNo("DT024");
        data.setSysNo("S001");
        data.setTransmission("1");
        data.setDataFormatNo("DF002");
        data.setContent("");

        //定义url
//        String url = "http://localhost:8888/receive";
        String url = "https://172.18.18.77:30127/producer/send/so";

        //双重遍历集合，同时发送到对应的接口
        read.forEach(x ->{
            x.forEach(y ->{
                data.setStrcontent(y);
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(data));
                restTemplate.postForObject(url, jsonObject, String.class);
            });
        });
        return new ResultData(200,"success",null);
    }
}

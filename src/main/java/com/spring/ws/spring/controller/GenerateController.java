package com.spring.ws.spring.controller;

import com.spring.ws.spring.sql.FiledGenerate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: GenerateController <br>
 * date: 2021/3/26 10:03 <br>
 * author: 帅 <br>
 * version: 1.0 <br>
 */
@RestController
public class GenerateController {

    @Autowired
    private FiledGenerate filedGenerate;

    @RequestMapping(value = "/generate",method = RequestMethod.GET)
    @ApiOperation("生成实体类")
    private String generate() throws Exception {
        filedGenerate.generate();
        return "生成成功";
    }
}

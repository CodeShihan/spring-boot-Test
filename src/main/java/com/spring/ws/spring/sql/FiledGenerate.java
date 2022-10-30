package com.spring.ws.spring.sql;


import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.HashMap;
@Component
public class FiledGenerate {

    public void generate() throws Exception {
        Configuration freeMarkerConfiguration = new Configuration(Configuration.getVersion());
        freeMarkerConfiguration.setDefaultEncoding("UTF-8");
        freeMarkerConfiguration.setDirectoryForTemplateLoading(new File(new ClassPathResource("src\\main\\resources\\templates").getPath()));
        Template template = freeMarkerConfiguration.getTemplate("index.ftl");
        StringWriter writer = new StringWriter();
        String str = this.getStr();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("filed",str);
        template.process(map,writer);

        FileSystemView fsv = FileSystemView.getFileSystemView();
        String path=fsv.getHomeDirectory().getPath();    //这便是读取桌面路径的方法了
        String tab = ConDataSource.underlineToCamel(tablename, false);
        File file=new File(path+File.separator+tab+".java");
        FileOutputStream outputStream=new FileOutputStream(file);
        byte[] bytes = writer.toString().getBytes();
        outputStream.write(bytes,0,bytes.length);
        outputStream.flush();
        outputStream.close();
    }


    @Value("${generate.db.name}")
    private String dbName;

    @Value("${generate.db.password}")
    private String password;

    @Value("${generate.db.username}")
    private String username;

    @Value("${generate.db.tablename}")
    private String tablename;

    @Value("${generate.db.url}")
    private String url;

    private String getStr() throws Exception {
        DbProperty dbProperty=new DbProperty();
        dbProperty.setDbName(dbName);
        dbProperty.setPassword(password);
        dbProperty.setUserName(username);
        dbProperty.setTableName(tablename);
        dbProperty.setUrl(url);
        ConDataSource conDataSource=new ConDataSource(dbProperty);
        return conDataSource.getFileds();
    }

}

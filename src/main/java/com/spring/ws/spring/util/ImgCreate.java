package com.spring.ws.spring.util;

import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ImgCreate {

    static char[] codeSequence={'a','b','c','d','e','f','g','h','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z',
            '2','3','4','5','6','7','8','9'};
    static int charNum= codeSequence.length;


    public static void main(String[] args) throws IOException
    {
        Map<String, String> map = generateCode();//**没有这个文件夹的请先自行创建文件夹，以免报错！！**
        System.out.println(map);
    }


    public static Map<String,String> generateCode() throws IOException {//首先定义验证码图片框

        ClassPathResource classPathResource = new ClassPathResource("src\\main\\resources\\img\\code.jpg");
        String path = classPathResource.getPath();

        int width = 100;   //验证码图片的宽度
        int height = 55;    //验证码图片的高度
        BufferedImage buffImg =new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //定义图片上的图形和干扰线
        Graphics2D gd = buffImg.createGraphics();
        gd.setColor(Color.lightGray);    //将图片填充为浅灰色
        gd.fillRect(0,0,width,height);
        gd.setColor(Color.black);        //画边框
        gd.drawRect(0,0,width-1,height-1);
        //随机产生16条灰色干扰线
        gd.setColor(Color.gray);
        //生成一个随机数生成器类，随机生成干扰线
        Random random =new Random();
        for (int i=0;i<16;i++)
        {
            int x = random.nextInt(width);
            int y =random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            gd.drawLine(x,y,x+x1,y+y1);
        }

        //计算字位置坐标
        int codeCount =4;       //字符个数
        int fontHeight ;   //字体高度
        int codeX;         //第一个字符的X坐标，因为后面的坐标依次递增，所以他的X轴值codeX的
        int codeY;         //验证字符的Y坐标，因为并排所以值一样
        //width-4除去左右多余的位置，是验证码更加集中显示，减的越多越集中
        //codeCount+1，等比分配显示的宽度，包括两边的空格；


        codeX=(width-4)/(codeCount+1);   //第一个字母的起始位置
        fontHeight = height-10;         //高度中间区域显示验证码
        codeY=height-7;        //

        //创建字体，字体的大小应该根据图片的高度来定
        Font font= new Font("Fixedsys",Font.PLAIN,fontHeight);
        gd.setFont(font);

        //获取到实际生成的验证码
        StringBuilder stringBuilder = new StringBuilder();
        //随机产生codeCount数字的验证码
        for(int i=0;i<codeCount;i++)
        {//每次随机拿一个字母，赋予随机的颜色
            String strRand = String.valueOf(codeSequence[random.nextInt(charNum)]);
            int red = random.nextInt(255);
            int blue = random.nextInt(255);
            int green = random.nextInt(255);
            gd.setColor(new Color(red,green,blue));
            //把字放在图上
            gd.drawString(strRand,(i+1)*codeX,codeY);

            stringBuilder.append(strRand);
        }


        ImageIO.write(buffImg,"jpg",new File(path));

        Map<String,String> map = new HashMap<>();
        map.put("imgUrl",path);
        map.put("code",stringBuilder.toString());
        return map;
    }
}

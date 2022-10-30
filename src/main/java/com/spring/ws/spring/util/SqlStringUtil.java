package com.spring.ws.spring.util;

import org.springframework.util.StringUtils;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlStringUtil {
    public static String getResults(Class<?> dtoClass) throws Exception {
        StringBuilder sql=new StringBuilder();
        sql.append("@Results(value = {");
        sql.append("\r\n");
        Field[] fields = dtoClass.getDeclaredFields();
        String fieldName,columnName;
        Column column;
        NoSelectColumn noSelectColumn;
        Class<?> type;
        for (Field field : fields) {
            field.setAccessible(true);
            type = field.getType();
            fieldName = field.getName();
            if ("serialVersionUID".equals(fieldName)
                    ||type.equals(List.class)
                    ||type.equals(Set.class)
                    ||type.equals(Object.class)){
                continue;
            }
            if (JavaClassType.containsKey(type)){
                noSelectColumn = field.getAnnotation(NoSelectColumn.class);
                if (noSelectColumn!=null){
                    continue;
                }
                column = field.getAnnotation(Column.class);
                if (column!=null){
                     columnName = column.name();
                }else {
                    columnName = humpToLine2(fieldName);
                }
                fieldName=field.getName();
                sql.append(String.format("        @Result(property = \"%s\",column = \"%s\",javaType = %s),",fieldName,columnName,JavaClassType.get(type)));
                sql.append("\n");
            }else {
                throw new Exception("属性类型不是包装类型！字段名称："+fieldName);
            }

        }
        sql.append("\r\n");
        sql.append("})");
        return sql.toString();
    }
    public static String getAllColumn(Class<?> cls,String str,String... fild){
        return getColumn(cls,str,fild);
    }

    public static String getAllColumn(Class<?> cls,String str){
        return getColumn(cls,str,null);
    }

    public static String getAllColumn(Class<?> cls){
        return getColumn(cls,null,null);
    }

    public static String getColumn(Class<?> cls,String str,String... fild){
        StringBuilder sql=new StringBuilder();
        String fieldName,columnName;
        Class<?> type;
        Boolean flag;
        Field[] fields = cls.getDeclaredFields();
        List<String> strList=null;
        if (fild==null){
            flag=false;
        }else {
            flag=true;
            strList = Arrays.asList(fild);
        }
        NoSelectColumn noSelectColumn;
        for (Field field : fields) {
            field.setAccessible(true);
            type = field.getType();
            fieldName = field.getName();
            if ("serialVersionUID".equals(fieldName)
                    ||type.equals(List.class)
                    ||type.equals(Set.class)
                    ||type.equals(Object.class)){
                continue;
            }
            noSelectColumn = field.getAnnotation(NoSelectColumn.class);
            if (noSelectColumn!=null){
                continue;
            }
            if (flag){
                if (strList.contains(fieldName)){
                    continue;
                }
            }
            columnName = humpToLine2(fieldName);
            if (StringUtils.isEmpty(str)){
                sql.append(columnName+",");
            }else {
                sql.append(str+"."+columnName+",");
            }

        }
        return sql.toString().substring(0, sql.length() - 1);
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /** 驼峰转下划线,效率比上面高 */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}

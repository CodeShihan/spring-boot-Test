package com.spring.ws.spring.sql;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;


import javax.sql.DataSource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConDataSource {
    private DbProperty db;

    private JdbcTemplate jdbcTemplate;

    public ConDataSource(DbProperty db){
        this.db=db;
        this.jdbcTemplate=this.DataBaseSource();
    }

    public JdbcTemplate DataBaseSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUrl(db.getUrl());
        dataSource.setUsername(db.getUserName());
        dataSource.setPassword(db.getPassword());
        dataSource.setDriverClassName(db.getDriverClassName());
        return this.getJdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    public String getFileds() throws Exception {
        try {
            String sql="select COLUMN_NAME,COLUMN_KEY,column_comment,DATA_TYPE,IS_NULLABLE from INFORMATION_SCHEMA.Columns where table_name='"+db.getTableName()+"' and TABLE_SCHEMA='"+db.getDbName()+"' ORDER BY \n" +
                    "ORDINAL_POSITION";
            System.err.println(sql);
            List<SqlFileds> fileds = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SqlFileds.class));
            return this.generate(fileds);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public String generate(List<SqlFileds> fileds) throws Exception {
        String comment="";
        String column="";
        String columnKey="";
        String type="";
        StringBuilder builder=new StringBuilder();
        if (!CollectionUtils.isEmpty(fileds)){
            for (SqlFileds filed : fileds) {
                comment=filed.getColumnComment();
                column=filed.getColumnName();
                columnKey= filed.getColumnKey();
                type = this.getType(filed.getDataType());
                builder.append(this.isPri(columnKey,type));
                builder.append("\n");
                builder.append("\t@ApiModelProperty(\""+comment+"\")");
                builder.append("\n");
                this.getNullOrEmpty(type, comment);
                if ("No".equals(filed.getIsNullable())||"PRI".equals(filed.getColumnKey())){
                    builder.append("\t"+this.getNullOrEmpty(type,comment));
                    builder.append("\n");
                }
                builder.append("\t@Column(name=\""+column+"\")");
                builder.append("\n");
                builder.append("\tprivate "+type+" "+this.underlineToCamel(column,true)+";");
                builder.append("\n");
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private String isPri(String columnKey,String type){
        if ("PRI".equals(columnKey)&& (("Integer").equals(type)||("Long").equals(type))){
            return "@Id\n" +
                    "\t@GeneratedValue(strategy = GenerationType.IDENTITY)";
        }else if ("PRI".equals(columnKey)){
            return "@Id";
        }
        return "";
    }

    public static void main(String[] args) {
        String name_str = underlineToCamel("name_Str", false);
        System.out.println(name_str);

    }
    /**
     * 下划线转驼峰法
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underlineToCamel(String line,boolean smallCamel){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    public String getNullOrEmpty(String type,String comment){
        if ("String".equals(type)){
            return "@NotNull(message = \""+comment+"不能为Null\")\n" +
                    "\t@NotEmpty(message = \""+comment+"不能为空\")";
        }
        return "@NotNull(message = \""+comment+"不能为Null\")";
    }

    public String getType(String type) throws Exception {
        switch (type){
            case "bigint":
                return "Long";
            case "int":
            case "tinyint":
                return "Integer";
            case "varchar":
            case "char":
            case "text":
                return "String";
            case "decimal":
                return "BigDecimal";
            case "date":
                return "LocalDate";
            case "datetime":
                return "LocalDateTime";
            default:
                throw new Exception("未知类型："+type);
        }
    }
}

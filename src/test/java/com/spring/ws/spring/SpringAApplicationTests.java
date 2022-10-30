package com.spring.ws.spring;


import com.alibaba.fastjson.JSON;
import com.spring.ws.spring.elasticsearch.ElasticSearchUtil;
import com.spring.ws.spring.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Slf4j
class SpringAApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ElasticSearchUtil elasticSearchUtil;


//
//    @Test
//    void C(@Autowired Persion persion){
//        System.err.println(persion.getName());
//    }

    @Test
    void A() throws IOException {
//        System.err.println("===============删除索引库=====================");
//        Boolean response = elasticSearchUtil.deleteIndex(EsConst.Es_Index_Name);
//        System.err.println(response);
//        System.err.println("===============添加索引库=====================");
//        Boolean index= elasticSearchUtil.createIndex(EsConst.Es_Index_Name);
//        System.err.println(index);
//        System.err.println("===============索引库存在=====================");
//        Boolean aBoolean = elasticSearchUtil.existsIndex(EsConst.Es_Index_Name);
//        System.err.println(aBoolean);
//        System.err.println("===============所有索引库=====================");
//        Set<String> indexAll = elasticSearchUtil.getIndexAll();
//        indexAll.forEach(s->{
//            System.err.println(s);
//        });
//        System.err.println("===============添加对象=====================");
//        User user=new User(1,"帅");
//        elasticSearchUtil.addSoucre(user,user.getId().toString());
//        System.err.println("===============获取对象=====================");
//        User u = (User) elasticSearchUtil.get("1", User.class);
//        System.err.println(u);
//        System.err.println("===============修改对象=====================");
//        user=new User(2,"帅");
//        elasticSearchUtil.updateSource("1",user);
//        System.err.println("===============删除对象=====================");
//            elasticSearchUtil.deleteSource("2");
//        elasticSearchUtil.deleteSource("3");
//        elasticSearchUtil.deleteSource("1");
//        elasticSearchUtil.deleteSource("4");
//        System.err.println("===============是否存在对象=====================");
//        boolean b = elasticSearchUtil.existsSource("1");
//        System.err.println(b);
//        System.err.println("===============批量插入对象=====================");
//        ArrayList<User> userList = new ArrayList<>();
//        userList.add(new User(1,"帅","帅真帅"));
//        userList.add(new User(2,"康","帅是真的帅"));
//        userList.add(new User(3,"锐","康儿是沙雕"));
//        userList.add(new User(4,"柔","真的帅"));
//        userList.add(new User(5,"昌","是吧"));
//        userList.add(new User(6,"逢","我也觉得帅"));
//        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, Function.identity()));
//        Boolean aBoolean1 = elasticSearchUtil.addList(userMap);
//        System.err.println(aBoolean1);
        System.err.println("===============搜索对象=====================");
        //MatchAllQueryBuilder builder = QueryBuilders.matchAllQuery();//查询所有
        TermQueryBuilder builder = QueryBuilders.termQuery("desc", "帅");

        List<Object> objects = elasticSearchUtil.search(builder, 0, 20, User.class);
        System.err.println(objects);



    }









    @Test
    void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("ws_test");
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.index());
    }

    @Test
    void isExists() throws IOException {
        GetIndexRequest request = new GetIndexRequest("ws_test");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("ws_test");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
        System.out.println(response.toString());
    }

    @Test
    void createDoc() throws IOException {
        User user=new User();
        user.setId(1);
        user.setName("张三");
        IndexRequest request = new IndexRequest("ws_test");
        request.timeout("1s");
        request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    @Test
    void createDocs() throws IOException {
        List<User> userList=new ArrayList<>();
        User user=new User();
        user.setId(1);
        user.setName("张三");
        userList.add(user);
        user=new User();
        user.setId(2);
        user.setName("李四");
        userList.add(user);


        IndexRequest request = new IndexRequest("ws_test");
        request.timeout("1s");
        request.source(JSON.toJSONString(userList), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }


//    @Test
//    void a(){
//        String num = redisUtil.get("num");
//        System.out.println(num);
//    }
//
//    @Test
//    void test(){
//        for (int i=0;i<20;i++){
//            new Thread(() -> {
//                try {
//                    t();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//       // threadPool.shutdown();
//
//    }







//
//    public Boolean lock(String key,Long time){
//
//        while (true){
//            String value = stringRedisTemplate.opsForValue().get(key);
//            if (StringUtils.isBlank(value)){
//                Boolean absent = stringRedisTemplate.opsForValue().setIfAbsent(key, time + "",5, TimeUnit.SECONDS);
//                if (absent==false){
//                    continue;
//                }
//                return true;
//            }
//            return false;
//        }
//
//
//    }
//
//    public Boolean lock(String key,Long time){
//
//        while (true){
//            String value = stringRedisTemplate.opsForValue().get(key);
//            if (StringUtils.isBlank(value)){
//                Boolean absent = stringRedisTemplate.opsForValue().setIfAbsent(key, time + "",5, TimeUnit.SECONDS);
//                if (absent==false){
//                    continue;
//                }
//                return true;
//            }
//            return false;
//        }
//    }
//    public void t(){
//        try {
//            lock("lock",System.currentTimeMillis())
//            if (){
//                stringRedisTemplate.opsForValue().decrement("quantity");
//            }
//        }catch (Exception e) {
//
//        }finally {
//
//        }
//    }











//    /**
//     * 分布式锁整不出来 我是fw
//     */
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Test
//    void contextLoads() {
//
//        for (int i=0;i<200;i++){
//            new Thread(()->{
//                decr();
//            }).start();
//        }
//    }
//    private static final int TIMEOUT = 5*1000;
//    public void decr(){
//
//        String key="lock";
//        Long time=System.currentTimeMillis()+TIMEOUT;
//        try {
//            Thread.sleep(1);
//            //获取锁
//            if (lock(key,time)){
//                System.out.println(Thread.currentThread().getName()+"-->拿到锁");
//                Integer quantity = (Integer)redisTemplate.opsForValue().get("quantity");
//                if (quantity>=0) {
//                    Long decrement = redisTemplate.opsForValue().decrement("quantity");
//                    System.out.println(Thread.currentThread().getName() + "----->" + decrement);
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println(Thread.currentThread().getName()+"-->解锁");
//            unlock(key,time);
//        }
//    }
//
//    /**
//     * 获取锁
//     * @return
//     */
//    public boolean lock(String key,Long timeStamp){
//        //1-> 100 2-> 200
//        if(redisTemplate.opsForValue().setIfAbsent(key,timeStamp)){
//            System.err.println(Thread.currentThread().getName()+"-->拿到锁");
//            return true;
//        }
//
//        //1-> 100
//        String time = redisTemplate.opsForValue().get(key)+"";
//        Long timeMillis = System.currentTimeMillis();
//        if (!StringUtil.isEmpty(time)&&Long.parseLong(time)<timeMillis){
//            //timeOut -> 100 或 200
//            String timeOut = redisTemplate.opsForValue().getAndSet(key,timeStamp)+"";
//            if (StringUtil.isEmpty(timeOut)&&time.equals(timeOut)){
//                return true;
//            }
//        }
//        return false;
//
//
//    }

//    /**
//     * 释放锁
//     */
//    public void  unlock(String key,Long time){
//        Long timeStamp=(Long)redisTemplate.opsForValue().get(key);
//        if (StringUtil.isNotEmpty(timeStamp+"")&&timeStamp.equals(time)){
//            System.out.println(Thread.currentThread().getName()+"-->删除锁");
//            redisTemplate.opsForValue().getOperations().delete(key);
//        }
//    }

//    @SneakyThrows
//    public static void main(String[] args) {
//        long mainStart = System.currentTimeMillis();
//        System.err.println("thread-userFuture-->"+Thread.currentThread().getName());
//        CompletableFuture<List<User>> userFuture = CompletableFuture.supplyAsync(() -> {
//
//            long start = System.currentTimeMillis();
//            System.err.println("thread-userFuture-->"+Thread.currentThread().getName());
//            List<User> users=new ArrayList<>();
//            for (int i = 0; i <= 200000; i++) {
//                User u = new User();
//                u.setId(i);
//                u.setName(i + "");
//                users.add(u);
//            }
//            long end = System.currentTimeMillis();
//            System.err.println("thread-userFuture-->"+Thread.currentThread().getName()+"子线程添加100w数据时间:"+(end-start));
//            System.gc();
//            return users;
//        });
//        CompletableFuture<List<User>> user = CompletableFuture.supplyAsync(() -> {
//            long start = System.currentTimeMillis();
//            System.err.println("thread-user-->"+Thread.currentThread().getName());
//            List<User> users=new ArrayList<>();
//            for (int i = 0; i <=200000; i++) {
//                User u = new User();
//                u.setId(i);
//                u.setName(i + "");
//                users.add(u);
//            }
//            long end = System.currentTimeMillis();
//            System.err.println("thread-user-->"+Thread.currentThread().getName()+"子线程添加100w数据时间:"+(end-start));
//            System.gc();
//            return users;
//        });
//        userFuture.get();
//        user.get();
//        long mainEnd = System.currentTimeMillis();
//        System.err.println("thread-user-->"+Thread.currentThread().getName()+"主线程时间:"+(mainEnd-mainStart));
//
//    }

//    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        System.err.println("thread--->" + Thread.currentThread().getName());
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i <= 1000000; i++) {
//            User u = new User();
//            u.setId(i);
//            u.setName(i + "");
//            users.add(u);
//        }
//        for (int i = 0; i <= 1000000; i++) {
//            User u = new User();
//            u.setId(i);
//            u.setName(i + "");
//            users.add(u);
//        }
//        long end = System.currentTimeMillis();
//        System.err.println("thread--->" + Thread.currentThread().getName() + "正常添加200w数据时间:" + (end - start));
//    }
}

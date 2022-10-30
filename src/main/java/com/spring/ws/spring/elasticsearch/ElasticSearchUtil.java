package com.spring.ws.spring.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spring.ws.spring.entity.User;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ElasticSearchUtil {

    private RestHighLevelClient client;
    private String time;
    private String name;

    public ElasticSearchUtil(RestHighLevelClient client,String name){
        this.client=client;
        this.name=name;
        this.time="1s";
    }

    /**
     * 创建索引库
     * @return
     * @throws IOException
     */
    public Boolean createIndex(String name) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(name);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }

    /**
     * 删除索引库
     * @return
     * @throws IOException
     */
    public Boolean deleteIndex(String name) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(name);
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }

    /**
     * 判断索引库是否存在
     * @return
     * @throws IOException
     */
    public Boolean existsIndex(String name) throws IOException {
        GetIndexRequest request = new GetIndexRequest(name);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }

    /**
     * 获取所有的索引库名称
     * @return
     * @throws IOException
     */
    public Set<String> getIndexAll() throws IOException {
        GetAliasesRequest request = new GetAliasesRequest();
        GetAliasesResponse response = client.indices().getAlias(request, RequestOptions.DEFAULT);
        Map<String, Set<AliasMetaData>> map = response.getAliases();
        Set<String> keySet = map.keySet();
        return keySet;
    }

    /**
     * 添加对象到索引库
     * @param object 对象
     * @return
     * @throws IOException
     */
    public RestStatus addSoucre(Object object,String id) throws IOException {
        IndexRequest request = new IndexRequest(name);
        request.id(id);
        request.timeout(time);
        request.source(JSON.toJSONString(object), XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        return response.status();
    }

    /**
     * 删除文档数据
     * @param id   删除数据id
     * @return
     * @throws IOException
     */
    public RestStatus deleteSource(String id) throws IOException {
        DeleteRequest request = new DeleteRequest(name,id);
        request.timeout(time);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        return response.status();
    }

    /**
     * 判断文档数据是否存在
     * @param id   数据id
     * @return
     * @throws IOException
     */
    public boolean existsSource(String id) throws IOException {
        GetRequest request = new GetRequest(name, id);
        boolean exists = client.existsSource(request, RequestOptions.DEFAULT);
        return exists;
    }

    /**
     * 修改文档数据
     * @param id   数据id
     * @return
     * @throws IOException
     */
    public UpdateResponse updateSource(String id,Object object) throws IOException {
        UpdateRequest request = new UpdateRequest(name, id);
        request.timeout(time);
        request.doc(JSONObject.toJSONString(object),XContentType.JSON);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        return response;
    }

    /**
     * 获取文档中某一条的数据
     * @param id  文档数据id
     * @param type 转换类型
     * @return
     * @throws IOException
     */
    public Object get(String id,Class<?> type) throws IOException {
        GetRequest request = new GetRequest(name, id);
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        Map<String, Object> source = response.getSource();
        Object object = JSONObject.parseObject(JSONObject.toJSONString(source), type);
        return object;
    }

    /**
     * 批量插入数据到索引库
     * @param map
     * @return
     * @throws IOException
     */
    public Boolean addList(Map<Integer, User> map) throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout("30s");
        map.forEach((k,v)->{
            request.add(new IndexRequest(name).id(k.toString()).source(JSON.toJSONString(v),XContentType.JSON));
        });
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        return response.hasFailures()==false?true:false;
    }

    /**
     * 搜索
     * @param queryBuilder  搜索条件
     * @param from   分页起始值
     * @param size   分页大小
     * @param cls    转换类
     * @return
     * @throws IOException
     */
    public List<Object> search(QueryBuilder queryBuilder, int from, int size,Class<?> cls) throws IOException {
        SearchRequest request = new SearchRequest(name);
        //构建搜索条件
        SearchSourceBuilder searchBuilder=new SearchSourceBuilder();
        searchBuilder.timeout(new TimeValue(30,TimeUnit.SECONDS));
        //分页  从0开始！！！！
        searchBuilder.from(from);
        searchBuilder.size(size);
        //高亮
        HighlightBuilder builder = new HighlightBuilder();
        builder.field("name");
        builder.field("desc");
        builder.requireFieldMatch(false);
        builder.preTags("<span style='color:red'>");
        builder.postTags("</span>");
        searchBuilder.highlighter(builder);

        searchBuilder.query(queryBuilder);

        searchBuilder.sort(SortBuilders.fieldSort("id").order(SortOrder.ASC));//按照分数排序
        request.source(searchBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHit[] hits = response.getHits().getHits();
        List<Object> list=new ArrayList<>();
        Object o ;
        for (SearchHit hit : hits) {
            Map<String, HighlightField> fields = hit.getHighlightFields();
            HighlightField name = fields.get("name");
            HighlightField desc = fields.get("desc");
            Map<String, Object> map = hit.getSourceAsMap();
            if (desc!=null){
                Text[] fragments = desc.getFragments();
                StringBuilder str=new StringBuilder();
                for (Text text : fragments) {
                    str.append(text);
                }
                map.put("desc",str);
            }
            if (name!=null){
                Text[] fragments = name.getFragments();
                StringBuilder str=new StringBuilder();
                for (Text text : fragments) {
                    str.append(text);
                }
                map.put("name",str);
            }
            o = JSONObject.parseObject(JSONObject.toJSONString(map), cls);
            list.add(o);
        }

        return list;
    }


}

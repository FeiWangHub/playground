//package com.fei.playground.util.elasticSearch;
//
//import cn.aresoft.idea.model.ShenSevenStock;
//import com.alibaba.fastjson.JSON;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.support.master.AcknowledgedResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.CreateIndexResponse;
//import org.elasticsearch.client.indices.GetIndexRequest;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.core.TimeValue;
//import org.elasticsearch.index.query.MatchQueryBuilder;
//import org.elasticsearch.index.query.MultiMatchQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
//
///**
// * ElasticSearch的工具里
// * TODO 测试代码尚未删除
// */
//@Service
//public class ESUtil {
//
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;
//
//    //创建索引index(put java_index)
//    public void testCreateIndex() throws IOException {
//        CreateIndexRequest request = new CreateIndexRequest("java_index");
//        CreateIndexResponse resp = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
//        System.out.println(resp);
//    }
//
//    //测试获取索引,判断是否存在
//    void testExistIndex() throws IOException {
//        GetIndexRequest request = new GetIndexRequest("java_index");
//        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
//        System.out.println(exists);
//    }
//
//    //删除索引
//    void testDeleteIndex() throws IOException {
//        DeleteIndexRequest request = new DeleteIndexRequest("java_index");
//        AcknowledgedResponse resp = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
//        System.out.println(resp);
//    }
//
//    //添加文档
//    void testAddDocument() throws IOException {
//        ShenSevenStock m = new ShenSevenStock();
//        m.setCode("110");
//        m.setStock_name("测试股票名");
//        IndexRequest req = new IndexRequest("java_index");
//
//        //规则 put /java_index/_doc/
//        req.id("1");
//        req.timeout(TimeValue.MAX_VALUE);
//        req.timeout("1s");
//
//        //将数据放入请求
//        req.source(JSON.toJSONString(m), XContentType.JSON);
//
//        //发送请求
//        IndexResponse resp = restHighLevelClient.index(req, RequestOptions.DEFAULT);
//
//        System.out.println(resp.toString());
//        System.out.println(resp.status());
//
//    }
//
//    //判断文档是否存在
//    void testIndexExists() throws IOException {
//        GetRequest getReq = new GetRequest("java_index", "1");
//        //不获取_source的上下文了
//        getReq.fetchSourceContext(new FetchSourceContext(false));
//        getReq.storedFields("_none_");
//
//        boolean exists = restHighLevelClient.exists(getReq, RequestOptions.DEFAULT);
//        System.out.println(exists);
//    }
//
//    //获得文档的信息
//    void testGetDoccument() throws Exception {
//        GetRequest getRequest = new GetRequest("java_index", "1");
//        GetResponse getResp = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
//
//        System.out.println(getResp.getSourceAsString());//打印文档的全部内容
//    }
//
//    //更新文档记录
//    void testUpdateDoc() throws Exception {
//        UpdateRequest updateRequest = new UpdateRequest();
//        updateRequest.timeout("1s");
//
//        ShenSevenStock stock = new ShenSevenStock();
//        updateRequest.doc(JSON.toJSONString(stock), XContentType.JSON);
//
//        UpdateResponse resp = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
//        System.out.println(resp.status());
//    }
//
//    //删除文档记录
//    void deleteDoc() throws Exception {
//        DeleteRequest req = new DeleteRequest("java_index", "3");
//        req.timeout("1s");
//
//        DeleteResponse resp = restHighLevelClient.delete(req, RequestOptions.DEFAULT);
//        System.out.println(resp);
//    }
//
//    //批量插入数据
//    void bulkRequest() throws Exception {
//        BulkRequest bulkRequest = new BulkRequest();
//        bulkRequest.timeout("10s");
//
//        ArrayList<ShenSevenStock> list = new ArrayList<>();
//        list.add(new ShenSevenStock());
//        list.add(new ShenSevenStock());
//        list.add(new ShenSevenStock());
//
//        for (int i = 0; i < list.size(); i++) {
//            bulkRequest.add(
//                    new IndexRequest("java_index")
//                            .id("" + (i + 1))
//                            .source(JSON.toJSONString(list.get(i)), XContentType.JSON));
//        }
//
//        BulkResponse resp = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
//        System.out.println(resp.hasFailures());
//    }
//
//
//    //用matchQuery, 查询index指定字段
//    public SearchResponse searchByMatchQuery(String es_index, String queryKey, String queryValue, int from, int size)
//            throws IOException {
//        //构建查询条件
//        SearchRequest searchReq = new SearchRequest(es_index);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        //查询条件，可以使用QueryBuilders
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(queryKey, queryValue);
//        sourceBuilder.query(matchQueryBuilder);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        sourceBuilder.from(from);
//        sourceBuilder.size(size);
//        searchReq.source(sourceBuilder);
//
//        SearchResponse resp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
////        System.out.println("！！！DEBUG SearchResponse返回的字符串是:");
////        for (SearchHit documentFields : resp.getHits().getHits()) {
////            System.out.println(documentFields.getSourceAsMap());
////        }
//
//        return resp;
//    }
//
//    public SearchResponse searchByMultiMatchQuery(String es_index, String queryStr, String[] queryFields, int from, int size) throws IOException {
//        SearchRequest searchReq = new SearchRequest(es_index);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        //第一个参数是查询的值，后面的参数是字段名，可以跟多个字段，用逗号隔开
//        MultiMatchQueryBuilder qBuilder = QueryBuilders.multiMatchQuery(queryStr, queryFields);
//        sourceBuilder.query(qBuilder);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        sourceBuilder.from(from);
//        sourceBuilder.size(size);
//        searchReq.source(sourceBuilder);
//
//        return restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
//    }
//}

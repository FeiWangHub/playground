//package com.fei.playground.util.elasticSearch;
//
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.core.TimeValue;
//import org.elasticsearch.index.query.MatchQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class ElasticSearchClientConfig {
//
//    @Value("${es.host}")
//    private String host;
//    @Value("${es.port}")
//    private int port;
//    @Value("${es.user}")
//    private String user;
//    @Value("${es.pwd}")
//    private String pwd;
//
//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
////        无密码链接
////        RestHighLevelClient esClient = new RestHighLevelClient(
////                RestClient.builder(
////                        new HttpHost(host, port, "http")
////                )
////        );
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, pwd));
//        RestHighLevelClient esClient = new RestHighLevelClient(
//                RestClient.builder(new HttpHost(host, port))
//                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//                                httpClientBuilder.disableAuthCaching();
//                                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//                            }
//                        })
//        );
//
//        return esClient;
//    }
//
//    public String getHost() {
//        return host;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public String getUser() {
//        return user;
//    }
//
//    public String getPwd() {
//        return pwd;
//    }
//
//    public static void main(String[] args) throws IOException {
//        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("39.100.78.62", 9200, "http")
//                )
//        );
//
//        //搜索test_index2 构建查询条件
//        SearchRequest searchRequest = new SearchRequest("test_index2");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        //条件
//        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "金龙");
//
////        TermQueryBuilder termQuery = QueryBuilders.termQuery("name", "茅台");
//        searchSourceBuilder.query(matchQuery);
//        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(20);
//
//        //发请求
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse resp = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        System.out.println("======测试es链接=========");
////        System.out.println(JSON.toJSONString(resp.getHits()));
//        System.out.println(resp);
//        restHighLevelClient.close();
//    }
//}

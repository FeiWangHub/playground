package com.fei.playground.algorithm.hackerRank.M_McKinsey_OA;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Get Author Articles
 * Write an HTTP GET method to retrieve information from an articles' database. The query response is
 * paginated and can be further accessed by appending to the query string &page=num where num is the
 * page number.
 * ©
 * Given a string of author, getArticleTitles must perform the following tasks:
 * 2
 * 1. Query https://jsonmock.hackerrank.com/api/articles?author=<authorName>&page=<num> (replace
 * <authorName> and <num>).
 * 2. Initialize the titles array to store a list of string elements.
 * 3. Store the name of each article returned in the data field to the titles array using the following logic:
 * • If title is not null, use title as the name.
 * 3
 * o If title is null, and story title is not null, use story title as the name.
 * • If both title and story_title are null, ignore the article.
 * 4. Based on the total pages count, fetch all the data (pagination) and perform step 3 for each.
 * 5. Return the array of titles.
 * 
 * The query response from the website is a JSON response with the following five fields:
 * • page: The current page.
 * • per page: The maximum number of results per page.
 * • total. The total number of records in the search result.
 * • total pages: The total number of pages that must be queried to get all the results.
 * • data: An array of JSON objects containing article information
 * 
 * Function Description
 * Complete the function getArticleTitles in the editor below.
 * 
 * getArticleTitles has the following parameter(s):
 * string author: the author string to search on
 * Returns:
 * string(]: a list of the articles as described
 * Sample Input:
 * author = 'epaga'
 * Sample Output:
 * A Message to Our Customers
 * Apple's declining software quality
 */
public class M_RestAPI_AuthorArticles {

    public static List<String> getAuthorArticles(String author) {
        LinkedList<String> result = new LinkedList<>();
        int curPage = 1;
        int totalPages = 1;
        String url = "https://jsonmock.hackerrank.com/api/articles?author=epaga&page=%s";
        while(curPage <= totalPages){
            String resp = httpGetRequest(String.format(url, curPage));
            JSONObject jo = (JSONObject) JSON.parse(resp);

            totalPages = Integer.parseInt(jo.get("total_pages").toString());
            JSONArray jsonDataArray = jo.getJSONArray("data");
            Iterator<Object> it = jsonDataArray.iterator();
            while (it.hasNext()){
                JSONObject item = (JSONObject) it.next();
                String title = (String) item.get("title");
                String story_title = (String) item.get("story_title");
                if(title != null){
                    result.add(title);
                }else if(story_title != null){
                    result.add(story_title);
                }
            }
            curPage++;
        }

        System.out.println(result);
        return result;
    }

    public static String httpGetRequest(String url){
        System.out.println("!!!Debug URL: " + url);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String resp = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            resp = EntityUtils.toString(response.getEntity());
            System.out.println("!!!Debug resp: " + resp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //使用nullable
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resp;
    }

    public static void main(String[] args) {
        M_RestAPI_AuthorArticles.getAuthorArticles("epaga");
    }

}

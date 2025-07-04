package com.fei.playground.algorithm.hackerRank.M_McKinsey_OA;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
 * <p>
 * The query response from the website is a JSON response with the following five fields:
 * • page: The current page.
 * • per page: The maximum number of results per page.
 * • total. The total number of records in the search result.
 * • total pages: The total number of pages that must be queried to get all the results.
 * • data: An array of JSON objects containing article information
 * <p>
 * Function Description
 * Complete the function getArticleTitles in the editor below.
 * <p>
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

    public static List<String> getAuthorArticlesByFastJSON(String author) {
        LinkedList<String> result = new LinkedList<>();
        int curPage = 1;
        int totalPages = 1;
        String url = "https://jsonmock.hackerrank.com/api/articles?author=epaga&page=%s";
        while (curPage <= totalPages) {
            String respStr = httpGetRequest(String.format(url, curPage));
            JSONObject respJsonObj = (JSONObject) JSON.parse(respStr);

            totalPages = Integer.parseInt(respJsonObj.get("total_pages").toString());
            JSONArray jsonDataArray = respJsonObj.getJSONArray("data");
            Iterator<Object> it = jsonDataArray.iterator();
            while (it.hasNext()) {
                JSONObject item = (JSONObject) it.next();
                String title = (String) item.get("title");
                String story_title = (String) item.get("story_title");
                if (title != null) {
                    result.add(title);
                } else if (story_title != null) {
                    result.add(story_title);
                }
            }
            curPage++;
        }

        System.out.println(result);
        return result;
    }

    public static List<String> getAuthorArticlesByGSON(String author) {
        LinkedList<String> result = new LinkedList<>();
        int curPage = 1;
        int totalPages = 1;
        String url = "https://jsonmock.hackerrank.com/api/articles?author=epaga&page=%s";

        while (curPage <= totalPages) {
            String respStr = httpGetRequest(String.format(url, curPage));

            JsonObject respJsonObj = JsonParser.parseString(respStr).getAsJsonObject();
            totalPages = respJsonObj.get("total_pages").getAsInt();

            JsonArray jsonDataArray = respJsonObj.getAsJsonArray("data");
            Iterator<JsonElement> it = jsonDataArray.iterator();
            while (it.hasNext()) {
                JsonObject item = it.next().getAsJsonObject();
                String title = item.get("title").getAsString();
                String story_title = item.get("story_title").getAsString();
                if (title != null) {
                    result.add(title);
                } else if (story_title != null) {
                    result.add(story_title);
                }
            }
            curPage++;
        }

        System.out.println(result);
        return result;
    }

    public static String httpGetRequest(String url) {
        System.out.println("!!!Debug URL: " + url);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String respBodyStr = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            respBodyStr = EntityUtils.toString(response.getEntity());
            System.out.println("!!!Debug resp: " + respBodyStr);
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
        return respBodyStr;
    }

    public static void main(String[] args) {
//        M_RestAPI_AuthorArticles.getAuthorArticlesByFastJSON("epaga");
        M_RestAPI_AuthorArticles.getAuthorArticlesByGSON("epaga");
    }

}

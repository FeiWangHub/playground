package com.fei.playground.algorithm.hackerRank.M_McKinsey_OA;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * REST API: Relevant Food Outlets
 * A REST API contains information about food outlets across multiple cities. Given
 * the city name, and maximum cost for 2 persons, the goal is to use the API to get
 * the list of food outlets that belong to this city and have an estimated
 */
public class TODO_RestAPI_getRelevantFoodOutlets {

    /**
     * 真实环境答案
     */
    public static List<String> getRelevantFoodOutlets(String city, int maxCost) {
        LinkedList<String> result = new LinkedList<>();
        JsonParser parser = new JsonParser();

        //1 paginate api to get all date
        int curPage = 1;
        int totalPages = 1;
        String urlFmt = "https://jsonmock.hackerrank.com/api/food_outlets?city=%s&page=%s";
        while (curPage <= totalPages) {
            String respStr = httpGet(String.format(urlFmt, city, curPage));
            JsonObject respJsonObj = parser.parse(respStr).getAsJsonObject();
            totalPages = respJsonObj.get("total_pages").getAsInt();
            System.out.println("totalPages is: " + totalPages);//TODO

            JsonArray jsonDataArray = respJsonObj.getAsJsonArray("data");
            Iterator<JsonElement> it = jsonDataArray.iterator();
            while (it.hasNext()) {
                //2 filter out result
                JsonObject item = it.next().getAsJsonObject();
                String itemCity = item.get("city").getAsString();
                int cost = item.get("estimated_cost").getAsInt();
                System.out.println(itemCity + " cost : " + cost);//TODO
                if (cost <= maxCost && itemCity.equals(city)) {
                    String name = item.get("name").getAsString();
                    result.add(name);
                }
            }
            curPage++;
        }

        return result;
    }

    //do http GET request, and return response content as String
    public static String httpGet(String url) {
        String respBodyStr = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse resp = httpClient.execute(httpGet);
            respBodyStr = EntityUtils.toString(resp.getEntity());
        } catch (IOException e) {
            //TODO
        }
        System.out.println(respBodyStr);//TODO to delete
        return respBodyStr;
    }

}

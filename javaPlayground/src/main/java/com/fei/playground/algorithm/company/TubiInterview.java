package com.fei.playground.algorithm.company;

import cn.hutool.http.HttpUtil;
import com.fei.playground.util.DateUtil;
import com.fei.playground.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class TubiInterview {

    /**
     * Fetch the following csv and get top 10 rating movies.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(String.format("---- Started, time now is %s ----", DateUtil.HHmmssSSS.format(new Date())));

        String url = "https://gist.githubusercontent.com/CatTail/18695526bd1adcc21219335f23ea5bea/raw/54045ceeae6a508dec86330c072c43be559c233b/movies.csv";

        // 1. http Fetch csv
        String csvString = HttpUtil.downloadString(url, Charset.forName("UTF-8"));

        // 2. parse csv into object list , watch out " mark, skip 1 line
        String[] rows = csvString.split(System.lineSeparator());
        int len = rows.length;
        ArrayList<Movie> movies = new ArrayList<>(len - 1);//TODO use arraylist?
        for (int i = 1; i < len; i++) {
            //1 split by comma, watch out "
            String row = rows[i];
            String[] columns;
            if (StringUtil.empty(row)) {
                continue;
            }

            if (row.contains("\"")) {
                int idxOfLastQuote = row.lastIndexOf("\"");
                int idxOfLastComma = row.lastIndexOf(",");
                columns = new String[]{row.substring(0, idxOfLastQuote + 1),
                        row.substring(idxOfLastQuote + 2, idxOfLastComma),
                        row.substring(idxOfLastComma + 1)};


            } else {
                columns = row.split(",");
            }

            //2 assemble object
            Movie m = new Movie(columns[0], Double.parseDouble(columns[1]), Double.parseDouble(columns[2]));
            movies.add(m);
        }

        System.out.println("len of movies: " + movies.size());
        System.out.println(movies.get(0).toString());

        // 3. sort/get top 10 rating movies.
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if(o1.getRating().equals(o2.getRating())){
                    return 0;
                }else if (o1.getRating()>o2.getRating()){
                    return -1;
                } else{
                    return 1;
                }
            }
        });

        //我是先测试一下
        for (int i=0; i<10; i++){
            System.out.println(movies.get(i));
        }
    }

    @Data
    @AllArgsConstructor
    @ToString
    static
    class Movie {
        String name;
        Double running_time;
        Double rating;
    }

}

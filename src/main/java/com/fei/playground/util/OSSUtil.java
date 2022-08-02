//package com.fei.playground.util;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import com.aliyun.oss.model.DeleteObjectsRequest;
//import com.aliyun.oss.model.DeleteObjectsResult;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//import java.util.List;
//
//@Component
//public class OSSUtil {
//
//    // goldstreamweb bucket相关常量
//    public static String endpoint;
//    public static String accessKeyId;
//    public static String accessKeySecret;
//    public static String gsWebBucket;
//    public static String gsWebBucketUrl;
//    public static String gsWebFilePrefix;//用于区分测试和生产
//    public static String gsWebReportJsonFile = "reports_json.js";
//
//    @Value("${aliyun.oss.endpoint}")
//    public void setEndpoint(String endpoint) {
//        cn.aresoft.common.util.OSSUtil.endpoint = endpoint;
//    }
//
//    @Value("${aliyun.oss.accessKeyId}")
//    public void setAccessKeyId(String accessKeyId) {
//        cn.aresoft.common.util.OSSUtil.accessKeyId = accessKeyId;
//    }
//
//    @Value("${aliyun.oss.accessKeySecret}")
//    public void setAccessKeySecret(String accessKeySecret) {
//        cn.aresoft.common.util.OSSUtil.accessKeySecret = accessKeySecret;
//    }
//
//    @Value("${aliyun.oss.gsWebBucket}")
//    public void setGsWebBucket(String gsWebBucket) {
//        cn.aresoft.common.util.OSSUtil.gsWebBucket = gsWebBucket;
//    }
//
//    @Value("${aliyun.oss.gsWebBucketUrl}")
//    public void setGsWebBucketUrl(String gsWebBucketUrl) {
//        cn.aresoft.common.util.OSSUtil.gsWebBucketUrl = gsWebBucketUrl;
//    }
//
//    @Value("${aliyun.oss.gsWebFilePrefix}")
//    public void setGsWebFilePrefix(String gsWebFilePrefix) {
//        cn.aresoft.common.util.OSSUtil.gsWebFilePrefix = gsWebFilePrefix;
//    }
//
//    /**
//     * 注意,名字重复的文件会直接被覆盖
//     * @param fileName 可加入文件夹路径名，如test/file.txt
//     * @param fileStream InputStream
//     * @return oss文件外网地址
//     */
//    public static String uploadFileToOSSWebBucket(String fileName, InputStream fileStream){
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        ossClient.putObject(gsWebBucket, fileName, fileStream);
//        ossClient.shutdown();
//        return gsWebBucketUrl + fileName;
//    }
//
//    /**
//     * 删除OSS中的文件
//     * @param fileKeys 例如 test/file.pdf
//     * @return count of files
//     */
//    public static int deleteFilesFromOSSWebBucket(List<String> fileKeys){
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(gsWebBucket).withKeys(fileKeys));
//        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
//        return deletedObjects.size();
//    }
//
//    /**
//     * @param ossUrl 例如 https://goldstreamweb.oss-cn-shenzhen.aliyuncs.com/test/file.pdf
//     * @return String 例如 test/file.pdf
//     */
//    public static String getKeyFromOSSWebUrl(String ossUrl){
//        int beginIdx = ossUrl.indexOf("aliyuncs.com/") + 13;
//        return ossUrl.substring(beginIdx);
//    }
//
//    public static void main(String[] args) {
////        String url = OSSUtil.uploadFileToOSS(gsWebBucket, "testFileName.txt", new FileReader("D:\\temp\\1.txt").getInputStream());
////        System.out.println(url);
////        System.out.println(getKeyFromOSSWebUrl("https://goldstreamweb.oss-cn-shenzhen.aliyuncs.com/test/file.pdf"));
//    }
//}

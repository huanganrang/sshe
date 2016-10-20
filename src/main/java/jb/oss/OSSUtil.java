package jb.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import jb.absx.F;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * Created by guxin on 2016/9/16.
 *
 * 阿里oss工具类
 */
public class OSSUtil {

    final private static Logger logger = LoggerFactory.getLogger(OSSUtil.class);

    final private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    final private static String endpointUrl = ".oss-cn-shanghai.aliyuncs.com/";
    final private static String accessKeyId = "LTAIHmlWzXmr6sDn";
    final private static String accessKeySecret = "5Noh2L7rdNAD4OK5I0snQeNUbQyDuM";
    final public static String bucketName = "ngate";

    private static OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

    /**
     * 创建Bucket(相当于建立存储文件的文件夹)
     */
    public static Boolean createBucket(String bucketName) {
        boolean result = false;
        if(!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            result = true;
        }
        //设置Bucket权限为公共读，私有写
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

        return result;
    }

    /**
     * 删除Bucket
     */
    public static Boolean deleteBucket(String bucketName) {
        boolean result = false;
        if(ossClient.doesBucketExist(bucketName)) {
            ossClient.deleteBucket(bucketName);
            result = true;
        }

        return result;
    }

    /**
     * 列举Bucket
     */
    public static List<Bucket> listBuckets() {
        List<Bucket> buckets = ossClient.listBuckets();

        return buckets;
    }

    /**
     * 设置文件的权限，此权限高于Bucket的权限
     */
    public static boolean setObjectAcl(String bucketName, String key, CannedAccessControlList acl) {
        boolean result = false;
        if(!F.empty(bucketName) && !F.empty(key)) {
            ossClient.setObjectAcl(bucketName, key, acl);
            result = true;
        }

        return result;
    }

    /**
     * 判断文件是否存在
     */
    public static Boolean doesObjectExist(String bucketName, String key) {
        boolean exist = false;
        if(!F.empty(bucketName) && !F.empty(key)) {
            exist = ossClient.doesObjectExist(bucketName, key);
        }

        return exist;
    }

    /**
     * 上传字符串，key相当于文件名，必需带后缀
     */
    public static String putString(String bucketName, String content, String key) {
        String url = "";
        if(!F.empty(bucketName) && !F.empty(content) && !F.empty(key)) {
            ossClient.putObject(bucketName, key, new ByteArrayInputStream(content.getBytes()));
            url = "http://" + bucketName + endpointUrl + key;
        }

        return url;
    }

    /**
     * 上传流，key相当于文件名，必需带后缀
     */
    public static String putInputStream(String bucketName, InputStream inputStream, String key) {
        String url = "";
        if(!F.empty(bucketName) && !F.empty(key)) {
            ossClient.putObject(bucketName, key, inputStream);
            url = "http://" + bucketName + endpointUrl + key;
        }

        return url;
    }

    /**
     * 上传本地文件，key相当于文件名，必需带后缀
     */
    public static String putFile(String bucketName, File localFile, String key) {
        String url = "";
        if(!F.empty(bucketName) && !F.empty(key)) {
            ossClient.putObject(bucketName, key, localFile);
            url = "http://" + bucketName + endpointUrl + key;
        }

        return url;
    }

    /**
     * 下载文件流(读字符串等内容)，key相当于文件名，必需带后缀
     */
    public static String getObjectContent(String bucketName, String key) {
        String result = "";
        if(!F.empty(bucketName) && !F.empty(key)) {
            OSSObject ossObject = ossClient.getObject(bucketName, key);
            BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
            try {
                while (true) {
                    String line = reader.readLine();
                    if (line == null) break;
                    result += line + "\n";
                }
                reader.close();
                ossObject.getObjectContent().close();
            } catch (Exception e) {
                logger.error("下载文件流异常", e);
            }
        }

        return result;
    }

    /**
     * 下载到本地文件，key相当于文件名，必需带后缀，file为本地文件
     */
    public static void getFile(String bucketName, String key, File file) {
        if(!F.empty(bucketName) && !F.empty(key)) {
            ossClient.getObject(new GetObjectRequest(bucketName, key), file);
        }
    }

}

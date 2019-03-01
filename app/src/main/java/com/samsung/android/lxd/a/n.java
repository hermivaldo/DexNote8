package com.samsung.android.lxd.a;

import java.util.HashMap;
import java.util.Map;

/* compiled from: UnzipErrorCodeUtil */
public class n {
    private static n a;
    private Map<Integer, String> b = new HashMap();

    public static synchronized n a() {
        n nVar;
        synchronized (n.class) {
            if (a == null) {
                a = new n();
            }
            nVar = a;
        }
        return nVar;
    }

    private n() {
        b();
    }

    public String a(int i) {
        return this.b.containsKey(Integer.valueOf(i)) ? (String) this.b.get(Integer.valueOf(i)) : "Error code not found!";
    }

    private void b() {
        this.b.put(Integer.valueOf(-1), "ERROR_NONE");
        this.b.put(Integer.valueOf(0), "ERROR_UNKNOWN");
        this.b.put(Integer.valueOf(1), "ERROR_CANCEL");
        this.b.put(Integer.valueOf(10000), "ERROR_FILE_RELATED_START");
        this.b.put(Integer.valueOf(10001), "ERROR_SAME_SRC_DST_DURING_COPY");
        this.b.put(Integer.valueOf(10002), "ERROR_SAME_SRC_DST_DURING_MOVE");
        this.b.put(Integer.valueOf(10003), "ERROR_SRC_FILE_NOT_EXIST");
        this.b.put(Integer.valueOf(10004), "ERROR_DST_FILE_NOT_EXIST");
        this.b.put(Integer.valueOf(10005), "ERROR_FILE_SIZE_NOT_SUPPORTED_FAT32");
        this.b.put(Integer.valueOf(10006), "ERROR_FILE_INVALID_SRC");
        this.b.put(Integer.valueOf(10007), "ERROR_FILE_INVALID_DST");
        this.b.put(Integer.valueOf(10008), "ERROR_FILE_MAX_ITEM");
        this.b.put(Integer.valueOf(10009), "ERROR_FILE_NOT_ENOUGH_MEMORY_DELETE_SOME_ITEMS");
        this.b.put(Integer.valueOf(10010), "ERROR_SRC_READ_FAIL");
        this.b.put(Integer.valueOf(10011), "ERROR_DST_WRITE_FAIL");
        this.b.put(Integer.valueOf(10012), "ERROR_RENAME_FAIL");
        this.b.put(Integer.valueOf(10013), "ERROR_FAIL_TO_OPEN");
        this.b.put(Integer.valueOf(10014), "ERROR_FAIL_TO_DELETE_FILE");
        this.b.put(Integer.valueOf(10015), "ERROR_FAIL_TO_DELETE_FOLDER");
        this.b.put(Integer.valueOf(10016), "ERROR_SRC_DELETE_FAIL");
        this.b.put(Integer.valueOf(10017), "ERROR_DST_NOT_ENOUGH_MEMORY");
        this.b.put(Integer.valueOf(10018), "ERROR_DST_WRITE_RESTRICTED");
        this.b.put(Integer.valueOf(19999), "ERROR_FILE_RELATED_END");
        this.b.put(Integer.valueOf(20000), "ERROR_QUOTA_RELATED_START");
        this.b.put(Integer.valueOf(20001), "ERROR_REACH_MAX_ITEM");
        this.b.put(Integer.valueOf(20002), "ERROR_OUT_OF_STORAGE");
        this.b.put(Integer.valueOf(29999), "ERROR_QUOTA_RELATED_END");
        this.b.put(Integer.valueOf(30000), "ERROR_REPOSITORY_RELATED_START");
        this.b.put(Integer.valueOf(30001), "ERROR_INSERT_FAIL");
        this.b.put(Integer.valueOf(30002), "ERROR_DELETE_FAIL");
        this.b.put(Integer.valueOf(39999), "ERROR_REPOSITORY_RELATED_END");
        this.b.put(Integer.valueOf(40000), "ERROR_NETWORK_RELATED_START");
        this.b.put(Integer.valueOf(40001), "ERROR_NETWORK_NOT_CONNECTED");
        this.b.put(Integer.valueOf(40002), "ERROR_NETWORK_UNSTABLE");
        this.b.put(Integer.valueOf(49999), "ERROR_NETWORK_RELATED_END");
        this.b.put(Integer.valueOf(50000), "ERROR_CLOUD_RELATED_START");
        this.b.put(Integer.valueOf(50001), "ERROR_CLOUD_SIGN_IN_ALREADY_IN_PROGRESS");
        this.b.put(Integer.valueOf(50002), "ERROR_CLOUD_SIGN_IN_NO_SELECTED_ACCOUNT");
        this.b.put(Integer.valueOf(50003), "ERROR_CLOUD_ALREADY_SIGNED_IN");
        this.b.put(Integer.valueOf(50004), "ERROR_CLOUD_TOKEN_FAILED");
        this.b.put(Integer.valueOf(50005), "ERROR_CLOUD_SYNC_FAILED");
        this.b.put(Integer.valueOf(50006), "ERROR_CLOUD_PARENT_NOT_EXIST");
        this.b.put(Integer.valueOf(50007), "ERROR_CLOUD_FILE_NOT_EXIST");
        this.b.put(Integer.valueOf(50008), "ERROR_CLOUD_GDPR_BLOCKED");
        this.b.put(Integer.valueOf(50009), "ERROR_CLOUD_SERVER_NOT_RESPONDING");
        this.b.put(Integer.valueOf(50010), "ERROR_CLOUD_CONNECTION_ERROR");
        this.b.put(Integer.valueOf(50011), "ERROR_CLOUD_PARSE_ERROR");
        this.b.put(Integer.valueOf(50012), "ERROR_CLOUD_SYNC_NEEDED");
        this.b.put(Integer.valueOf(59999), "ERROR_CLOUD_RELATED_END");
        this.b.put(Integer.valueOf(60000), "ERROR_COMPRESS_RELATED_START");
        this.b.put(Integer.valueOf(60001), "ERROR_COMPRESSOR_COMPRESS_FAILED");
        this.b.put(Integer.valueOf(60002), "ERROR_COMPRESSOR_EXTRACT_FAILED");
        this.b.put(Integer.valueOf(60003), "ERROR_COMPRESSOR_INVALID_SRC");
        this.b.put(Integer.valueOf(60004), "ERROR_COMPRESSOR_NO_SUCH_FILE");
        this.b.put(Integer.valueOf(60005), "ERROR_COMPRESSOR_NOT_ENOUGH_MEMORY");
        this.b.put(Integer.valueOf(60006), "ERROR_COMPRESSOR_NOT_SUPPORT_RAR");
        this.b.put(Integer.valueOf(60007), "ERROR_COMPRESSOR_NOTHING_EXTRACTED");
        this.b.put(Integer.valueOf(69999), "ERROR_COMPRESS_RELATED_END");
        this.b.put(Integer.valueOf(70000), "ERROR_LOD_DATA_NULL");
        this.b.put(Integer.valueOf(70001), "ERROR_LOD_EXTRACTED_FOLDER_NULL");
        this.b.put(Integer.valueOf(70002), "ERROR_LOD_EXTRACTED_FILE_INVALID");
        this.b.put(Integer.valueOf(70003), "ERROR_LOD_EXTRACTED_FILE_EMPTY");
    }
}

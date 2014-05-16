package com.bingzer.android.driven.dropbox;

import com.bingzer.android.driven.DrivenFile;

public class Path {
    public static final String ROOT = "/";

    public static String combine(DrivenFile drivenFile, String title){
        if(drivenFile != null)
            return combine(drivenFile.getId(), title);
        else
            return combine("", title);
    }

    public static String combine(String path1, String path2){
        String p1 = emptyIfNull(path1);
        String p2 = emptyIfNull(path2);

        if(p1.endsWith("/") || p2.startsWith("/"))
            return path1 + path2;
        else
            return path1 + "/" + path2;
    }

    public static String clean(String path){
        // make sure that path has "/"
        if(path != null && !path.startsWith("/")) return "/" + path;
        return path;
    }

    public static String clean(DrivenFile drivenFile){
        if(drivenFile != null) return clean(drivenFile.getId());
        return null;
    }

    private static String emptyIfNull(String str){
        if(str == null) return "";
        return str;
    }
}

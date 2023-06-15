package org.example.unit;

public class Config {
    public static String outDir = "null";

    public Config() {
    }

    public static String getOutDir() {
        if (OsUtils.isWindows()) {
            return outDir.equals("null") ? "D:\\study\\neuLab\\result\\res\\" : outDir;
        } else {
            return outDir.equals("null") ? "/home/wwww/data2/codeCoverage/res/" : outDir;
        }
    }
}
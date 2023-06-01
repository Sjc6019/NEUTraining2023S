package org.bill.demoproject.common.utils;

public class UUIDUtil {
    /**
     * Generate 1 UUID
     */
    public static String getOneUUID() {
        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    /**
     * Generate UUIDs by number
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] uuids = new String[number];
        for (int i = 0; i < number; i++) {
            uuids[i] = getOneUUID();
        }
        return uuids;
    }
}

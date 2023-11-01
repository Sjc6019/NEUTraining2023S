package org.bill.demoproject.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonUtils {

    private static final ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

    public static <T> List<T> readList(String json, TypeReference<List<T>> tTypeReference) {
        if (json == null || "".equals(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, tTypeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

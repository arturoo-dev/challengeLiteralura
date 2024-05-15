package com.arturodev.challengeLiteralura.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConvertData implements IConvertData {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertData(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

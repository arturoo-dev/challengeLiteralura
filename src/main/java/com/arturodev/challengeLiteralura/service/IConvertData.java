package com.arturodev.challengeLiteralura.service;

public interface IConvertData {
    <T> T convertData(String json, Class<T> tClass);
}

package com.vlad9pa.tasktest.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("json")
@Repository
public interface JsonRepository<T,Id>{
    public static final String PATH_TO_MAIN_DIR = "/home/vlad9pa/JsonStorage/";
    void setLastId(Id id);
    Id getLastId();
    void save(T entity);
    T getOne(Id id);
    void update(T entity);
    void delete(T entity);
    void deleteAll();
}
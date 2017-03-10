package com.vlad9pa.tasktest.service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}

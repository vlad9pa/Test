package com.vlad9pa.tasktest.Service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}

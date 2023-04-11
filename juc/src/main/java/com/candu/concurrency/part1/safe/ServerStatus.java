package com.candu.concurrency.part1.safe;

import java.util.Set;

public class ServerStatus {
    public final Set<String> users;
    public final Set<String> queries;

    public ServerStatus(Set<String> users, Set<String> queries) {
        this.users = users;
        this.queries = queries;
    }
    public void addUser(String u) {
        synchronized (users) {
            users.add(u);
        }
    }
    public void addQuery(String q) {
        synchronized (queries) {
            queries.add(q);
        }
    }

    public void removeUser(String u) {
        synchronized (users) {
            users.remove(u);
        }
    }

    public void removeQuery(String q) {
        synchronized (queries){
            queries.remove(q);
        }
    }
}

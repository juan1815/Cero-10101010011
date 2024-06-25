package com.fotograbados.springv1.domain.report;

import com.fotograbados.springv1.persistence.entities.Users;

import java.util.List;

public class ListarUsuariosExcel {
    private List<Users> users;

    public ListarUsuariosExcel(List<Users> users) {
        this.users = users;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}


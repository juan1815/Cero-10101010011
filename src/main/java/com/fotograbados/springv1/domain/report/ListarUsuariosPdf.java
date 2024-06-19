package com.fotograbados.springv1.domain.report;


import com.fotograbados.springv1.persistence.entities.Users;

import java.util.List;

public class ListarUsuariosPdf {
    private List<Users> users;

    public ListarUsuariosPdf(List<Users> users) {
        this.users = users;
    }

    public List<Users> getUsuarios() {
        return users;
    }

    public void setUsuarios(List<Users> users) {
        this.users = users;
    }
}

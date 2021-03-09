package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.Role;

public interface RoleService {

    Role findRoleByName(Enum name);

    Role getAnonRole();
}

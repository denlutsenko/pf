package ua.com.petfood.pf.service.impl;

import org.springframework.stereotype.Service;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.Role;
import ua.com.petfood.pf.model.RoleName;
import ua.com.petfood.pf.repository.RoleRepository;
import ua.com.petfood.pf.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role getAnonRole() {
        return findRoleByName(RoleName.ANONYMOUS);
    }

    @Override
    public Role findRoleByName(final Enum name) {
        Role role = roleRepository.findOneByName(name);

        if (role == null) {
            throw new NotFoundException("Role " + name.toString() + " not found");
        }

        return role;
    }
}

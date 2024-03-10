package com.codegym.maccenter.service.impl;

import com.codegym.maccenter.model.Role;
import com.codegym.maccenter.repository.RoleRepository;
import com.codegym.maccenter.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public Optional<Role> findRoleById(int id) {
        return roleRepository.findById(id);
    }
}

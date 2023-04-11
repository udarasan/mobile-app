package com.example.mobileapp.service.impl;


import com.example.mobileapp.dto.UserDTO;
import com.example.mobileapp.entity.User;
import com.example.mobileapp.repositroy.UserRepository;
import com.example.mobileapp.service.UserService;
import com.example.mobileapp.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            userRepository.save(modelMapper.map(userDTO,User.class));
            return VarList.Created;
        }
    }

    public int deleteUser(String username) {
        if (userRepository.existsByEmail(username)) {
            userRepository.deleteByEmail(username);
            return VarList.Created;
        } else {
            return VarList.Not_Found;
        }
    }


    public List<UserDTO> getAllUsers() {
        List<User> users=userRepository.findAll();
        return modelMapper.map(users, new TypeToken<ArrayList<UserDTO>>() {
        }.getType());
    }

    public UserDTO searchUser(String username) {
        if (userRepository.existsByEmail(username)) {
            User user=userRepository.findByEmail(username);
            return modelMapper.map(user,UserDTO.class);
        } else {
            return null;
        }
    }

    public int updateUser(UserDTO userDTO) {
        if (!userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.Created;
        }
    }
}

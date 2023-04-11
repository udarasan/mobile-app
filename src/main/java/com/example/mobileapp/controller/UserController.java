package com.example.mobileapp.controller;


import com.example.mobileapp.dto.ResponseDTO;
import com.example.mobileapp.dto.UserDTO;
import com.example.mobileapp.service.impl.UserServiceImpl;
import com.example.mobileapp.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        try {
            int res = userService.saveUser(userDTO);
            if (res==201) {
                responseDTO.setCode(VarList.Created);
                responseDTO.setMessage("success");
                responseDTO.setData(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
            } else if (res==406) {
                responseDTO.setCode(VarList.Not_Acceptable);
                responseDTO.setMessage("Username Already Use");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
            } else {
                responseDTO.setCode(VarList.Bad_Gateway);
                responseDTO.setMessage("Error");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userDTO) {
            try {
                int res = userService.updateUser(userDTO);
                if (res==201) {
                    responseDTO.setCode(VarList.Created);
                    responseDTO.setMessage("success");
                    responseDTO.setData(userDTO);
                    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
                } else if (res==406) {
                    responseDTO.setCode(VarList.Not_Acceptable);
                    responseDTO.setMessage("Username Not Available ");
                    responseDTO.setData(null);
                    return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
                } else {
                    responseDTO.setCode(VarList.Bad_Gateway);
                    responseDTO.setMessage("Error");
                    responseDTO.setData(null);
                    return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);
                }
            } catch (Exception e) {
                responseDTO.setCode(VarList.Internal_Server_Error);
                responseDTO.setMessage(e.getMessage());
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }




    @PostMapping("/delete/{username}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String username) {
        try {
            int res = userService.deleteUser(username);
            if (res == 00) {
                responseDTO.setCode(VarList.Accepted);
                responseDTO.setMessage("Success");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (res==01) {
                responseDTO.setCode(VarList.Not_Found);
                responseDTO.setMessage("Not available user");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            } else {
                responseDTO.setCode(VarList.Internal_Server_Error);
                responseDTO.setMessage("Error");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/searchUser/{username}")
    public ResponseEntity<ResponseDTO> searchUser(@PathVariable String username) {
        try {
            UserDTO userDTO = userService.searchUser(username);
            if (userDTO !=null) {
                responseDTO.setCode(VarList.Accepted);
                responseDTO.setMessage("Success");
                responseDTO.setData(userDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.Not_Found);
                responseDTO.setMessage("No User Available For this name");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(VarList.Accepted);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        System.out.println("username");
        try{
            List<UserDTO> userDTOList = userService.getAllUsers();
            responseDTO.setCode(VarList.Created);
            responseDTO.setMessage("Success");
            responseDTO.setData(userDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        }catch (Exception e){
            responseDTO.setCode(VarList.Internal_Server_Error);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}



package com.example.mobileapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  {
    @Id
    private String email;
    private String password;
    private String status;
    private String phoneNo;
    private String idPhoto;
    private String name;



}
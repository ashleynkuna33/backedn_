package com.uwc_cam_champion.backend.request.user;

import java.time.LocalDateTime;
import java.util.*;

import com.uwc_cam_champion.backend.models.Cam;
import com.uwc_cam_champion.backend.models.Deadline;
import com.uwc_cam_champion.backend.models.ModuleInfo;
import com.uwc_cam_champion.backend.models.UserModule;

public class UpdateUserRequest {

    private String name;
    private String surname;
    private String username;
    private String email;
    private Boolean isEmailVerified = false;
    private String password;
    private String phone;
    private LocalDateTime lastLogin;
    private Cam cam;
    private List<Deadline> deadlines = new ArrayList<>();
    private List<UserModule> userModules = new ArrayList<>();
    private List<ModuleInfo> createdModules = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Cam getCam() {
        return cam;
    }

    public void setCam(Cam cam) {
        this.cam = cam;
    }

    public List<Deadline> getDeadlines() {
        return deadlines;
    }

    public void setDeadlines(List<Deadline> deadlines) {
        this.deadlines = deadlines;
    }

    public List<UserModule> getUserModules() {
        return userModules;
    }

    public void setUserModules(List<UserModule> userModules) {
        this.userModules = userModules;
    }

    public List<ModuleInfo> getCreatedModules() {
        return createdModules;
    }

    public void setCreatedModules(List<ModuleInfo> createdModules) {
        this.createdModules = createdModules;
    }
}
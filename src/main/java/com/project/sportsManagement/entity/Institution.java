package com.project.sportsManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "institution")
public class Institution implements UserDetails {

    @Id
    @Column(name = "institution_code")
    private Integer institutionCode;

    @Column(name = "institution_name")
    private String institutionName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",referencedColumnName = "role_id")
    private Role authority;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id",referencedColumnName = "location_id")
    private Location address;


    @OneToMany(mappedBy = "institution",fetch = FetchType.EAGER)
    private Set<Student> students;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    public Institution() {
    }

    public Institution(Location location){
        this.address = location;
    }

    public Institution(Integer institutionCode, String institutionName, String email, String password, Role authority, Location address) {
        this.institutionCode = institutionCode;
        this.institutionName = institutionName;
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.address = address;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }


    public Integer getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(Integer institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getAuthority() {
        return authority ;
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Institution other = (Institution) obj;
        return Objects.equals(institutionCode, other.institutionCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionCode);
    }
}

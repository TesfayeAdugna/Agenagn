package com.agenagn.project.security;

import com.agenagn.project.Items;
import com.agenagn.project.Contact;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String photo;
    private String role;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Items>items;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Contact>contact;

    public boolean hasPhoto(){
        if (this.photo == null) return false;
        return true;
    }
    public boolean isAdmin(){
        if (this.role == "ROLE_ADMIN") return true;
        return false;
    }


    public String getPhotosImagePath() {
        if (photo == null || id == null) return "NO";
         
        return "/user-profile-photo/" +username+"/"+photo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.role));
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
}


package com.project.sportsManagement.service;

import com.project.sportsManagement.entity.Institution;
import com.project.sportsManagement.entity.Student;
import com.project.sportsManagement.repo.InstitutionRepository;
import com.project.sportsManagement.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            return student.get();
        }
        Optional<Institution> institution = institutionRepository.findByEmail(email);
        if (institution.isPresent()){
            return institution.get();
        }
        throw new UsernameNotFoundException("Username is invalid");
    }
}
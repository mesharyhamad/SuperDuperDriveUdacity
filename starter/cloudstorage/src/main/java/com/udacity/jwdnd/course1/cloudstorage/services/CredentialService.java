package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private UserMapper userMapper;
    private CredentialMapper credentialMapper;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getCredentials(Integer userID){
        return this.credentialMapper.getCredentials(userID);
    }

    public Credential getCredential(Integer credentialID){
        return this.credentialMapper.getCredentialByID(credentialID);
    }

   public Integer insertCredential(Credential credential){
        return this.credentialMapper.insertCredential(credential);
   }
   public  void  updateCredential(Integer credentialId, String userName, String url, String key, String password){
        this.credentialMapper.updateCredential(credentialId,userName,url,key,password);
   }

   public void deleteCredential(Integer credentialId){
        this.credentialMapper.deleteCredentialById(credentialId);
   }
}

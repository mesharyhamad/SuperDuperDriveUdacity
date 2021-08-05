package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
public class CredentialController {

    private UserService userService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public CredentialController(UserService userService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/credential")
    public String createOrUpdateCredential(Authentication authentication, @ModelAttribute("credential") CredentialForm credentialForm , Model model){
        Integer userId = userService.getUserIdByName(authentication.getName());
        String credentialIdStr = credentialForm.getCredentialid();
        String password = credentialForm.getPassword();

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        if(credentialIdStr.isEmpty()) {
            Credential credential = new Credential(0, credentialForm.getUrl(), credentialForm.getUserName(), encryptedPassword, encodedKey, userId);
           Integer index= this.credentialService.insertCredential(credential);
           System.out.println(this.credentialService.getCredential(index).getUserid());
        }else {
            this.credentialService.updateCredential(Integer.parseInt(credentialIdStr),credentialForm.getUserName(),credentialForm.getUrl(),encodedKey,encryptedPassword);
        }

        model.addAttribute("status","true");
        model.addAttribute("message","Credential has been created successfully");
        return "result";
    }
    @GetMapping("/credential/delete/{id}")
    public String deleteCredential(@PathVariable Integer id,Model model){
        this.credentialService.deleteCredential(id);
        model.addAttribute("status","true");
        model.addAttribute("message","Credential has been created successfully");
        return "result";
    }
}

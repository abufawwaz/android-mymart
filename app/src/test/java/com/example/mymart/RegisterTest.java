package com.example.mymart;

import com.example.mymart.businessLogic.Feedbacklogic;
import com.example.mymart.businessLogic.RegisterUser;
import com.example.mymart.businessLogic.checkUser;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.io.IOException;

import model.LoginRegisterResponse;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegisterTest {

    @Test
    public void RegisterTest() throws IOException{
        RegisterUser registerUser=new RegisterUser("sndis","sandis","bansbari","98603398500","male","User");
        boolean result=registerUser.register();
        assertEquals(true,result);
    }




}

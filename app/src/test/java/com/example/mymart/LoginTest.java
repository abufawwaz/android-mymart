package com.example.mymart;

import com.example.mymart.businessLogic.checkUser;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import java.io.IOException;
import java.util.List;

import Interface.Api;
import Url.Url;
import model.FeedbackModel;
import model.LoginRegisterResponse;
import model.UserModel;
import model.UserRegModel;
import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest {

    @Test
    public void test() throws IOException{
      checkUser checkUser=new checkUser("user","user");
      LoginRegisterResponse result=checkUser.checkUser();
      assertThat(result,is(IsNull.notNullValue()));

    }

}

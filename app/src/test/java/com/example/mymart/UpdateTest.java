package com.example.mymart;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.mymart.businessLogic.RegisterUser;
import com.example.mymart.businessLogic.UpdateLogic;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.io.IOException;

import Interface.Api;
import Url.Url;
import model.UpdateUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static android.content.Context.MODE_PRIVATE;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UpdateTest {


    @Test
    public void updateUser() throws IOException{
        UpdateLogic logic=new UpdateLogic("sa","sandsais","892","s");
        boolean result=logic.update();
        assertThat(result,is(IsNull.notNullValue()));
    }


}
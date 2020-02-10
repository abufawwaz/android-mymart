package com.example.mymart;

import com.example.mymart.businessLogic.AddCartLogic;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddtoCartTest {

    @Test
    public void AddtoCart(){
        AddCartLogic addCartLogic=new AddCartLogic("5","sasa","5","cap","500","clothes","red","ktm","bansbari","2");
        boolean result = addCartLogic.addtocart();
        assertThat(result,is(IsNull.notNullValue()));
    }


}

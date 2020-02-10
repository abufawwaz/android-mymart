package com.example.mymart;

import com.example.mymart.businessLogic.Feedbacklogic;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FeedbackTest {

    @Test
    public void FeedbackTest() throws IOException {
        Feedbacklogic feedbacklogic=new Feedbacklogic("sandis","sandis@sandis.com","9860339850","This is an application");
        boolean result = feedbacklogic.feedback();
        assertThat(result,is(IsNull.notNullValue()));
    }
}

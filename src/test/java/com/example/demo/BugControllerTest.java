package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Test showing the problem
 * @author JB Nizet
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BugController.class)
public class BugControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * This test fails, but it should pass.
     * When starting the app, and launching
     *     <pre>curl curl "http://localhost:8080/api/bug?foo"</pre>,
     * paramFoo is returned, which shows that the behavior is different between test and production
     */
    @Test
    public void shouldInvokeParamFooWhenFooIsPassedAsParameterWithoutEqualSign() throws Exception {
        mvc.perform(get("/api/bug?foo"))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.content().string("paramFoo"));
    }

    /**
     * This test passes
     */
    @Test
    public void shouldInvokeParamFooWhenFooIsPassedAsParameterWithEqualSign() throws Exception {
        mvc.perform(get("/api/bug?foo="))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.content().string("paramFoo"));
    }
}

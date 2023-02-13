package com.example.fitnessapp;

import static org.junit.Assert.assertEquals;

import com.example.fitnessapp.model.Calculation;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkResult() throws Exception{
        Calculation calc=new Calculation();
        calc.setNum1(5);
        calc.setNum2(6);
        calc.getResult();
        int ans=calc.getAnsTest();
        assertEquals("not equal",30,ans,0);
    }
}
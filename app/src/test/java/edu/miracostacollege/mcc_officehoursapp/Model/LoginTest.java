package edu.miracostacollege.mcc_officehoursapp.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {
    Login testLogin1;
    Login testLogin2;
    Login sameLogin;
    Login profTest;

    @Before
    public void setUp() throws Exception {
        testLogin1 = new Login("test1@gmail.com", "password1", 0);
        sameLogin = new Login("test1@gmail.com", "password1", 0);
        testLogin2 = new Login(1, "test2@gmail.com", "password2", 0);
        profTest = new Login(2,"test3@gmail.com", "password3", 1);
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void getIsProfessor() {
        assertEquals("Testing student getIsProfessor ", 0, testLogin1.getIsProfessor());
        assertEquals("Testing student getIsProfessor ", 0, testLogin2.getIsProfessor());
        assertEquals("Testing professor getIsProfessor ", 1, profTest.getIsProfessor());
    }

    @Test
    public void setIsProfessor() {
        testLogin1.setIsProfessor(1);
        assertEquals("Testing student to professor setter ", 1, testLogin1.getIsProfessor());
        testLogin1.setIsProfessor(0);
        assertEquals("Testing student to professor setter ", 0, testLogin1.getIsProfessor());
        testLogin1.setIsProfessor(2);
        assertEquals("Testing student to professor setter ", 0, testLogin1.getIsProfessor());
        testLogin1.setIsProfessor(-3);
        assertEquals("Testing student to professor setter ", 0, testLogin1.getIsProfessor());
    }

    @Test
    public void getmId() {
        assertEquals("Testing student get id ", 1, testLogin2.getmId());
        assertEquals("Testing professor get id ", 2, profTest.getmId());
        testLogin1.setmId(Integer.MIN_VALUE);
        assertEquals("Testing student set id ", Integer.MIN_VALUE, testLogin1.getmId());
        testLogin1.setmId(Integer.MAX_VALUE);
        assertEquals("Testing student set id ", Integer.MAX_VALUE, testLogin1.getmId());
    }

    @Test
    public void setmId() {
        testLogin1.setmId(3);
        assertEquals("Testing student set id ", 3, testLogin1.getmId());
        testLogin1.setmId(Integer.MIN_VALUE);
        assertEquals("Testing student set id ", Integer.MIN_VALUE, testLogin1.getmId());
        testLogin1.setmId(Integer.MAX_VALUE);
        assertEquals("Testing student set id ", Integer.MAX_VALUE, testLogin1.getmId());
    }

    @Test
    public void getmEmail() {
        assertEquals("Testing student get email ", "test2@gmail.com", testLogin2.getmEmail());
        assertEquals("Testing professor get email ", "test3@gmail.com", profTest.getmEmail());
    }

    @Test
    public void setmEmail() {
        testLogin1.setmEmail("changed1@email");
        profTest.setmEmail("changed2@email");
        assertEquals("Testing student set email ", "changed1@email", testLogin1.getmEmail());
        assertEquals("Testing professor set email ", "changed2@email", profTest.getmEmail());
    }

    @Test
    public void getmPassowrd() {
        assertEquals("Testing student get password ", "password2", testLogin2.getmPassowrd());
        assertEquals("Testing professor get password ", "password3", profTest.getmPassowrd());
    }

    @Test
    public void setmPassowrd() {
        testLogin1.setmPassowrd("changed1@email");
        profTest.setmPassowrd("changed2@email");
        assertEquals("Testing student set email ", "changed1@email", testLogin1.getmPassowrd());
        assertEquals("Testing professor set email ", "changed2@email", profTest.getmPassowrd());
    }

    @Test
    public void testToString() {
        String expectedString = "Login{mId=2, mEmail='test3@gmail.com', mPassowrd='password3', isProfessor=1}";
        assertEquals("Testing to string ", expectedString, profTest.toString());
    }

    @Test
    public void testEquals() {
        assertEquals("Testing equal using not equal logins", false, testLogin1.equals(testLogin2));
        assertEquals("Testing equal using same logins", true, testLogin1.equals(sameLogin));
    }

    @Test
    public void testHashCode() {
        assertEquals("Testing equal using not equal logins", 2080377435, testLogin1.hashCode());
    }
}
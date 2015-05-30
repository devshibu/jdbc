package testJdbc;


import baseJdbc.Base;

/**
 * Created by Shibu on 5/30/2015.
 */
public class testDB {
    public static void main(String[] args){
        Base base = new Base();
        //base.insertData("103","kerin","McGhee","2315468562","kerin@gmail.com","kerin23","kerin1234");
        //base.selectData();
        //base.deleteData("103");
        //base.updateData("2356749562", "103");
        base.selectData("104");
    }
}

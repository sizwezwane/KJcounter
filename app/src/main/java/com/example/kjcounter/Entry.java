package com.example.kjcounter;

public class Entry {
    String now;
    String categ;
    double amount;
    public Entry(String date, String categories, double amountE){
        now=date;
        categ=categories;
        amount=amountE;
    }


    @Override
    public String toString() {
        return now+"\nCategory: "+categ+"\nAmount: "+amount;
    }
}

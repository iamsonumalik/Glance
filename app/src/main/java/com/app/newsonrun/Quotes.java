package com.app.newsonrun;

/**
 * Created by malik on 1/2/16.
 */
public class Quotes {

    String []quoteslist;

    Quotes(){
        quoteslist = new String[]{
                        "Hello,",
                        "Hi,",
                        ":-)",
                        "What’s up..",
                        "What’s new..",
                        "Nice to see you.",
                        "Howdy!",
                        "Hiya!",
                        "Namaskar,",
                        "Well Hello!",
                        "Hola!",
                        "Salaam,"
        };

    }

    public String getQuoteslist(int pos) {
        return quoteslist[pos];
    }
}

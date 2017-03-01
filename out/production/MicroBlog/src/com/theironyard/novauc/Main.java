package com.theironyard.novauc;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.eclipse.jetty.server.Authentication;
import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {

        static User user;

    public static void main(String[] args) {
        // write your code here

        Spark.init();


        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    //if (user == null) {
                      //  return new ModelAndView(m, "index.html");
                    //} else {
                     //   m.put("name", user.name);
                       // return new ModelAndView(m, "index.html");
                    //}
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/Submit",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    user = new User(name);
                    response.redirect("/");
                    return "";
                })
        );

    }
}

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

        Spark.init();


        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    if (user == null) {
                        return new ModelAndView(m, "index.html");
                    } else {
                        m.put("name", user.name);
                        m.put("post",user.posts);
                        return new ModelAndView(m, "messages.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/login",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    user = new User(name);
                    response.redirect("/");
                    return "";
                })
        );
        Spark.post("/Submit",
                ((request, response) -> {
                    String Message = request.queryParams("Message");
                    user.posts.add(Message);
                    response.redirect("/");
                    return "";

                }));

    }
}

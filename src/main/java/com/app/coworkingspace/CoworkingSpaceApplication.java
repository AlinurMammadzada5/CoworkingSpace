package com.app.coworkingspace;

import com.app.coworkingspace.UI.Menu;
import com.app.coworkingspace.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CoworkingSpaceApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        Menu menu = context.getBean(Menu.class);
        menu.showMenu();

        context.close();
    }
}

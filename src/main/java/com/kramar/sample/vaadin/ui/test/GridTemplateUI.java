package com.kramar.sample.vaadin.ui.test;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

import java.util.function.Function;

@Theme("mytheme2")
@SpringUI(path = "/gridtemplate")
public class GridTemplateUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

        GridLayout gridLayout = new GridLayout(4, 3);
//        gridLayout.addStyleName("v-gridlayout");
        gridLayout.setWidth(100, Unit.PERCENTAGE);
        gridLayout.setHeight(100, Unit.PERCENTAGE);
        gridLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        HorizontalLayout headerLayout = new HorizontalLayout(new Label("Header"));
        headerLayout.setHeight(200, Unit.PIXELS);
        headerLayout.setWidth(100, Unit.PERCENTAGE);
        headerLayout.setStyleName("background-red");
        gridLayout.addComponent(headerLayout, 0 ,0, 3, 0);

        VerticalLayout verticalLayoutLeft = new VerticalLayout();
        verticalLayoutLeft.addStyleName("background-yellow");
        verticalLayoutLeft.addComponent(new Label("left"));
        verticalLayoutLeft.setHeight("600px");
//        verticalLayoutLeft.setWidth(25, Unit.PERCENTAGE);
        VerticalLayout verticalLayoutRight = new VerticalLayout();
        verticalLayoutRight.addStyleName("background-yellowgreen");
        verticalLayoutRight.setHeight("600px");
        verticalLayoutRight.addComponent(new Label("right"));

        gridLayout.addComponent(verticalLayoutLeft, 0, 1);
        gridLayout.addComponent(verticalLayoutRight, 1, 1, 3, 1);

        Function<Long, String> f = Object::toString;




        setContent(gridLayout);

    }
}

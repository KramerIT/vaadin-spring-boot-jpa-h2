package com.kramar.sample.vaadin.ui.test;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

@Theme("mytheme")
@SpringUI(path = "/template")
public class TemplateUI extends UI {

    private VerticalLayout mainVerticalLayout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout headerLayout = new VerticalLayout();
//        headerLayout.setVisible(true);
        headerLayout.addStyleName("background-red");
//        headerLayout.setWidthUndefined();
//        headerLayout.setHeightUndefined();
        headerLayout.setHeight("200px");
        headerLayout.setWidth(100, Unit.PERCENTAGE);
        headerLayout.addComponent(new Label("Header"));

        HorizontalLayout mainWindow = new HorizontalLayout();
        mainWindow.setWidth(100, Unit.PERCENTAGE);
        VerticalLayout verticalLayoutLeft = new VerticalLayout();
        verticalLayoutLeft.addStyleName("background-yellow");
        verticalLayoutLeft.addComponent(new Label("left"));
        verticalLayoutLeft.setHeight("600px");
//        verticalLayoutLeft.setWidth(25, Unit.PERCENTAGE);
        VerticalLayout verticalLayoutRight = new VerticalLayout();
        verticalLayoutRight.addStyleName("background-yellowgreen");
        verticalLayoutRight.setHeight("600px");
//        verticalLayoutRight.setWidth("600px");
//        verticalLayoutRight.setWidth(175, Unit.PERCENTAGE);
//        verticalLayoutRight.addComponent(new Label("right"));
        mainWindow.addComponents(verticalLayoutLeft, verticalLayoutRight);
        mainWindow.setExpandRatio(verticalLayoutLeft, 25);
        mainWindow.setExpandRatio(verticalLayoutRight, 75);


//        mainVerticalLayout.addComponent(headerLayout);
//        GridLayout gridLayout = new GridLayout(2, 3);
//        gridLayout.addStyleName("v-grid-row-red");
//        gridLayout.setWidthUndefined();
//        gridLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
//        gridLayout.addComponent(headerLayout, 1 ,1);


        mainVerticalLayout.addComponent(headerLayout);
        mainVerticalLayout.addComponent(mainWindow);


        HorizontalLayout footerLayout = new HorizontalLayout();
        footerLayout.setStyleName("background-blue");
        footerLayout.setHeight("150px");
        footerLayout.setWidth(100, Unit.PERCENTAGE);
        footerLayout.addComponent(new Label("Footer"));
        mainVerticalLayout.addComponent(footerLayout);

        setContent(mainVerticalLayout);

    }
}

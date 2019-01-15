package com.kramar.sample.vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;

@SpringView(name = MainView.NAME)
public class MainView extends CustomComponent implements View {

    public final static String NAME = "main";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final MenuBar menuBar = new MenuBar();
        menuBar.addItem("Email", (MenuBar.Command) item -> getUI().getNavigator().navigateTo(EmailView.NAME));
        menuBar.addItem("Error", (MenuBar.Command) item -> getUI().getNavigator().navigateTo(EmailView.NAME + "error"));
        menuBar.addItem("Access Denied", (MenuBar.Command) item -> getUI().getNavigator().navigateTo(AccessDeniedView.NAME));
        this.setCompositionRoot(menuBar);
    }
}
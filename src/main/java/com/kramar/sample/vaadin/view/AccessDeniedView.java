package com.kramar.sample.vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = AccessDeniedView.NAME)
public class AccessDeniedView extends CustomComponent implements View {

    public final static String NAME = "accessDenied";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Label("Access Denied!"));
        this.setCompositionRoot(verticalLayout);
    }
}

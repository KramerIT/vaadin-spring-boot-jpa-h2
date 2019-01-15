package com.kramar.sample.vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.io.File;
import java.net.URL;

@SpringView(name = ErrorView.NAME)
public class ErrorView extends CustomComponent implements View {

    public final static String NAME = "error";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final VerticalLayout verticalLayout = new VerticalLayout();
        ClassLoader classLoader = getClass().getClassLoader();
        URL fileUrl = classLoader.getResource("images/404-error-page.jpg");
        if (fileUrl != null) {
            File file = new File(replaceWhitespace(fileUrl.getFile()));
            FileResource resource = new FileResource(file);
            final Image image = new Image("", resource);
            verticalLayout.addComponent(image);
            this.setCompositionRoot(verticalLayout);
        } else {
            this.setCompositionRoot(new Label("404"));
        }
    }

    // for correct work in Windows OS
    private static String replaceWhitespace(String str) {
        if (str.contains("%20")) {
            str = str.replace("%20", " ");
        }
        return str;
    }

}
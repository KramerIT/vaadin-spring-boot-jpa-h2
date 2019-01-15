package com.kramar.sample.vaadin.ui;

import com.kramar.sample.vaadin.view.AccessDeniedView;
import com.kramar.sample.vaadin.view.ErrorView;
import com.kramar.sample.vaadin.view.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/")
@Theme("mytheme")
public class MainUI extends UI implements ViewAccessControl {

    @Autowired
    private final SpringViewProvider springViewProvider;

    public MainUI(final SpringViewProvider springViewProvider) {
        this.springViewProvider = springViewProvider;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final Navigator navigator = new Navigator(this, this);
        springViewProvider.setAccessDeniedViewClass(AccessDeniedView.class);

        navigator.addProvider(springViewProvider);
        navigator.navigateTo(MainView.NAME);
        navigator.setErrorView(new ErrorView());
    }

    @Override
    public boolean isAccessGranted(UI ui, String beanName) {
        if ("notAvailableView".equals(beanName)) {
            return false;
        }
        return true;
    }

}

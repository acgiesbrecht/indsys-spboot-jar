/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.common;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class LayoutController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String navigation;

    @PostConstruct
    public void init() {
        navigation = "";
    }

    public void page1(ActionEvent e) {
        this.navigation = "insert_student.xhtml";
    }

    public void page2(ActionEvent e) {
        this.navigation = "page2.xhtml";
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String nav) {
        this.navigation = nav;
    }
}

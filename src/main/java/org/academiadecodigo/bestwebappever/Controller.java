package org.academiadecodigo.bestwebappever;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String init() {

        return "ionize/index";
    }


}

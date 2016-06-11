package org.interfacedesign.controller.role;

import org.interfacedesign.core.domain.model.role.Designer;
import org.interfacedesign.core.domain.model.role.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lishaohua on 16-6-11.
 */
@Controller
@RequestMapping("/designer")
public class DesignerController {
    @Autowired
    private DesignerService designerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Designer getDesigner(@PathVariable Long id) {
        Assert.notNull(id, "designer id must not be null");
        return designerService.getDesigner(id);
    }
}

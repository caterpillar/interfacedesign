package org.interfacedesign.core.domain.model.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lishaohua on 16-6-11.
 */
@Service
public class DesignerServiceImpl implements DesignerService {
    @Autowired
    private DesignerRepository designerRepository;

    public Designer getDesigner(Long id) {
        return designerRepository.findOne(id);
    }
}

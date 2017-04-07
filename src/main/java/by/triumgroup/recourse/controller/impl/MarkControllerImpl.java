package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.MarkController;
import by.triumgroup.recourse.entity.Mark;
import by.triumgroup.recourse.service.MarkService;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MarkControllerImpl
        extends AbstractCrudController<Mark, Integer>
        implements MarkController {

    private static final Logger logger = getLogger(MarkControllerImpl.class);

    public MarkControllerImpl(MarkService markService) {
        super(markService, logger);
    }
}

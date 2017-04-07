package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.HometaskSolutionController;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.HometaskSolution;
import by.triumgroup.recourse.entity.Mark;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.MarkService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class HometaskSolutionControllerImpl
        extends AbstractCrudController<HometaskSolution, Integer>
        implements HometaskSolutionController {

    private static final Logger logger = getLogger(HometaskSolutionControllerImpl.class);
    private final MarkService markService;

    public HometaskSolutionControllerImpl(HometaskSolutionService hometaskSolutionService, MarkService markService) {
        super(hometaskSolutionService, logger);
        this.markService = markService;
    }

    @Override
    public Mark getMark(@PathVariable("solutionId") Integer solutionId) {
        return wrapServiceCall(logger, () -> {
            Optional<Mark> callResult = markService.findBySolutionId(solutionId);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }
}

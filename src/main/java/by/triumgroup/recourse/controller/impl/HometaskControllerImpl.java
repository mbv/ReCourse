package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.HometaskController;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.Hometask;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.service.HometaskService;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.util.ServiceCallWrapper;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class HometaskControllerImpl
        extends AbstractCrudController<Hometask, Integer>
        implements HometaskController {

    private static final Logger logger = getLogger(HometaskControllerImpl.class);
    private final HometaskSolutionService hometaskSolutionService;

    public HometaskControllerImpl(HometaskService hometaskService, HometaskSolutionService hometaskSolutionService) {
        super(hometaskService, logger);
        this.hometaskSolutionService = hometaskSolutionService;
    }

    @Override
    public List<HometaskSolution> getSolutions(@PathVariable("hometaskId") Integer hometaskId, Pageable pageable) {
        return ServiceCallWrapper.wrapServiceCall(logger, () -> {
            Optional<List<HometaskSolution>> solutions = hometaskSolutionService.findByHometaskId(hometaskId, pageable);
            return solutions.orElseThrow(NotFoundException::new);
        });
    }
}

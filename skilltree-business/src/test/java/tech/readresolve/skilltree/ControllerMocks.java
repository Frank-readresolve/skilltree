package tech.readresolve.skilltree;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

import tech.readresolve.skilltree.controllers.AccountController;
import tech.readresolve.skilltree.controllers.ActivityController;
import tech.readresolve.skilltree.controllers.CertificationController;
import tech.readresolve.skilltree.controllers.CertificationLevelController;
import tech.readresolve.skilltree.controllers.SkillController;
import tech.readresolve.skilltree.controllers.TrainingController;

@TestConfiguration
public class ControllerMocks {

    @MockBean
    private AccountController accountController;

    @MockBean
    private ActivityController activityController;

    @MockBean
    private CertificationLevelController certificationLevelController;

    @MockBean
    private CertificationController certificationController;

    @MockBean
    private SkillController skillController;

    @MockBean
    private TrainingController trainingController;

}

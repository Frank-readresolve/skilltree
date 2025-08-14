package tech.readresolve.skilltree.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.readresolve.skilltree.dtos.in.SkillCreate;
import tech.readresolve.skilltree.entities.Activity;
import tech.readresolve.skilltree.entities.Skill;
import tech.readresolve.skilltree.repositories.ActivityReposiroty;
import tech.readresolve.skilltree.repositories.SkillReposiroty;

@Service
public class SkillService extends BaseService {

    private final SkillReposiroty skills;

    private final ActivityReposiroty activities;

    SkillService(SkillReposiroty skills, ActivityReposiroty activities) {
	this.skills = skills;
	this.activities = activities;
    }

    @Transactional
    public void create(SkillCreate inputs) {
	Skill entity = new Skill();
	Activity activity = activities.getReferenceById(inputs.activityId());
	entity.setActivity(activity);
	entity.setCode(inputs.code());
	entity.setName(inputs.name());
	entity.setDescription(inputs.description());
	skills.save(entity);
    }

}

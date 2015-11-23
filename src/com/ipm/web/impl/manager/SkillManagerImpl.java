package com.ipm.web.impl.manager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipm.web.dto.Skill;
import com.ipm.web.interfaces.SkillManager;
import com.ipm.web.interfaces.dao.SkillDao;

public class SkillManagerImpl implements SkillManager {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private SkillDao skillDao;

	@Override
	public List<Skill> getSkills(String username, int projectId) {
		return skillDao.getSkills(username, projectId);
	}

	@Override
	public void createSkill(Skill skill) {
		skillDao.createSkill(skill);

	}

	@Override
	public void updateSkill(Skill skill) {
		skillDao.updateSkill(skill);

	}

	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	@Override
	public void removeSkill(Skill skill) {
		this.skillDao.removeSkill(skill);

	}

	@Override
	public void getSkill(Skill skill) {
		this.skillDao.getSkill(skill);
	}

}

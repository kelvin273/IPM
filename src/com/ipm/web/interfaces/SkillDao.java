package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Skill;

public interface SkillDao {

	public List<Skill> getSkills(String username, int projectId);

	public void createSkill(Skill skill);
	
	public void removeSkill(Skill skill);
}

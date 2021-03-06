package com.ipm.web.interfaces.dao;

import java.util.List;

import com.ipm.web.dto.Skill;

public interface SkillDao {

	public List<Skill> getSkills(String username, long projectId);

	public void createSkill(Skill skill);
	
	public void removeSkill(Skill skill);
	
	public void updateSkill(Skill skill);

	public void getSkill(Skill skillId);
}

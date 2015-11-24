package com.ipm.web.interfaces;

import java.util.List;

import com.ipm.web.dto.Skill;

public interface SkillManager {

    public List<Skill> getSkills(String username, long projectId);
    
    public void createSkill(Skill skill);
    
    public void removeSkill(Skill skill);
    
    public void updateSkill(Skill skill);

	public void getSkill(Skill skill);

}

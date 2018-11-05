package com.bit.tatab.main.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit.tatab.main.vo.CommentVO;
import com.bit.tatab.main.vo.MainBackgroundVO;
import com.bit.tatab.main.vo.ProjectVO;


@Repository
public class MainDAOImpl implements MainDAO {

	@Inject
	private SqlSession sqlSession;

	@Override
	public void insert(ProjectVO project) {
		sqlSession.insert("insert", project);
	}
	
	@Override
	public void insertMember(ProjectVO project, String login_email) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("project", project);
		param.put("login_email", login_email);
		sqlSession.insert("insert_prj_member_t", param);
	}

	@Override
	public List<ProjectVO> selectAllProject(String login_email) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("login_email", login_email);
		
		List<ProjectVO> projectList = sqlSession.selectList("selectAllProject", param);
		List<ProjectVO> projectNameList = new ArrayList<ProjectVO>();
		
		for(int i=0; i<projectList.size(); i++) {
			projectNameList.add(projectList.get(i));
		}
		
		return projectNameList;
	}

	@Override
	public void modifyComment(CommentVO commentVO) {
		sqlSession.update("modifyComment", commentVO);
	}

	@Override
	public void modifyBackgroundImage(MainBackgroundVO mainBackgroundVO) {
		sqlSession.insert("modifyBackgroundImage", mainBackgroundVO);
	}

	@Override
	public MainBackgroundVO findBackgroundImage(String login_email) {
		MainBackgroundVO backgroundImage = sqlSession.selectOne("findBackgroundImage", login_email);
		return backgroundImage;
	}
	
	

}

package service;

import dao.DAOFactory;
import dao.StudyDAO;
import entity.Study;

import java.util.List;

public class StudyService {
    private StudyDAO studyDAO = DAOFactory.getStudyDAO();

    public void add(Study entity) {
        studyDAO.add(entity);
    }

    public Study getById(int id) {
        return studyDAO.getById(id);
    }

    public List<Study> getAll() {
        return studyDAO.getAll();
    }

    public void edit(Study entity) {
        studyDAO.edit(entity);
    }

    public void remove(Study entity) {
        studyDAO.remove(entity);
    }
}
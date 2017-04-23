package com.coura.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coura.model.Course;							  
import com.coura.model.CourseInstructorWrapper;
import com.coura.model.Instructor;

@Transactional
@Repository
public class InstructorDaoImpl implements InstructorDao {
	
	private static final Logger logger = LoggerFactory.getLogger(InstructorDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public boolean addInstructor(CourseInstructorWrapper wrapper) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(wrapper.getInstructor());
		session.flush();
		session.clear();
		session.persist(wrapper.getCourseInstructor());
		session.flush();
		session.clear();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public Instructor findInstructorByEmailId(String emailId) {
		Integer instructorId = getIdForInstructor(emailId);
		if(instructorId == null)
			return null;
		Session session = this.sessionFactory.getCurrentSession();
		Instructor instructor = (Instructor) session.get(Instructor.class, instructorId);
		return instructor;
	}
	
	public boolean isExistingInstructor(String emailId) {
		Instructor i = findInstructorByEmailId(emailId);
		if (i != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void updateInstructor(Instructor instructor) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Instructor set firstName = :firstName, lastName = :lastName," + 
				" emailId = :emailId, researchInterest = :researchInterest where id = :id");
		query.setParameter("firstName", instructor.getFirstName());
		query.setParameter("lastName", instructor.getLastName());
		query.setParameter("emailId", instructor.getEmailId());
		query.setParameter("researchInterest", instructor.getResearchInterest());
		query.setParameter("id", instructor.getId());
		query.executeUpdate();
	}
	
	public void deleteInstructor(Integer instructorId) {
		Session session = this.sessionFactory.getCurrentSession();
		
		// Delete from instructorrating table
		Query query1 = session.createQuery("delete from InstructorRating where instructorId = :instructorId");
		query1.setParameter("instructorId", instructorId);
		query1.executeUpdate();
		session.flush();
		session.clear();
				
		// Delete from instructorreview table
		Query query2 = session.createQuery("delete from InstructorReview where instructorId = :instructorId");
		query2.setParameter("instructorId", instructorId);
		query2.executeUpdate();
		session.flush();
		session.clear();
		// Delete from courseinstructor table
		Query query3 = session.createQuery("delete from CourseInstructor where instructorId = :instructorId");
		query3.setParameter("instructorId", instructorId);
		query3.executeUpdate();
		session.flush();
		session.clear();
			
		// Delete from course table
		Query query4 = session.createQuery("delete from Instructor where id = :instructorId");
		query4.setParameter("instructorId", instructorId);
		query4.executeUpdate();
		session.flush();
		session.clear();
	}
	
	@SuppressWarnings("unchecked")
	public List<Instructor> listAllInstructors() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select i.id as id, i.firstName as firstName, i.lastName as lastName, i.researchInterest as" + 
				" researchInterest, i.emailId as emailId from Instructor i").setResultTransformer(Transformers.aliasToBean(Instructor.class));
		List<Instructor> list = query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Instructor> getInstructorsForCourse(Integer courseId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select i.firstName as firstName, i.lastName as lastName, i.id as id from Instructor i, CourseInstructor ci" + 
				" where i.id = ci.instructorId and ci.courseId = :courseId").setResultTransformer(Transformers.aliasToBean(Instructor.class));
		query.setParameter("courseId", courseId);
		List<Instructor> instructors = query.list();
		return instructors;
	}
 
	@SuppressWarnings("unchecked")
	public List<Instructor> getInstructorById(Integer instructorId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select i.firstName as firstName, i.lastName as lastName, i.emailId as emailId, " + 
				"i.researchInterest as researchInterest from Instructor i where i.id = :instructorId").setResultTransformer(Transformers.aliasToBean(Instructor.class));
		query.setParameter("instructorId", instructorId);
		List<Instructor> instructor = query.list();
		return instructor;
	}
	
	@SuppressWarnings("unchecked")
	public Integer getIdForInstructor(String emailId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select id as id from Instructor where emailId like :emailId").setResultTransformer(Transformers.aliasToBean(Instructor.class));
		query.setParameter("emailId", emailId+"%");
		List<Instructor> instructor = query.list();
		if(instructor.isEmpty())
			return null;
		return instructor.get(0).getId();
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getCourseForInstructor(Integer instructorId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c.id as id, c.courseNumber as courseNumber, c.courseName as courseName, c.prerequisite as prerequisite, c.description as description from Course c, CourseInstructor ci" + 
				" where c.id = ci.courseId and ci.instructorId = :instructorId").setResultTransformer(Transformers.aliasToBean(Course.class));
		query.setParameter("instructorId", instructorId);
		List<Course> course = query.list();
		return course;
	}
}
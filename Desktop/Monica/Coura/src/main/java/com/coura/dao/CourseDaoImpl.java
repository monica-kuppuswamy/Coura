package com.coura.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coura.model.Course;
import com.coura.model.CourseInstructor;
import com.coura.model.Instructor;
import com.coura.model.Users;
import java.util.HashSet;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {
	
	Set<Course> mrsc=new HashSet<Course>();
	
	private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public Course findCourseByNumber(Integer courseId) {
		Session session = this.sessionFactory.getCurrentSession();
		Course course = (Course) session.get(Course.class, courseId);
		return course;
	}
	
	public boolean isExistingCourse(Integer courseId) {
		Course c = findCourseByNumber(courseId);
		if (c != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Course findCourseByNumber(String courseNumber) {
		Integer courseId = getIdForCourse(courseNumber);
		if(courseId == null)
			return null;
		Session session = this.sessionFactory.getCurrentSession();
		Course course = (Course) session.get(Course.class, courseId);
		return course;
	}
	
	public boolean isExistingCourse(String courseNumber) {
		Course c = findCourseByNumber(courseNumber);
		if (c != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void saveCourses(Course course) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(course);
	}
	
	public void updateCourse(Course course) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Course set courseNumber = :courseNumber, courseName = :courseName," + 
				" prerequisite = :preRequisite, description = :cDesc where id = :cNo");
		query.setParameter("courseNumber", course.getCourseNumber());
		query.setParameter("courseName", course.getCourseName());
		query.setParameter("preRequisite", course.getPrerequisite());
		query.setParameter("cDesc", course.getDescription());
		query.setParameter("cNo", course.getId());
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> listAllCourses() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c.id as id, c.courseNumber as courseNumber, c.courseName as courseName, c.prerequisite" + 
				" as prerequisite, c.description as description from Course c").setResultTransformer(Transformers.aliasToBean(Course.class));
		List<Course> courseList = query.list();
		return courseList;
	}
	
	public void deleteCourse(Integer courseId) {
		Session session = this.sessionFactory.getCurrentSession();
		Course course = findCourseByNumber(courseId);
		if (course != null) {
			
			// Delete from courserating table
			Query query1 = session.createQuery("delete from CourseRating where courseId = :courseId");
			query1.setParameter("courseId", course.getId());
			query1.executeUpdate();
			session.flush();
			session.clear();
			
			// Delete from instructorrating table
			Query query5 = session.createQuery("delete from InstructorRating where courseId = :courseId");
			query5.setParameter("courseId", course.getId());
			query5.executeUpdate();
			session.flush();
			session.clear();
			
			// Delete from coursereview table
			Query query4 = session.createQuery("delete from CourseReview where courseId = :courseId");
			query4.setParameter("courseId", course.getId());
			query4.executeUpdate();
			session.flush();
			session.clear();			   
				
			// Delete from courseinstructor table
			Query query2 = session.createQuery("delete from CourseInstructor where courseId = :courseId");
			query2.setParameter("courseId", course.getId());
			query2.executeUpdate();
			session.flush();
			session.clear();
			
			Query query6 = session.createQuery("delete from StudentCourse where courseId = :courseId");
			query6.setParameter("courseId", course.getId());
			query6.executeUpdate();
			session.flush();
			session.clear();
				
			// Delete from course table
			Query query3 = session.createQuery("delete from Course where id = :courseId");
			query3.setParameter("courseId", course.getId());
			query3.executeUpdate();
			session.flush();
			session.clear();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getCourseById(Integer courseId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName, c.prerequisite as prerequisite, " + 
				"c.description as description from Course c where c.id = :courseId").setResultTransformer(Transformers.aliasToBean(Course.class));
		query.setParameter("courseId", courseId);
		List<Course> course = query.list();
		if(!mrsc.contains(course))
		mrsc.add(course.get(0));
		return course;
	}
	
	@SuppressWarnings("unchecked")
	public Integer getIdForCourse(String courseNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c.id as id from Course c where c.courseNumber = :courseNumber").setResultTransformer(Transformers.aliasToBean(Course.class));
		query.setParameter("courseNumber", courseNumber);
		List<Course> course = query.list();
		if(course.isEmpty())
			return null;
		return course.get(0).getId();
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getIdForInstructor(String instructorName) {
		Session session = this.sessionFactory.getCurrentSession();
		if(instructorName.equals("")) {
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(0);
			ids.add(0);
			System.out.println(ids);
			return ids;
		}
		else {
		Query query = session.createQuery("select i.id as id from Instructor i where :instructorName LIKE CONCAT('%', i.firstName, '%') and :instructorName LIKE CONCAT('%', i.lastName, '%')").setResultTransformer(Transformers.aliasToBean(Instructor.class));
		query.setParameter("instructorName", instructorName);
		List<Instructor> instructor = query.list();
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < instructor.size(); i++) {
			ids.add(instructor.get(i).getId());
		}
		return ids;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> getCoursesForInstructor(Integer instructorId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c, CourseInstructor ci" + 
				" where c.id = ci.courseId and ci.instructorId = :instructorId").setResultTransformer(Transformers.aliasToBean(Course.class));
		query.setParameter("instructorId", instructorId);
		List<Course> course = query.list();
		return course;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course> searchCourses(String courseNumber, String courseName, String areaOfInterest, String instructorName) {
		Session session = this.sessionFactory.getCurrentSession();
		if(courseNumber.equals("undefined")) {
			courseNumber = "";
		}
		if(courseName.equals("undefined")) {
			courseName = "";
		}
		if(areaOfInterest.equals("undefined")) {
			areaOfInterest = "";
		}
		if(instructorName.equals("undefined")) {
			instructorName = "";
		}
		if(!courseNumber.equals("") && !courseName.equals("") && !areaOfInterest.equals("") && !instructorName.equals("")) {
			List<Integer> ids = getIdForInstructor(instructorName);
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c, CourseInstructor ci" + 
				" where c.courseNumber = :courseNumber and c.courseName = :courseName and c.description LIKE CONCAT('%', :areaOfInterest, '%') and c.id = ci.courseId and ci.instructorId IN (:ids)").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseNumber", courseNumber);
			query.setParameter("courseName", courseName);
			query.setParameter("areaOfInterest", areaOfInterest);
			query.setParameterList("ids", ids);
			List<Course> course = query.list();
			return course;
		}
		if(courseNumber.equals("") && !courseName.equals("") && !areaOfInterest.equals("") && !instructorName.equals("")) {
			List<Integer> ids = getIdForInstructor(instructorName);
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c, CourseInstructor ci" + 
				" where c.courseName = :courseName and c.description LIKE CONCAT('%', :areaOfInterest, '%') and c.id = ci.courseId and ci.instructorId IN (:ids)").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseName", courseName);
			query.setParameter("areaOfInterest", areaOfInterest);
			query.setParameterList("ids", ids);
			List<Course> course = query.list();
			return course;
		}
		if(!courseNumber.equals("") && courseName.equals("") && !areaOfInterest.equals("") && !instructorName.equals("")) {
			List<Integer> ids = getIdForInstructor(instructorName);
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c, CourseInstructor ci" + 
					" where c.courseNumber = :courseNumber and c.description LIKE CONCAT('%', :areaOfInterest, '%') and c.id = ci.courseId and ci.instructorId IN (:ids)").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseNumber", courseNumber);
			query.setParameter("areaOfInterest", areaOfInterest);
			query.setParameterList("ids", ids);
			List<Course> course = query.list();
			return course;
		}
		if(!courseNumber.equals("") && !courseName.equals("") && areaOfInterest.equals("") && !instructorName.equals("")) {
			List<Integer> ids = getIdForInstructor(instructorName);
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c, CourseInstructor ci" + 
				" where c.courseNumber = :courseNumber and c.courseName = :courseName and c.id = ci.courseId and ci.instructorId IN (:ids)").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseNumber", courseNumber);
			query.setParameter("courseName", courseName);
			query.setParameterList("ids", ids);
			List<Course> course = query.list();
			return course;
		}
		if(!courseNumber.equals("") && !courseName.equals("") && !areaOfInterest.equals("") && instructorName.equals("")) {
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c" + 
				" where c.courseNumber = :courseNumber and c.courseName = :courseName and c.description LIKE CONCAT('%', :areaOfInterest, '%')").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseNumber", courseNumber);
			query.setParameter("courseName", courseName);
			query.setParameter("areaOfInterest", areaOfInterest);
			List<Course> course = query.list();
			return course;
		}
		if(courseNumber.equals("") && courseName.equals("") && !areaOfInterest.equals("") && !instructorName.equals("")) {
			List<Integer> ids = getIdForInstructor(instructorName);
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c, CourseInstructor ci" + 
				" where c.description LIKE CONCAT('%', :areaOfInterest, '%') and c.id = ci.courseId and ci.instructorId IN (:ids)").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("areaOfInterest", areaOfInterest);
			query.setParameterList("ids", ids);
			List<Course> course = query.list();
			return course;
		}
		if(courseNumber.equals("") && !courseName.equals("") && areaOfInterest.equals("") && !instructorName.equals("")) {
			List<Integer> ids = getIdForInstructor(instructorName);
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c, CourseInstructor ci" + 
				" where c.courseName = :courseName and c.id = ci.courseId and ci.instructorId IN (:ids)").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseName", courseName);
			query.setParameterList("ids", ids);
			List<Course> course = query.list();
			return course;
		}
		if(courseNumber.equals("") && !courseName.equals("") && !areaOfInterest.equals("") && instructorName.equals("")) {
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c" + 
				" where c.courseName = :courseName and c.description LIKE CONCAT('%', :areaOfInterest, '%')").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseName", courseName);
			query.setParameter("areaOfInterest", areaOfInterest);
			List<Course> course = query.list();
			return course;
		}
		if(!courseNumber.equals("") && courseName.equals("") && areaOfInterest.equals("") && !instructorName.equals("")) {
			List<Integer> ids = getIdForInstructor(instructorName);
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c, CourseInstructor ci" + 
				" where c.courseNumber = :courseNumber and c.id = ci.courseId and ci.instructorId IN (:ids)").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseNumber", courseNumber);
			query.setParameterList("ids", ids);
			List<Course> course = query.list();
			return course;
		}
		if(!courseNumber.equals("") && courseName.equals("") && !areaOfInterest.equals("") && instructorName.equals("")) {
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c" + 
				" where c.courseNumber = :courseNumber and c.description LIKE CONCAT('%', :areaOfInterest, '%')").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseNumber", courseNumber);
			query.setParameter("areaOfInterest", areaOfInterest);
			List<Course> course = query.list();
			return course;
		}
		if(!courseNumber.equals("") && !courseName.equals("") && areaOfInterest.equals("") && instructorName.equals("")) {
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c" + 
				" where c.courseNumber = :courseNumber and c.courseName = :courseName").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseNumber", courseNumber);
			query.setParameter("courseName", courseName);
			List<Course> course = query.list();
			return course;
		}
		if(!courseNumber.equals("") && courseName.equals("") && areaOfInterest.equals("") && instructorName.equals("")) {
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c" + 
				" where c.courseNumber = :courseNumber").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseNumber", courseNumber);
			List<Course> course = query.list();
			return course;
		}
		if(courseNumber.equals("") && !courseName.equals("") && areaOfInterest.equals("") && instructorName.equals("")) {
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c" + 
				" where c.courseName = :courseName").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("courseName", courseName);
			List<Course> course = query.list();
			return course;
		}
		if(courseNumber.equals("") && courseName.equals("") && !areaOfInterest.equals("") && instructorName.equals("")) {
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c" + 
				" where c.description LIKE CONCAT('%', :areaOfInterest, '%')").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameter("areaOfInterest", areaOfInterest);
			List<Course> course = query.list();
			return course;
		}
		if(courseNumber.equals("") && courseName.equals("") && areaOfInterest.equals("") && !instructorName.equals("")) {
			List<Integer> ids = getIdForInstructor(instructorName);
			Query query = session.createQuery("select c.courseNumber as courseNumber, c.courseName as courseName from Course c, CourseInstructor ci" + 
				" where c.id = ci.courseId and ci.instructorId IN (:ids)").setResultTransformer(Transformers.aliasToBean(Course.class));
			query.setParameterList("ids", ids);
			List<Course> course = query.list();
			return course;
		}
		List<Course> course = new ArrayList<Course>();
		return course;
		
	}
	
	@Override
	public List<Course> listMostRecentlySearchedCourses() {
		
		Set<Course> set=new HashSet<Course>(mrsc);
		List<Course> x=new ArrayList<Course>(set);
		if(x.size()>4)
			x.subList(0,x.size()-4).clear();
		return x;
	}

	@Override
	public List<Instructor> listAllInstructors() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select i.id as id, i.firstName as firstName, i.lastName as lastName, i.emailId as emailId, i.researchInterest as researchInterest from Instructor i").setResultTransformer(Transformers.aliasToBean(Instructor.class));
		List<Instructor> instuctorList = query.list();
		return instuctorList;
	}

	@Override
	public List<Instructor> searchInstructors(String firstName, String lastName, String areaOfInterest) {
		
		if (firstName.equalsIgnoreCase("undefined")) {
			firstName = " ";
		}
		if (lastName.equalsIgnoreCase("undefined")) {
			lastName = " ";
		}
		if (areaOfInterest.equalsIgnoreCase("undefined")) {
			areaOfInterest = " ";
		}
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select i.id as id, i.firstName as firstName, i.lastName as lastName, i.emailId as emailId, " + 
				"i.researchInterest as researchInterest from Instructor i where i.firstName like :fname and i.lastName like :lname and " + 
				"i.researchInterest like :researchinterest").setResultTransformer(Transformers.aliasToBean(Instructor.class));
		query.setParameter("fname", firstName.trim() + "%");
		query.setParameter("lname", lastName.trim() + "%");
		query.setParameter("researchinterest", areaOfInterest.trim() + "%");
		List<Instructor> instuctorList = query.list();
		return instuctorList;
	}
	
	@Override
	public List<Course> listRecommendedCourses(Integer courseId) {
		
		List<Course> rc=new ArrayList<Course>();
		List<Course>rV=new ArrayList<Course>(mrsc);
		String []areas={"Architecture","Programming","Software","Design","Network"};
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c.id as id, c.courseNumber as courseNumber, c.courseName as courseName, c.prerequisite" + 
				" as prerequisite, c.description as description from Course c").setResultTransformer(Transformers.aliasToBean(Course.class));
		List<Course> courseList = query.list();
		for (int k = 0; k < courseList.size(); k++) {
			if (courseList.get(k).getId() == courseId) {
				courseList.remove(k);
			}
		}
		for(int i=0;i<rV.size();i++)
			for(int j=0;j<areas.length;j++)
				if(rV.get(i).getDescription().toLowerCase().contains(areas[j].toLowerCase()))	
					for(int k=0;k<courseList.size()-1;k++)
						if(courseList.get(k).getDescription().toLowerCase().contains(areas[j].toLowerCase()))
							rc.add(courseList.get(k));

		Set<Course> temp=new HashSet<Course>(rc);
		List<Course> tEmP =new ArrayList<Course>(temp);

		return tEmP;
	}

}
package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Admin;
import com.app.entity.Master;
import com.app.entity.MasterPK;
import com.app.entity.Student;
import com.app.entity.Test;

public interface IMasterRepository extends JpaRepository<Master, MasterPK> {

	// find by std and test
	List<Master> findByStudentTblAndTestTbl(Student std, Test test);

	// find by std and test and que
	List<Master> findByAdminTblAndStudentTblAndTestTbl(Admin admin, Student std, Test test);

	// find by admin
	List<Master> findByAdminTbl(Admin admin);

	// get marks sum by id's
	@Query("SELECT SUM(m.resultObtainedMarks) FROM Master m WHERE m.masterId.studentPrn = :studentPrn AND m.masterId.testId = :testId AND m.masterId.adminId = :adminId")
	Integer getTotalMarks(@Param("studentPrn") Long studentPrn, @Param("testId") Long testId,
			@Param("adminId") Long adminId);

	// get test list by admin id
	@Query("SELECT m.masterId.testId FROM Master m WHERE m.masterId.adminId = :adminId GROUP BY m.masterId.testId")
	List<Long> findByAdminIdGroupByTestId(@Param("adminId") Long adminId);

//get question list by test id
	@Query("SELECT m.masterId.questionId FROM Master m WHERE m.masterId.adminId = :adminId AND m.masterId.testId = :testId GROUP BY m.masterId.questionId")
	List<Long> findByTestIdGroupByQuestionId(@Param("adminId") Long adminId, @Param("testId") Long testId);

//get student list by test id, admin id
	@Query("SELECT m.masterId.studentPrn FROM Master m WHERE m.masterId.adminId = :adminId AND m.masterId.testId = :testId GROUP BY m.masterId.studentPrn")
	List<Long> findByTestIdGroupByStudentPrn(@Param("adminId") Long adminId, @Param("testId") Long testId);
	
//get question list by test id, admin id, student
	@Query("SELECT m.masterId.questionId FROM Master m WHERE m.masterId.adminId = :adminId AND m.masterId.testId = :testId AND m.masterId.studentPrn = :studentPrn GROUP BY m.masterId.questionId")
	List<Long> findQuestionsForExam(@Param("adminId") Long adminId, @Param("testId") Long testId, @Param("studentPrn") Long studentPrn);
}

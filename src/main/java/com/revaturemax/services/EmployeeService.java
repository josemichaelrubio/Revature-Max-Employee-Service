package com.revaturemax.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revaturemax.dtos.*;
import com.revaturemax.models.*;
import com.revaturemax.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private QuizScoreRepository quizScoreRepository;
	@Autowired
	private TopicCompetencyRepository topicCompetencyRepository;
	@Autowired
	private QCFeedbackRepository qcFeedbackRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private CurriculumService curriculumService;

	public ResponseEntity<String> getEmployee(long employeeId, Set<String> fields) {
		Employee employee = employeeRepository.findById(employeeId).orElse(null);
		if (employee == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		EmployeeDTO employeeDTO = new EmployeeDTO(employee);
		if (fields != null && fields.contains("quiz-scores")) {
			employeeDTO.setQuizScores(getQuizScoresAndInfoForEmployee(employee));
		}
		if (fields != null && fields.contains("topic-competencies")) {
			employeeDTO.setTopicCompetencies(getTopicCompetenciesAndInfoForEmployee(employee));
		}
		if (fields != null && fields.contains("qc-feedbacks")) {
			employeeDTO.setQcFeedbacks(getQCFeedbackAndInfoForEmployee(employee));
		}

		try {
			return new ResponseEntity<>(objectMapper.writer().writeValueAsString(employeeDTO), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private List<EmployeeQuizScore> getQuizScoresAndInfoForEmployee(Employee employee) {
		List<QuizScore> quizScores = quizScoreRepository.findByEmployee(employee);
		Map<Long, QuizScore> quizScoreMap = quizScores.stream().collect(Collectors.toMap(quizScore -> quizScore.getId().getQuizId(), quizScore -> quizScore));

		List<Long> quizIds = new ArrayList<>(quizScoreMap.keySet());

		List<QuizDTO> quizDTOS = curriculumService.getQuizzesByListOfIds(quizIds);
		List<EmployeeQuizScore> employeeQuizScores = new ArrayList<>();
		quizDTOS.forEach(quizDTO -> {
			employeeQuizScores.add(new EmployeeQuizScore(new QuizScoreId(employee.getId(), quizDTO.getId()), quizScoreMap.get(quizDTO.getId()).getScore(), quizDTO.getName()));
		});
		return employeeQuizScores;
	}

	private List<EmployeeTopicCompetency> getTopicCompetenciesAndInfoForEmployee(Employee employee) {
		List<TopicCompetency> topicCompetencies = topicCompetencyRepository.findByEmployee(employee);
		System.out.println(topicCompetencies);
		Map<Long, TopicCompetency> topicCompetencyMap = topicCompetencies.stream().collect(Collectors.toMap(topicCompetency -> topicCompetency.getId().getTopicId(), topicCompetency -> topicCompetency));

		List<Long> topicIds = new ArrayList<>(topicCompetencyMap.keySet());

		List<TopicDTO> topicDTOS = curriculumService.getTopicsByListOfIds(topicIds);
		List<EmployeeTopicCompetency> employeeTopicCompetencies = new ArrayList<>();
		topicDTOS.forEach(topicDTO ->
				employeeTopicCompetencies.add(new EmployeeTopicCompetency(new TopicCompetencyId(employee.getId(), topicDTO.getTopicId()), topicCompetencyMap.get(topicDTO.getTopicId()).getCompetency(), topicDTO.getTechName())));
		return employeeTopicCompetencies;
	}

	private List<EmployeeQCFeedback> getQCFeedbackAndInfoForEmployee(Employee employee) {
		List<QCFeedback> qcFeedbacks = qcFeedbackRepository.findByEmployee(employee);
		Map<Long, QCFeedback> qcFeedbackMap = qcFeedbacks.stream().collect(Collectors.toMap(qcFeedback -> qcFeedback.getId().getQcId(), qcFeedback -> qcFeedback));

		List<Long> qcIds = new ArrayList<>(qcFeedbackMap.keySet());

		List<QCDTO> qcdtos = curriculumService.getQCNamesByListOfIds(qcIds);
		List<EmployeeQCFeedback> employeeQCFeedbacks = new ArrayList<>();
		qcdtos.forEach(qcdto -> {
			employeeQCFeedbacks.add(new EmployeeQCFeedback(new QCFeedbackId(employee.getId(), qcdto.getId()), qcFeedbackMap.get(qcdto.getId()).getAssociateRating(), qcFeedbackMap.get(qcdto.getId()).getInstructorFeedback(), qcdto.getName()));
		});
		return employeeQCFeedbacks;
	}

	public Employee getEmployeeByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}

	public ResponseEntity<String> getEmployees(Set<String> emails) {
		List<Employee> employees = employeeRepository.findByEmailIn(emails);
		try {
			return new ResponseEntity<>(objectMapper.writer().writeValueAsString(employees), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> getEmployees(Set<Long> employeeIds, Set<String> fields) {
		List<Employee> employees = employeeRepository.findAllById(employeeIds);

		List<QuizScore> quizScores = null;
		if (fields != null && fields.contains("quiz-scores")) {
			quizScores = quizScoreRepository.findByEmployeeIn(employees);
		}
		List<TopicCompetency> topicCompetencies = null;
		if (fields != null && fields.contains("topic-competencies")) {
			topicCompetencies = topicCompetencyRepository.findByEmployeeIn(employees);
		}
		List<QCFeedback> qcFeedbacks = null;
		if (fields != null && fields.contains("qc-feedbacks")) {
			qcFeedbacks = qcFeedbackRepository.findByEmployeeIn(employees);
		}

		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeeDTO employeeDTO = new EmployeeDTO(employee);
			if (quizScores != null && !quizScores.isEmpty()) {
				List<QuizScore> empScores = quizScores.stream()
						.filter(x -> x.getEmployee().equals(employee))
						.collect(Collectors.toList());
				employeeDTO.setQuizScores(empScores.stream().map(
						quizScore -> new EmployeeQuizScore(quizScore.getId(), quizScore.getScore())
					).collect(Collectors.toList()));
			}
			if (topicCompetencies != null && !topicCompetencies.isEmpty()) {
				List<TopicCompetency> empTopics = topicCompetencies.stream()
						.filter(x -> x.getEmployee().equals(employee))
						.collect(Collectors.toList());
				employeeDTO.setTopicCompetencies(empTopics.stream().map(
						topicCompetency -> new EmployeeTopicCompetency(topicCompetency.getId(), topicCompetency.getCompetency())
				).collect(Collectors.toList()));
			}
			if (qcFeedbacks != null && !qcFeedbacks.isEmpty()) {
				List<QCFeedback> empQc = qcFeedbacks.stream()
						.filter(x -> x.getEmployee().equals(employee))
						.collect(Collectors.toList());
				employeeDTO.setQcFeedbacks(empQc.stream().map(
						qcFeedback -> new EmployeeQCFeedback(qcFeedback.getId(), qcFeedback.getAssociateRating(), qcFeedback.getInstructorFeedback())
				).collect(Collectors.toList()));
			}
			employeeDTOs.add(employeeDTO);
		}

		try {
			return new ResponseEntity<>(objectMapper.writer().writeValueAsString(employeeDTOs), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<Long> createNewEmployee(String name, String email) {
		Employee employee = new Employee(name, email);
		//EMPLOYEE STARTS OFF AS A GUEST. WILL REMAIN A GUEST UNTIL EMAIL IS VERIFIED
		employee.setRole(Role.GUEST);
		employee = employeeRepository.save(employee);
		emailService.sendVerify(employee.getEmail(), employee.getId());
		return new ResponseEntity<>(employee.getId(), HttpStatus.CREATED);
	}

	public ResponseEntity<Employee> updateEmployee(Long employeeId, Employee employee) {
		Employee dbEmployee = employeeRepository.findById(employeeId).orElse(null);
		if(employee.getId() != 0){
			dbEmployee.setId(dbEmployee.getId());
		}
		if(employee.getRole() != null){
			dbEmployee.setRole(employee.getRole());
		}
		if(employee.getName() != null){
			dbEmployee.setName(employee.getName());
		}
		if(employee.getEmail() != null){
			dbEmployee.setEmail(employee.getEmail());
		}
		if(employee.getPhoneNumber() != null){
			dbEmployee.setPhoneNumber(employee.getPhoneNumber());
		}
		if(employee.getAddress() != null){
			dbEmployee.setAddress(employee.getAddress());
		}
		if(employee.getPictureUrl() != null){
			dbEmployee.setPictureUrl(employee.getPictureUrl());
		}

		employee = employeeRepository.save(dbEmployee);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	private boolean validName(String name) {
		return name != null && !name.equals("") && name.length() < 256;
	}

	private boolean validEmail(String email) {
		if (email == null) return false;
		return email.matches("^\\S+@\\S+\\.\\S+$") && email.length() < 256;
	}

	private boolean validPassword(String password) {
		return password != null && !password.equals("");
	}

}

package com.app.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CreateExamDto;
import com.app.dto.DisplayResultDto;
import com.app.dto.ExportResultDto;
import com.app.dto.SubmitAnswerDto;
import com.app.entity.Question;
import com.app.entity.Student;
import com.app.entity.Test;
import com.app.service.IAdminService;
import com.app.service.IBatchService;
import com.app.service.IExamService;
import com.app.service.IMasterService;
import com.app.service.ISubmitAnswerService;
import com.app.service.ITestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@RequestMapping("/exam")
public class ExamController {
//dependency for service class
	@Autowired
	IMasterService masterService;

	@Autowired
	IExamService examService;

	@Autowired
	IAdminService adminService;

	@Autowired
	IBatchService batchService;

	@Autowired
	ITestService testService;

	@Autowired
	ISubmitAnswerService subAnswerService;

//dependency of model mapper 
//	@Autowired
//	private ModelMapper mapper;

//noargconst
	public ExamController() {
	}

//get exam list by admin id
	@GetMapping("/examtestlist/{adminId}")
	public List<Test> getTestByAdmin(@PathVariable Long adminId) {
		return examService.getTestByAdmin(adminId);
	}

//get std list by test
	@GetMapping("/examstdlist/{adminId}/{testId}")
	public List<Student> getStudentByTest(@PathVariable Long adminId, @PathVariable Long testId) {
		return examService.getStudentByTest(adminId, testId);
	}

// get que list by test
	@GetMapping("/examquelist/{adminId}/{testId}")
	public List<Question> getQuestionByTest(@PathVariable Long adminId, @PathVariable Long testId) {
		return examService.getQuestionByTest(adminId, testId);
	}

// get que list by exam mapping
	@GetMapping("/examquestion/{adminId}/{testId}/{stdId}")
	public List<Question> getExamQuestions(@PathVariable Long adminId, @PathVariable Long testId,
			@PathVariable Long stdId) {
		return examService.getQuestionFromExam(adminId, testId, stdId);
	}

// create exam
//return full list
	/*
	 * @PostMapping("/createexam/{adminId}/{stdId}/{testId}/{queId}") public
	 * List<Master> addExam(@PathVariable Long adminId, @PathVariable
	 * ArrayList<Long> stdId, @PathVariable Long testId,
	 * 
	 * @PathVariable ArrayList<Long> queId) { return examService.insertExam(adminId,
	 * stdId, testId, queId); }
	 */

//return void
	/*
	 * @PostMapping("/createexam/{adminId}/{stdId}/{testId}/{queId}") public void
	 * addExam(@PathVariable Long adminId, @PathVariable ArrayList<Long>
	 * stdId, @PathVariable Long testId,
	 * 
	 * @PathVariable ArrayList<Long> queId) { examService.insertExam(adminId, stdId,
	 * testId, queId); }
	 */

// return void- request body
	@PostMapping("/createexam")
	public void addExam(@RequestBody CreateExamDto examDto) {
		examService.insertExam(examDto.getAdminId(), examDto.getStdId(), examDto.getTestId(), examDto.getQueId());
	}

//generate Result by Id
	@GetMapping("/marks/{stdId}/{testId}/{adminId}")
	public int getTotalMarks(@PathVariable Long stdId, @PathVariable Long testId, @PathVariable Long adminId) {
		return examService.getTotalMarks(stdId, testId, adminId);
	}

//generate Result obj by Id
	@GetMapping("/result/{stdId}/{testId}/{adminId}")
	public DisplayResultDto getResult(@PathVariable Long stdId, @PathVariable Long testId, @PathVariable Long adminId) {
		return examService.getResult(stdId, testId, adminId);
	}

//Insert Answer- submit answer
	@PatchMapping("/submitanswer/{adminId}/{stdId}/{testId}/{queId}/{language}")
	// insertAnswer(Long adminID, Long stdID, Long testID, Long queID, String
	// language, String code)
	public SubmitAnswerDto submitAnswer(@PathVariable Long adminId, @PathVariable Long stdId, @PathVariable Long testId,
			@PathVariable Long queId, @PathVariable String language, @RequestBody String objCode)
			throws JsonMappingException, JsonProcessingException {
		return subAnswerService.insertAnswer(adminId, stdId, testId, queId, language, objCode);
	}

//generate result list by batch
	@GetMapping("/resultlistbatch/{batchId}/{testId}/{adminId}")
	public List<ExportResultDto> getResultByBatch(@PathVariable Long batchId, @PathVariable Long testId,
			@PathVariable Long adminId) {
		return examService.getResultListfromBatch(adminId, testId, batchId);
	}

// generate result list by test
	@GetMapping("/resultlisttest/{adminId}/{testId}")
	public List<ExportResultDto> getResultByTest(@PathVariable Long adminId, @PathVariable Long testId) {
		return examService.getResultListfromTest(adminId, testId);
	}

//export result pdf by batch
	@GetMapping("/resultexpbatch/{batchId}/{testId}/{adminId}/result_batch.pdf")
	public void exportBatchResultPdf(@PathVariable Long batchId, @PathVariable Long testId, @PathVariable Long adminId,
			HttpServletResponse response) throws IOException, DocumentException {

		List<ExportResultDto> examResults = examService.getResultListfromBatch(adminId, testId, batchId);
		// Set up the PDF document and file path
		String filePath = "F:/DAC_Project/Coding_Assessment_Portal/result.pdf";
		FileOutputStream outputStream = new FileOutputStream(filePath);

		// Set up the PDF document
		com.itextpdf.text.Document document = new Document();
		PdfWriter.getInstance(document, outputStream);
		document.open();

		// Add a heading to the PDF document
		Paragraph heading = new Paragraph(
				"Exam Results For " + "Batch " + batchService.getByBatchId(batchId).getBatchName() + " - "
						+ batchService.getByBatchId(batchId).getBatchDescription(),
				FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
		heading.setAlignment(Element.ALIGN_CENTER);
		document.add(heading);
		document.add(new Paragraph(" ")); // add another blank line

		// Add the title to the document
		Paragraph title = new Paragraph();
		title.add(new Phrase(
				"Test Number: " + testId + " | Test Name: " + testService.getByTestId(testId).getTestTitle()));
		title.add(Chunk.NEWLINE);
		title.add(new Phrase("Test Conducted By: " + adminService.getByAdminId(adminId).getAdminFirstname() + " "
				+ adminService.getByAdminId(adminId).getAdminLastname()));
		title.add(Chunk.NEWLINE);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		document.add(new Paragraph(" ")); // add another blank line

		// Add the data to the PDF document
		PdfPTable table = new PdfPTable(6);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.setWidthPercentage(100);
		float[] columnWidths = { 3, 2, 3, 3, 2, 2 };
		table.setWidths(columnWidths);
		table.addCell("PRN");
		table.addCell("RollNo");
		table.addCell("First Name");
		table.addCell("Last Name");
		table.addCell("Marks");
		table.addCell("Max Marks");

		for (ExportResultDto examResult : examResults) {
			table.addCell(String.valueOf(examResult.getStudentPrn()));
			table.addCell(examResult.getStudentRollNo());
			table.addCell(examResult.getStudentFirstname());
			table.addCell(examResult.getStudentLastname());
			table.addCell(String.valueOf(examResult.getStudentObtMarks()));
			table.addCell(String.valueOf(examResult.getExamTotalMarks()));
		}

		document.add(table);
		// Close the PDF document and write to the response output stream
		document.close();
		outputStream.close();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=result.pdf");

		// Write the PDF file to the response output stream
		Path file = Paths.get(filePath);
		byte[] data = Files.readAllBytes(file);
		response.getOutputStream().write(data);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

// export result pdf by test
	@GetMapping("/resultexptest/{adminId}/{testId}/result_test.pdf")
	public void exportTestResultPdf(@PathVariable Long adminId, @PathVariable Long testId, HttpServletResponse response)
			throws IOException, DocumentException {

		List<ExportResultDto> examResults = examService.getResultListfromTest(adminId, testId);

		// Set up the PDF document and file path
		String filePath = "F:/DAC_Project/Coding_Assessment_Portal/result.pdf";
		FileOutputStream outputStream = new FileOutputStream(filePath);

		// Set up the PDF document
		com.itextpdf.text.Document document = new Document();
		PdfWriter.getInstance(document, outputStream);
		document.open();

		// Add a heading to the PDF document
		Paragraph heading = new Paragraph(
				"Exam Result For " + "Test Number: " + testId + " | Test Name: "
						+ testService.getByTestId(testId).getTestTitle(),
				FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
		heading.setAlignment(Element.ALIGN_CENTER);
		document.add(heading);
		document.add(new Paragraph(" ")); // add another blank line

		// Add the title to the document
		Paragraph title = new Paragraph();
		title.add(new Phrase("Test Conducted By: " + adminService.getByAdminId(adminId).getAdminFirstname() + " "
				+ adminService.getByAdminId(adminId).getAdminLastname()));
		title.add(Chunk.NEWLINE);
		title.add(new Phrase("Test Duration: " + testService.getByTestId(testId).getTestDuration() + " Hr"
				+ " | Test Description: " + testService.getByTestId(testId).getTestDescription()));
		title.add(Chunk.NEWLINE);
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		document.add(new Paragraph(" ")); // add another blank line

		// Add the data to the PDF document
		PdfPTable table = new PdfPTable(6);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.setWidthPercentage(100);
		float[] columnWidths = { 3, 2, 3, 3, 2, 2 };
		table.setWidths(columnWidths);
		table.addCell("PRN");
		table.addCell("RollNo");
		table.addCell("First Name");
		table.addCell("Last Name");
		table.addCell("Marks");
		table.addCell("Max Marks");

		for (ExportResultDto examResult : examResults) {
			table.addCell(String.valueOf(examResult.getStudentPrn()));
			table.addCell(examResult.getStudentRollNo());
			table.addCell(examResult.getStudentFirstname());
			table.addCell(examResult.getStudentLastname());
			table.addCell(String.valueOf(examResult.getStudentObtMarks()));
			table.addCell(String.valueOf(examResult.getExamTotalMarks()));
		}

		document.add(table);
		// Close the PDF document and write to the response output stream
		document.close();
		outputStream.close();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=result.pdf");

		// Write the PDF file to the response output stream
		Path file = Paths.get(filePath);
		byte[] data = Files.readAllBytes(file);
		response.getOutputStream().write(data);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}

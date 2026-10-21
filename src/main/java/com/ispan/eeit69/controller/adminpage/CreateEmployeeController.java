package com.ispan.eeit69.controller.adminpage;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit69.model.Employee;
import com.ispan.eeit69.service.EmployeeService;
import com.ispan.eeit69.utils.SystemService;
import com.ispan.eeit69.validator.EmployeeValidator;

@Controller
@RequestMapping("/adminpages")
public class CreateEmployeeController extends AbstractController {

	Logger log = LoggerFactory.getLogger(CreateEmployeeController.class);

	EmployeeService employeeService;
	// 檢查Employee資料是否正確的驗證器
	EmployeeValidator employeeValidator;

//	@Autowired
	public CreateEmployeeController(EmployeeService employeeService, EmployeeValidator employeeValidator) {
		this.employeeService = employeeService;
		this.employeeValidator = employeeValidator;
	}

	// 當使用者在首頁按下『新增員工資料』超連結十，由本方法送出可讓使用者新增員工資料的空白表單。
	// 由InsertEmployeeForm.jsp顯示空白表單，以便使用者於瀏覽器上新員工增資料。
	@GetMapping("/employee/CreateEmployeeForm")
	public String sendEmptyForm(Model model) {
		Employee employee = new Employee();
		// 表單之<form:form 標籤的 modelAttribute屬性需要下列敘述儲存的屬性物件；
		model.addAttribute(employee); // 注意：使用預設的識別字串

		log.info("送出可供前端使用者新增的空白表單");
				
		return "/adminpages/createemployee";
	}

	// 當使用者在『新建員工資料』表單新增資料完畢，按下Submit按鈕後資料會送達本方法，
	// 本方法將進行驗證與表格資料的新增。
	// 要點如下:
	// 1. public String saveEmployee(@ModelAttribute Employee employee, .....)
	// 使用位於Model內的屬性物件來接收前端送來的員工資料，Model內的屬性物件是由本類別中
	// 由@ModelAttribute修飾之方法public Employee beforeSave() 先建立用來儲存前端送來資源工資料。
	// 識別字串未標明，乃採預設值"employee"。
	// 2. BindingResult存放與資料綁定有關的錯誤訊息
	// 3. 由於表單含有日期(其他如數值資料亦同)，會因為資料格式不正確而無法進行綁定
	// (即無法將資料放入Employee物件內)而發生錯誤，要若能顯示相關的錯誤訊息，
	// 需要建立 /src/main/resources/messages.properties 檔案，並在其內自行定義相關的錯誤訊息。
	// 訊息檔預設的 base name 為messages，否則需要於 application.properties內定義 base name:
	// spring.messages.basename=your_messages_file_base_name

	@PostMapping("/employee/insertEmployee")
	public String saveEmployee(@ModelAttribute Employee employee, BindingResult result, Model model,
			RedirectAttributes ra) throws SerialException, SQLException {

		employeeValidator.validate(employee, result);
		if (result.hasErrors()) {
			log.warn("前端使用者送回的表單含有錯誤資料");
			log.warn("-------------------------------------");
			// -------------------------------------
			// 下列四列敘述只有需要獲知由於格式錯而無法綁定時所需的錯誤訊息的Key才會用到它們
//			List<ObjectError> errors = result.getAllErrors();
//			for(ObjectError error : errors) {
//				log.warn(error.toString());
//			}
			// -------------------------------------
			// 如果使用者挑選圖片，if敘述的條件會是true
			if (!result.hasFieldErrors("filename")) {
				// 將圖片與圖片檔名的取出存入Model內以方便於驗證失敗時導回原輸入畫面時仍能顯示原來的圖片
				model.addAttribute("image", employee.getImage());
				model.addAttribute("filename", employee.getFilename());
			}
			return "/adminpages/createemployee";
		}

		if (employeeService.existsByEmployeeId(employee)) {
			log.warn("提供的員工編號已存在: " + employee.getEmployeeId());
			result.rejectValue("employeeId", "employee.employeeId.exist.error", "員工編號已存在，請更換新的員工編號");
			model.addAttribute("image", employee.getImage());
			model.addAttribute("filename", employee.getFilename());
			return "/adminpages/createemployee";
		}

		char[] c = employee.getImage().toCharArray();
		Clob clob = new SerialClob(c);
		employee.setPicture(clob);
		employeeService.save(employee);
		// 要將圖檔寫入Server端的資料夾
		File imageMainFolder = new File(SystemService.EMPLOYEE_IMAGE_FILE_FOLDER);
		String fileExt = ".txt";
		File outFile = new File(imageMainFolder, "Employee_" + employee.getId() + fileExt);
		try (PrintWriter pw = new PrintWriter(outFile);) {
			pw.print(employee.getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ra.addFlashAttribute("message", "資料新增成功");
		ra.addFlashAttribute("success", true);
//		ra.addFlashAttribute("message", "<font color='blue'>資料新增成功</font>");
		// 新增與修改成功一定要用 redirect: 傳回新增/修改成功的訊息
		log.info("新增員工資料已經完成");
		return "redirect:/adminpages/employee/queryEmployee";
	}

}

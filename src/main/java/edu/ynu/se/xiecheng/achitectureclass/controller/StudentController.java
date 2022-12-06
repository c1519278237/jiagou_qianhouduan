package edu.ynu.se.xiecheng.achitectureclass.controller;

import edu.ynu.se.xiecheng.achitectureclass.common.controller.BaseController;
import edu.ynu.se.xiecheng.achitectureclass.dto.TClassDTO;
import edu.ynu.se.xiecheng.achitectureclass.entity.Selection;
import edu.ynu.se.xiecheng.achitectureclass.entity.Student;
import edu.ynu.se.xiecheng.achitectureclass.entity.TClass;
import edu.ynu.se.xiecheng.achitectureclass.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "学生实体的控制器")
@RestController
@RequestMapping("/student")
public class StudentController extends BaseController<Student,Long> {

    private StudentService studentService;

    /**
     * DTO转化器
     */
    @Resource
    private ModelMapper modelMapper;


    public StudentController(@Autowired StudentService ss){
        super(ss);
        studentService =  ss;
    }


    @ApiOperation("学生选课")
    @GetMapping("/select")
    public Selection select(@ApiParam("学号") Long stu_id,@ApiParam("教学班号") Long cls_id){
        return studentService.select(stu_id,cls_id);
    }

    @ApiOperation("学生退课")
    @GetMapping("/withDraw")
    public Selection withDraw(@ApiParam("学号") Long stu_id, @ApiParam("教学班号") Long cls_id){
       return studentService.withDraw(stu_id,cls_id);
    }

    @ApiOperation("我的课程-DTO版")
    @GetMapping("/myClasses")
    public List<TClassDTO> getMyClasses(@ApiParam("学号") Long stu_id){
        List<TClass> results=studentService.getMyClasses(stu_id);
        return results.stream().map(
                    cls -> modelMapper.map(cls, TClassDTO.class)
                ).collect(Collectors.toList());
    }

    @ApiOperation("我的课程-DO版")
    @GetMapping("/myClassesDTO")
    public List<TClass> getMyClassesDTO(@ApiParam("学号") Long stu_id){
       return  studentService.getMyClasses(stu_id);
    }
}

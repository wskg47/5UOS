package com.example.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author superBrother
 * @since 2022-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_employee")
@ApiModel(value="Employee对象", description="")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "员工编号")
    private Integer id;

    @TableField("name")
    @ApiModelProperty(value = "员工姓名")
    @Excel(name = "员工姓名")
    private String name;

    @TableField("gender")
    @ApiModelProperty(value = "员工性别")
    @Excel(name = "员工性别")
    private String gender;

    @TableField("birthday")
    @ApiModelProperty(value = "员工生日")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Excel(name = "出生日期",width = 20,format = "yyyy-MM-dd") //设置宽度
    private LocalDate birthday;

    @ApiModelProperty(value = "身份证号")
    @TableField("idCard")
    @Excel(name = "身份证号" ,width = 30)
    private String idCard;

    @ApiModelProperty(value = "婚姻状况")
    @TableField("wedlock")
    @Excel(name = "婚姻状况")
    private String wedlock;

    @ApiModelProperty(value = "民族Id")
    @TableField("nationId")
    private Integer nationId;

    @ApiModelProperty(value = "籍贯")
    @TableField("nativePlace")
    @Excel(name = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "政治面貌")
    @TableField("politicId")
    private Integer politicId;

    @TableField("email")
    @ApiModelProperty(value = "邮箱")
    @Excel(name = "邮箱",width = 30)
    private String email;

    @TableField("phone")
    @ApiModelProperty(value = "电话号码")
    @Excel(name = "电话号码",width = 15)
    private String phone;

    @TableField("address")
    @ApiModelProperty(value = "家庭住址")
    @Excel(name = "家庭住址",width = 40)
    private String address;

    @ApiModelProperty(value = "部门ID")
    @TableField("departmentId")
    private Integer departmentId;

    @ApiModelProperty(value = "职称ID")
    @TableField("jobLevelId")
    private Integer jobLevelId;

    @ApiModelProperty(value = "职位ID")
    @TableField("posId")
    private Integer posId;

    @ApiModelProperty(value = "聘用形式")
    @TableField("engageForm")
    @Excel(name = "聘用形式")
    private String engageForm;

    @ApiModelProperty(value = "学历")
    @TableField("tiptopDegree")
    @Excel(name = "学历")
    private String tiptopDegree;

    @ApiModelProperty(value = "所属专业")
    @TableField("specialty")
    @Excel(name = "所属专业",width = 20)
    private String specialty;

    @ApiModelProperty(value = "毕业学校")
    @TableField("school")
    @Excel(name = "毕业学校",width = 20)
    private String school;

    @ApiModelProperty(value = "入职日期")
    @TableField("beginDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Excel(name = "入职日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate beginDate;

    @ApiModelProperty(value = "在职状态")
    @TableField("workState")
    private String workState;

    @ApiModelProperty(value = "工号")
    @TableField("workID")
    private String workID;

    @ApiModelProperty(value = "合同期限")
    @TableField("contractTerm")
    @Excel(name = "合同期限",suffix = "年")
    private Double contractTerm;

    @ApiModelProperty(value = "转正日期")
    @TableField("conversionTime")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Excel(name = "转正日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate conversionTime;

    @ApiModelProperty(value = "离职日期")
    @TableField("notWorkDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Excel(name = "离职日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate notWorkDate;

    @ApiModelProperty(value = "合同开始日期")
    @TableField("beginContract")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Excel(name = "合同开始日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate beginContract;

    @ApiModelProperty(value = "合同终止日期")
    @TableField("endContract")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Excel(name = "合同终止日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate endContract;

    @ApiModelProperty(value = "工龄")
    @TableField("workAge")
    private String workAge;

    @ApiModelProperty(value = "工资账套ID")
    @TableField("salaryId")
    private Integer salaryId;

    @ApiModelProperty(value = "民族")
    @TableField(exist = false)
    @ExcelEntity(name = "民族")
    private Nation nation;

    @ApiModelProperty(value = "政治面貌")
    @TableField(exist = false)
    @ExcelEntity(name = "政治面貌")
    private PoliticsStatus politicsStatus;

    @ApiModelProperty(value = "职称")
    @TableField(exist = false)
    @ExcelEntity(name = "职称")
    private Joblevel joblevel;

    @ApiModelProperty(value = "部门")
    @TableField(exist = false)
    @ExcelEntity(name = "部门")
    private Department department;


    @ApiModelProperty(value = "职位")
    @TableField(exist = false)
    @ExcelEntity(name = "职位")
    private Position position;

    @ApiModelProperty(value = "工资账套")
    @TableField(exist = false)
    @ExcelEntity(name = "工资账套")
    private Salary salary;


}

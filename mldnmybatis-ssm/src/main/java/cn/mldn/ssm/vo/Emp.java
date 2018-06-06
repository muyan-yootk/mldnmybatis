package cn.mldn.ssm.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Emp implements Serializable {
	private Long empno ;
	private String name ;
	private String job ;
	private Double salary ;
	private String photo ;
	public Long getEmpno() {
		return empno;
	}
	public void setEmpno(Long empno) {
		this.empno = empno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", name=" + name + ", job=" + job + ", salary=" + salary + ", photo=" + photo
				+ "]";
	}
}

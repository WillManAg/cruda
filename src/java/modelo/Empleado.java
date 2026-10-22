/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author william.marin
 */
import java.sql.Date;

public class Empleado {

    private int empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Integer sal;
    private Integer comm;
    private Integer deptno;

    public Empleado(int empno, String ename, String job,
            Integer mgr, Date hiredate, Integer sal,
            Integer comm, Integer deptno) {

        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public int getEmpno() {
        return empno;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public Integer getSal() {
        return sal;
    }

    public Integer getComm() {
        return comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

}

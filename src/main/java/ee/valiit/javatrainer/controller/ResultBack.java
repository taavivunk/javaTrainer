package ee.valiit.javatrainer.controller;

import java.util.List;

public class ResultBack {

    private String studentName;
    private List<ResultObjects> resultObject;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<ResultObjects> getResultObject() {
        return resultObject;
    }

    public void setResultObject(List<ResultObjects> resultObject) {
        this.resultObject = resultObject;
    }
}




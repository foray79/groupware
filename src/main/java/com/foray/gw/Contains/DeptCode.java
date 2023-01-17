package com.foray.gw.Contains;

import com.foray.gw.Entity.ContainsEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeptCode {
    private List<ContainsEntity> deptcode;
    private String[] dept;

    void dataset(){
        dept = new String[4];
        dept[0]="개발팀";
        dept[1]="기획팀";
        dept[2]="마케팅팀";
        dept[3]="CS팀";
    }

    List<ContainsEntity> init(){
        this.dataset();

        deptcode = new ArrayList<>();
        for(int i=0;i<this.dept.length;i++) {
            ContainsEntity containsEntity= new ContainsEntity();
            containsEntity.setIdx((long) i);
            containsEntity.setName(dept[i]);
            //  System.out.println("deptEntity.toString() = " + deptEntity.toString());
            deptcode.add( containsEntity);
        }

        return deptcode;
    }

    public List<ContainsEntity> getCode() {
        return this.init();
    }
    public String matchCode(int i){
        this.dataset();
        return this.dept[i];
    }
}

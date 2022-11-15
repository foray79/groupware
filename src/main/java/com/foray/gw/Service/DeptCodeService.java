package com.foray.gw.Service;

import com.foray.gw.Entity.DeptEntity;
import com.foray.gw.Entity.DocuType;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DeptCodeService {
    private List<DeptEntity> deptcode;
    private String[] dept;

    void dataset(){
        dept = new String[4];
        dept[0]="개발팀";
        dept[1]="기획팀";
        dept[2]="마케팅팀";
        dept[3]="CS팀";
    }

    List<DeptEntity> init(){
        this.dataset();

        deptcode = new ArrayList<>();
        for(int i=0;i<this.dept.length;i++) {
            DeptEntity deptEntity= new DeptEntity();
            deptEntity.setIdx(new Long(i));
            deptEntity.setDeptName(dept[i]);
          //  System.out.println("deptEntity.toString() = " + deptEntity.toString());
            deptcode.add( deptEntity);
        }

        return deptcode;
    }

    public List<DeptEntity> getCode() {
        return this.init();
    }
    public String matchCode(int i){
        this.dataset();
        return this.dept[i];
    }
}

package com.foray.gw.Contains;

import com.foray.gw.Entity.ContainsEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Postion {
    private List<ContainsEntity> code;
    private String[] lev;

    void dataset(){
        lev = new String[4];
        lev[0]="담당자";
        lev[1]="기관장";
        lev[2]="교역자";
        lev[3]="당회장";
    }

    List<ContainsEntity> init(){
        this.dataset();

        code = new ArrayList<>();
        for(int i=0;i<this.lev.length;i++) {
            ContainsEntity containsEntity= new ContainsEntity();
            containsEntity.setIdx((long) i);
            containsEntity.setName(lev[i]);
            //  System.out.println("deptEntity.toString() = " + deptEntity.toString());
            code.add( containsEntity);
        }

        return code;
    }

    public List<ContainsEntity> getCode() {
        return this.init();
    }
    public String matchCode(int i){
        this.dataset();
        return this.lev[i];
    }

}


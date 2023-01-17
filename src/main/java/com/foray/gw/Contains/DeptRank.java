package com.foray.gw.Contains;
import com.foray.gw.Entity.ContainsEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeptRank {
    private List<ContainsEntity> code;
    private String[] rank;

    void dataset(){
        rank = new String[7];
        rank[0]="성도";
        rank[1]="집사";
        rank[2]="권사";
        rank[3]="장로";
        rank[4]="전도사";
        rank[5]="강도사";
        rank[6]="목사";
    }

    List<ContainsEntity> init(){
        this.dataset();

        code = new ArrayList<>();
        for(int i=0;i<this.rank.length;i++) {
            ContainsEntity containsEntity= new ContainsEntity();
            containsEntity.setIdx((long) i);
            containsEntity.setName(rank[i]);
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
        return this.rank[i];
    }

}

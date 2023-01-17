package com.foray.gw.Service;

import com.foray.gw.Dto.ApprovalDto;
import com.foray.gw.Dto.ApprovalVo;
import com.foray.gw.Dto.UserDto;
import com.foray.gw.Entity.ApprovalEntity;
import com.foray.gw.Entity.DocumentEntity;
import com.foray.gw.Entity.UserEntity;
import com.foray.gw.Enum.ApprovalType;
import com.foray.gw.Repository.ApprovalRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@Transactional
public class ApprovalService {

    @Autowired
    ApprovalRepository approvalRepository;
    @Autowired
    UserService userService;
    @Autowired
    DocumentService documentservice;

    public void add(ApprovalDto approvalDto)
    {
        if(approvalDto.getIdx() == null) {
            ApprovalEntity approval = ApprovalDto.Trans(approvalDto);

            approvalRepository.save(approval);
        }
    }
    public void del(ApprovalVo approvalVo)
    {

        Long[] dels = approvalVo.getDelIdx();
        if(dels != null) {
            for (int i = 0; i < dels.length; i++) {
                Long del_idx = dels[i];
                approvalRepository.deleteById(del_idx);
            }
        }
    }
    public String toJson(HashMap<ApprovalType, String> approvalType)
    {
        //List<String> json = new ArrayList<>();
        JSONObject obj = new JSONObject();



        for(ApprovalType key : approvalType.keySet()){
            System.out.println("key = "+key.toString() + ", val = "+approvalType.get(key));

            obj.put(key.toString(),approvalType.get(key));
        }
        return obj.toJSONString();
   //     return "{"+String.join(",",json)+"}";

    }
    public String toJson(List<ApprovalEntity> approvalList)
    {
        //List<String> json = new ArrayList<>();
        JSONObject obj = new JSONObject();

        int key=0;

        for(ApprovalEntity Approval : approvalList) {
            JSONObject subobj = new JSONObject();

            subobj.put("idx",Approval.getIdx());
            subobj.put("userIdx",Approval.getUserIdx());
            subobj.put("name",Approval.getName());
            subobj.put("sign",Approval.getSign());

            for(Field field : Approval.getClass().getDeclaredFields()){
                field.setAccessible(true);

                if(field.getName()=="document"){
                    continue;
                }
                if(field.getName() =="idx") {
                    subobj.put("idx",Approval.getIdx());
                    continue;
                }
                if(field.getName() == "approvalType"){
                    subobj.put("approvalType",Approval.getApprovalType().getCode());
                    continue;
                }

                try {
                    if(field.get(Approval) == null)
                    {
                        subobj.put(field.getName(), "");
                    }else {
                        subobj.put(field.getName(), field.get(Approval).toString());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        obj.put(key,subobj);
        key++;
        }
        return obj.toJSONString();
    }
    //List<ApprovalDto> approvalDtos @RequestParam Map params
    public List<ApprovalDto> makeApprovalDto(Long idx, ApprovalVo approvalVo) {

        List<ApprovalDto> approvalDtoList = new ArrayList<>();

        /*문서 정보*/
        DocumentEntity document = documentservice.get(idx);

        Long[] apprIdxs = approvalVo.getApprIdx();
        Long[] userIdxs = approvalVo.getUserIdx();
        String[] signs = approvalVo.getSign();
        String[] approvalTypes = approvalVo.getApprovalType();
        if(userIdxs!=null && userIdxs.getClass().isArray() ) {
            for (int i = 0; i < userIdxs.length; i++) {
                ApprovalDto approvalDto = new ApprovalDto();
                approvalDto.setDocument(document); //문서코드

                Long appr_idx = apprIdxs[i];
                approvalDto.setIdx(appr_idx);

                if (userIdxs[i] == null) continue;
                else {
                    Long user_idx = userIdxs[i];
                    approvalDto.setUserIdx(user_idx);
                }


                String sign = signs[i];
                approvalDto.setSign(sign);

                ApprovalType approvalType = ApprovalType.valueOf(approvalTypes[i]);
                approvalDto.setApprovalType(approvalType);

                UserEntity userEntity = userService.get(approvalDto.getUserIdx());
                approvalDto.setUserName(userEntity.getUserName());

                approvalDtoList.add(approvalDto);
            }
        }
        return approvalDtoList;
    }
}

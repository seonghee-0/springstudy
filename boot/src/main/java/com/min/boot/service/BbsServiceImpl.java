package com.min.boot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.min.boot.dto.BbsDTO;
import com.min.boot.dto.UserDTO;
import com.min.boot.mapper.IBbsMapper;
import com.min.boot.utils.PageUtils;
import com.min.boot.utils.SecurityUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BbsServiceImpl implements IBbsService {

  private final IBbsMapper bbsMapper;
  private final PageUtils pageUtils;
  private final SecurityUtils securityUtils;
  
  @Override
  public int saveBbsParent(HttpServletRequest request) {
        
    HttpSession session = request.getSession();
    UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
    
    if(loginUser == null)  // 로그인이 풀린 유저
      return 0;
    
    String contents = securityUtils.preventXss(request.getParameter("contents"));
    BbsDTO bbsParent = BbsDTO.builder()
        .contents(contents)
        .userDTO(loginUser)// userDTO로 userno 를 불러와야하는데 loginUser에 userno 가 있음
        .build();
    
    return bbsMapper.insertBbsParent(bbsParent);
    
  }

  @Override
  public void loadBbsList(HttpServletRequest request, Model model) {
      
    //paging 처리 (page, total, display)
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));

    // 파라미터로 page가 전달되지않으면 1페이지를 보여주겠다.
    int page = Integer.parseInt(optPage.orElse("1"));
    int total = bbsMapper.selectBbsCount();
    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("20"));
    
    //paging 처리에 필요한 모든 요소 계산
    pageUtils.setPaging(total, display, page);
    
    // begin 과 end 값을 Map으로 만듬
    Map<String,Object> params = new HashMap<>();
    params.put("begin", pageUtils.getBegin());
    params.put("end", pageUtils.getEnd());
    
    // 목록 가져오기
    List<BbsDTO> bbsList = bbsMapper.selectBbsList(params);
    // View 로 전달(=forward)할 데이터 Model 에 저장하기
    model.addAttribute("total",total);
    model.addAttribute("bbsList", bbsList);
    model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/bbs/list.do"
        , ""
        , display
        , ""));

}
  

  @Override
  public int saveBbsChild(HttpServletRequest request) {
    
    /* 원글의 정보 */
    int depth = Integer.parseInt(request.getParameter("depth"));
    int groupNo = Integer.parseInt(request.getParameter("groupNo"));
    int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
    
    /* 답글의 정보 */
    String contents = securityUtils.preventXss(request.getParameter("contents"));
    
    /* 작성자의 정보 */
    UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
    
    /* BbsDTO bbsParent 과 updateGroupOrder */
    BbsDTO bbsParent = BbsDTO.builder()
        .groupNo(groupNo)
        .groupOrder(groupOrder)
        .build();
    bbsMapper.updateGroupOrder(bbsParent);
    
    /* BbsDTO bbsChild 와 insertBbsChild */
    BbsDTO bbsChild = BbsDTO.builder()
        .contents(contents)
        .userDTO(loginUser)
        .depth(depth + 1)
        .groupNo(groupNo)
        .groupOrder(groupOrder + 1)
        .build();
    
    return bbsMapper.insertBbsChild(bbsChild);
    
  }
  
  @Override
  public int removeBbs(int bbsNo) {
    return bbsMapper.deleteBbs(bbsNo);
  }
  
  @Override
  public void loadFindList(HttpServletRequest request, Model model) {
    
    // 검색 칼럼 / 검색어
    String column = request.getParameter("column");
    String query = request.getParameter("query");
    Map<String, Object> params = new HashMap<>();
    params.put("column", column);
    params.put("query", query);
    
    // paging 처리를 위한 준비물 (page, total, display)
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));
    int total = bbsMapper.selectFindCount(params);
    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("3"));
    
    // paging 처리에 필요한 모든 요소 계산
    pageUtils.setPaging(total, display, page);
    
    // begin 과 end 값을 Map 으로 만듬
    params.put("begin", pageUtils.getBegin());
    params.put("end", pageUtils.getEnd());
    
    // 목록 가져오기
    List<BbsDTO> bbsList = bbsMapper.selectFindList(params);
    
    // View 로 전달(forward)할 데이터 Model 에 저장하기
    model.addAttribute("total", total);
    model.addAttribute("bbsList", bbsList);
    model.addAttribute("paging", pageUtils.getPaging(request.getContextPath() + "/bbs/find.do"
                                                   , ""
                                                   , display
                                                   , "column=" + column + "&query=" + query));
    
  }
  
}